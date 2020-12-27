package com.joojet.plugins.mobs.skills.attack;

import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.entity.FallingBlock;
import org.bukkit.entity.LivingEntity;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.util.Vector;

import com.joojet.plugins.agcraft.main.AGCraftPlugin;
import com.joojet.plugins.mobs.DamageDisplayListener;
import com.joojet.plugins.mobs.skills.runnable.ExplodingBlockRunnable;
import com.joojet.plugins.mobs.util.MathUtil;

public class AnvilThrowSkill extends AnvilDropSkill {
	
	/** Allows the monster to use an "anvil throw skill", allowing it
	 *  to throw multiple anvils at a parabolic arc to its enemies.
	 *  @param range - Range of the skill
	 *  @param cooldown - Skill's cooldown
	 *  @param weight - Skill's weight
	 *  @param power - Explosion power of the falling anvil
	 *  @param amount - Total number of anvils used at a time when this skill is used */
	public AnvilThrowSkill(int range, int cooldown, int weight, float power, int amount) 
	{
		super(range, cooldown, weight, power, amount);
	}

	@Override
	protected void handleSkill(LivingEntity caster, List<LivingEntity> allies, List<LivingEntity> enemies,
			DamageDisplayListener damageDisplayListener) 
	{
		List <LivingEntity> targets = this.selectRandomEntities(enemies, amount);
		
		if (!targets.isEmpty())
		{
			caster.swingMainHand();
			caster.getWorld().spawnParticle(Particle.CRIT, caster.getEyeLocation(), 30, 1.0, 1.0, 1.0);
			caster.getWorld().playSound(caster.getEyeLocation(), Sound.BLOCK_ANVIL_PLACE, 1.0f, 1.0f);
			caster.getWorld().playSound(caster.getEyeLocation(), Sound.ITEM_TRIDENT_THROW, 1.0f, 1.0f);
			damageDisplayListener.displayStringAboveEntity(caster, ChatColor.BOLD + "" + ChatColor.DARK_RED + "HAVE A TASTE OF IRON!");
		}
		
		for (LivingEntity target : targets)
		{
			// Checks if the entity has line of sight with the target. If not, ignore this entity.
			if (!caster.hasLineOfSight(target))
			{
				continue;
			}
			
			Vector velocity = MathUtil.calculateArcBetweenPoints(caster.getLocation().toVector().clone(), target.getLocation().toVector().clone(), 
					(int) (caster.getHeight() * 2));
			
			// Check if the velocity vector is finite. If not, skip spawning this anvil.
			try
			{
				velocity.checkFinite();
			}
			catch (IllegalArgumentException iae)
			{
				continue;
			}
			
			FallingBlock anvil = this.spawnAnvil(target.getWorld(), caster.getLocation());
			anvil.setVelocity(velocity.normalize());
			caster.addPotionEffect(new PotionEffect (PotionEffectType.DAMAGE_RESISTANCE, 80, 3));
			
			new ExplodingBlockRunnable (anvil, 140, this.power, target).runTaskTimer(AGCraftPlugin.plugin, 10, 1);
		}
	}
}
