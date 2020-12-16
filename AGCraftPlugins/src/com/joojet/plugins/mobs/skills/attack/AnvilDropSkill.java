package com.joojet.plugins.mobs.skills.attack;

import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.entity.FallingBlock;
import org.bukkit.entity.LivingEntity;
import org.bukkit.scheduler.BukkitRunnable;

import com.joojet.plugins.agcraft.main.AGCraftPlugin;
import com.joojet.plugins.mobs.DamageDisplayListener;

public class AnvilDropSkill extends AbstractAttackSkill {
	
	/** Explosion power of the falling anvil */
	protected float power;
	/** Total number of anvils used at a time */
	protected int amount;
	
	/** Creates a new anvil drop skill for a custom entity to use, which drops an anvil onto a random enemy
	 *  @param range - Range of the skill
	 *  @param cooldown - Skill's cooldown
	 *  @param weight - Skill's weight
	 *  @param power - Explosion power of the falling anvil
	 *  @param amount - Total number of anvils used at a time when this skill is used */
	public AnvilDropSkill(int range, int cooldown, int weight, float power, int amount) 
	{
		super(range, cooldown, Integer.MAX_VALUE, weight);
		this.power = power;
		this.amount = amount;
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
			damageDisplayListener.displayStringAboveEntity(caster, ChatColor.BOLD + "" + ChatColor.DARK_RED + "TAKE THIS!");
		}
		
		for (LivingEntity target : targets)
		{
			FallingBlock anvil = (FallingBlock) target.getWorld().spawnFallingBlock(target.getLocation().add(0.0, 6.0, 0.0), Bukkit.createBlockData(Material.DAMAGED_ANVIL));
			anvil.setHurtEntities(true);
			anvil.setFallDistance(256.0f);
			anvil.setSilent(true);
			anvil.getWorld().spawnParticle(Particle.CRIT, anvil.getLocation(), 30, 1.0, 1.0, 1.0);
			anvil.getWorld().spawnParticle(Particle.SPELL_INSTANT, anvil.getLocation(), 30, 1.0, 1.0, 1.0);
			anvil.getWorld().spawnParticle(Particle.SMOKE_NORMAL, anvil.getLocation(), 10, 1.0, 1.0, 1.0);
			
			new BukkitRunnable () {
				// Max amount of time (in ticks) this runnable is active before forcefully closing
				private int ticks = 100;
				
				@Override
				public void run ()
				{
					if (anvil != null)
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
				}
			}.runTaskTimer(AGCraftPlugin.plugin, 0, 1);
		}
	}

	@Override
	protected boolean checkConditons(LivingEntity caster, List<LivingEntity> allies, List<LivingEntity> enemies) {
		return !enemies.isEmpty();
	}

}
