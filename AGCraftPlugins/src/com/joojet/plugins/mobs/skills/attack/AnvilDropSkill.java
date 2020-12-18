package com.joojet.plugins.mobs.skills.attack;

import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.World;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.FallingBlock;
import org.bukkit.entity.LivingEntity;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import com.joojet.plugins.agcraft.main.AGCraftPlugin;
import com.joojet.plugins.mobs.DamageDisplayListener;
import com.joojet.plugins.mobs.enums.MonsterClassifier;
import com.joojet.plugins.mobs.skills.runnable.ExplodingBlockRunnable;

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
			FallingBlock anvil = this.spawnAnvil(target.getWorld(), target.getLocation().add(0.0, 6.0, 0.0));
			
			caster.addPotionEffect(new PotionEffect (PotionEffectType.DAMAGE_RESISTANCE, 80, 3));
			
			new ExplodingBlockRunnable (anvil, 100, this.power, target).runTaskTimer(AGCraftPlugin.plugin, 0, 1);
		}
	}

	@Override
	protected boolean checkConditons(LivingEntity caster, List<LivingEntity> allies, List<LivingEntity> enemies) 
	{
		if (!enemies.isEmpty())
		{
			// Do a villager check if the caster is an iron golem
			if (caster.getType() == EntityType.IRON_GOLEM)
			{
				for (LivingEntity ally : allies)
				{
					if (ally.getType() == EntityType.WANDERING_TRADER || ally.getType() == EntityType.VILLAGER)
					{
						return false;
					}
				}
				return caster.getFireTicks() == 0;
			}
			// Otherwise, the caster is only allowed to use the skill if the the player's threat score exceeds mythic.
			else
			{
				return this.spawnWeight.getAverageThreatScore(caster) >= MonsterClassifier.MYTHIC.getThreshold();
			}
		}
		return false;
	}
	
	/** Spawns in a falling anvil projectile at a specific location
	 *  @param world - The world this anvil entity is being spawned into
	 *  @param location - The location in which this anvil is spawned */
	protected FallingBlock spawnAnvil (World world, Location location)
	{
		FallingBlock anvil = (FallingBlock) world.spawnFallingBlock(location, Bukkit.createBlockData(Material.DAMAGED_ANVIL));
		anvil.setHurtEntities(true);
		anvil.setFallDistance(256.0f);
		anvil.setSilent(true);
		anvil.getWorld().spawnParticle(Particle.CRIT, anvil.getLocation().add(0.0, 1.0, 0.0), 30, 1.0, 1.0, 1.0);
		anvil.getWorld().spawnParticle(Particle.SPELL_INSTANT, anvil.getLocation(), 30, 1.0, 1.0, 1.0);
		anvil.getWorld().spawnParticle(Particle.SMOKE_NORMAL, anvil.getLocation(), 10, 1.0, 1.0, 1.0);
		return anvil;
	}

}
