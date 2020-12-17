package com.joojet.plugins.mobs.skills.attack;

import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.entity.FallingBlock;
import org.bukkit.entity.LivingEntity;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

import com.joojet.plugins.agcraft.main.AGCraftPlugin;
import com.joojet.plugins.mobs.DamageDisplayListener;
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
			
			FallingBlock anvil = (FallingBlock) target.getWorld().spawnFallingBlock(caster.getLocation(), Bukkit.createBlockData(Material.DAMAGED_ANVIL));
			anvil.setHurtEntities(true);
			anvil.setFallDistance(256.0f);
			anvil.setSilent(true);
			anvil.getWorld().spawnParticle(Particle.CRIT, anvil.getLocation().add(0.0, 1.0, 0.0), 30, 1.0, 1.0, 1.0);
			anvil.getWorld().spawnParticle(Particle.SPELL_INSTANT, anvil.getLocation(), 30, 1.0, 1.0, 1.0);
			anvil.getWorld().spawnParticle(Particle.SMOKE_NORMAL, anvil.getLocation(), 10, 1.0, 1.0, 1.0);
			anvil.setFireTicks(Integer.MAX_VALUE);
			
			anvil.setVelocity(velocity.normalize());
			
			caster.addPotionEffect(new PotionEffect (PotionEffectType.DAMAGE_RESISTANCE, 80, 3));
			
			new BukkitRunnable () {
				// Max amount of time (in ticks) this runnable is active before forcefully closing
				private int ticks = 140;
				
				@Override
				public void run ()
				{
					if (ticks <= 0)
					{
						this.cancel();
					}
						
					if (anvil.isOnGround() || anvil.isDead())
					{
						anvil.getWorld().createExplosion(anvil.getLocation(), power, false, false);
						this.cancel();
					}
					--ticks;
				}
			}.runTaskTimer(AGCraftPlugin.plugin, 10, 1);
		}
	}
}
