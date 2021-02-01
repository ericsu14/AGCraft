package com.joojet.plugins.mobs.skills.buff;

import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.entity.AbstractArrow;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Projectile;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import com.joojet.plugins.mobs.monsters.MobEquipment;
import com.joojet.plugins.mobs.skills.passive.interfaces.PassiveAttack;
import com.joojet.plugins.mobs.skills.passive.interfaces.PassiveProjectile;

public class RageSkill extends AbstractBuffSkill implements PassiveProjectile, PassiveAttack
{
	/** Used to determine if a mob is enraged */
	private boolean enraged;
	/** Health percentage threshold the monster's health needs to reach before this skill is activated */
	private double threshold;
	/** Base damage amplifier used to increase incoming and outgoing damage when enraged */
	private final double baseDamageAmplifier = 0.10;
	
	/** Allows a monster to temporarily increase its strength and health once its
	 *  base health drops below a certain percentage.
	 *  @param amplifier - Power of the strength effect
	 *  @param duration - Duration of the monster's rage mode */
	public RageSkill (int amplifier, int duration, double threshold)
	{
		super (PotionEffectType.INCREASE_DAMAGE, duration, amplifier, 0, duration, 24);
		this.maxUses = 1;
		this.currentUsage = 1;
		this.enraged = false;
		this.threshold = threshold;
	}
	
	@Override
	protected void playBuffAnimation(LivingEntity entity) 
	{
		this.spawnColoredParticlesOnEntity(entity, 30, 255, 255, 255, Particle.VILLAGER_ANGRY);
		this.spawnColoredParticlesOnEntity(entity, 30, 64, 0, 0, Particle.SPELL_MOB);
		entity.getWorld().playSound(entity.getLocation(), Sound.ENTITY_ENDER_DRAGON_GROWL, 1.0F, 1.0F);
	}
	
	/** Overrides the generic potion effect skill, as we are going to apply multiple effects */
	@Override
	protected void applyPotionEffect (LivingEntity entity, PotionEffectType potion, int duration, int strength)
	{
		entity.addPotionEffect(new PotionEffect (PotionEffectType.GLOWING, duration, 0, false, true));
		entity.addPotionEffect(new PotionEffect (PotionEffectType.ABSORPTION, duration, 4, false, true));
		this.enraged = true;
	}

	/** Activates rage mode once the monster's health drops below 35%, which increases damage */
	@Override
	protected boolean checkConditons(LivingEntity caster, List<LivingEntity> allies,
			List<LivingEntity> enemies) 
	{
		return true;
	}
	
	@Override
	protected boolean checkConditions(LivingEntity caster) 
	{
		return this.checkHealthIsBelowThreshold(caster, this.threshold);
	}
	
	/** Overrides the update function to play a small animation when mob is enraged */
	@Override
	public void update (LivingEntity caster)
	{
		super.update(caster);
		if (this.enraged)
		{
			if (this.random.nextBoolean())
			{
				this.spawnColoredParticlesOnEntity(caster, 10, 0, 0, 0, Particle.SMOKE_LARGE);
				this.spawnColoredParticlesOnEntity(caster, 15, 0, 0, 0, Particle.FLAME);
			}
		}
		if (this.cooldownTick <= 0)
		{
			this.enraged = false;
		}
	}
	
	@Override
	protected String getBuffText() 
	{
		return ChatColor.RED + "" + ChatColor.BOLD + "☠ RAGE" + ChatColor.GOLD + " ENGAGED";
	}
	
	/** Projectiles shot by mobs with an active rage buff now has their projectile's base damage
	 *  increased by a certain percentage based on the passed amplifier. */
	@Override
	public void modifyProjectile(LivingEntity shooter, Projectile projectile, MobEquipment shooterEquipment) 
	{
		if (!this.enraged || shooter == null || shooterEquipment == null || projectile == null || 
				!(projectile instanceof AbstractArrow))
		{
			return;
		}
		
		AbstractArrow arrow = (AbstractArrow) projectile;
		arrow.setFireTicks(Integer.MAX_VALUE);
		// Plays particle and sound effects after launching a projectile under rage
		this.spawnColoredParticlesOnEntity(shooter, 10, 0, 0, 0, Particle.LAVA);
		shooter.getWorld().spawnParticle(Particle.SWEEP_ATTACK, shooter.getLocation(), 1, 0.0, 0.0, 0.0);
		shooter.getWorld().playSound(shooter.getLocation(), Sound.ENTITY_PLAYER_ATTACK_KNOCKBACK, 1.0f, 1.0f);
	}

	@Override
	public double modifyOutgoingDamageEvent(double damage, LivingEntity damager, LivingEntity target,
			MobEquipment damagerEquipment) 
	{
		double bonusDamage = 0.0;
		if (this.enraged)
		{
			bonusDamage = (damage * ((this.potionStrength + 1) * this.baseDamageAmplifier));
			this.spawnColoredParticlesOnEntity(target, 10, 0, 0, 0, Particle.CRIT);
			damager.getWorld().playSound(damager.getLocation(), Sound.ENTITY_PLAYER_ATTACK_CRIT, 1.0f, 1.0f);
		}
		return bonusDamage;
	}

	/** Custom mobs who are currently enraged takes more damage than usual. */
	@Override
	public double modifyIncomingDamageEvent(double damage, LivingEntity damager, LivingEntity target,
			MobEquipment targetEquipment) 
	{
		double bonusDamage = 0.0;
		
		if (this.enraged)
		{
			bonusDamage = (damage * ((this.potionStrength + 1) * this.baseDamageAmplifier));
			this.spawnColoredParticlesOnEntity(target, 20, 128, 0, 0, Particle.REDSTONE);
			target.getWorld().playSound(target.getLocation(), Sound.BLOCK_STONE_BREAK, 1.0f, 1.0f);
		}
		return bonusDamage;
	}

}
