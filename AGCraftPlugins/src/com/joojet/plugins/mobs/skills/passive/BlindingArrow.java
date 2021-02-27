package com.joojet.plugins.mobs.skills.passive;

import org.bukkit.Color;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Projectile;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import com.joojet.plugins.mobs.monsters.MobEquipment;
import com.joojet.plugins.mobs.skills.passive.interfaces.PassiveAttack;
import com.joojet.plugins.mobs.skills.passive.interfaces.PassiveProjectile;
import com.joojet.plugins.mobs.util.particle.ParticleUtil;

public class BlindingArrow extends AbstractPassiveSkill implements PassiveProjectile, PassiveAttack
{
	/** Counts the number of arrows shot by this entity */
	protected int arrowCounter;
	/** Allows the skill-caster to fire an arrow inflicted with the blind status once every n shots */
	protected int numArrowsBeforeFiring;
	/** Duration of blind effect */
	protected int duration;
	
	/** Allow the skill-caster to fire a special arrow with the blinding effect
	 *  once every certain amount of shots
	 *  @param duration Duration of the blindness effect in seconds
	 *  @param numArrowsBeforeFiring Number of arrows needed to be fired before this special arrow can be fired. */
	public BlindingArrow (int duration, int numArrowsBeforeFiring)
	{
		this.duration = duration * 20;
		this.numArrowsBeforeFiring = numArrowsBeforeFiring;
		this.arrowCounter = 0;
	}
	
	@Override
	public double modifyOutgoingDamageEvent(double damage, Entity source, LivingEntity damager, LivingEntity target,
			MobEquipment damagerEquipment, MobEquipment targetEquipment) 
	{
		if (source != null && source instanceof Arrow)
		{
			Arrow arrow = (Arrow) source;
			for (PotionEffect effect : arrow.getCustomEffects())
			{
				// Applies blindness particle effects on blinding arrows
				if (effect.getType() == PotionEffectType.BLINDNESS)
				{
					ParticleUtil.spawnColoredParticlesOnEntity(target, 8, 0, 0, 0, Particle.SQUID_INK);
					ParticleUtil.spawnColoredParticlesOnEntity(target, 8, 0, 0, 0, Particle.SMOKE_LARGE);
					target.getWorld().playSound(target.getLocation(), Sound.ENTITY_SQUID_SQUIRT, 1.0f, 1.0f);
				}
			}
		}
		return 0;
	}

	@Override
	public double modifyIncomingDamageEvent(double damage, Entity source, LivingEntity damager, LivingEntity target,
			MobEquipment targetEquipment) 
	{
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void modifyProjectile(LivingEntity shooter, Projectile projectile, MobEquipment shooterEquipment) 
	{
		if (this.arrowCounter % this.numArrowsBeforeFiring == 0 
				&& (projectile instanceof Arrow))
		{
			Arrow arrow = (Arrow) projectile;
			arrow.addCustomEffect(new PotionEffect (PotionEffectType.BLINDNESS, this.duration, 0), true);
			arrow.setColor(Color.BLACK);
			projectile.getWorld().spawnParticle(Particle.SQUID_INK, projectile.getLocation(), 8, 0.1, 0.1, 0.1, 0.1);
			shooter.getWorld().spawnParticle(Particle.SWEEP_ATTACK, shooter.getLocation(), 1, 0.1, 0.1, 0.1);
			shooter.getWorld().playSound(shooter.getLocation(), Sound.ENTITY_ILLUSIONER_CAST_SPELL, 1.0f, 1.0f);
		}
		++this.arrowCounter;
	}
	
}
