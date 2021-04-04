package com.joojet.plugins.mobs.skills.attack;

import java.util.List;

import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.entity.EnderPearl;
import org.bukkit.entity.LivingEntity;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.BoundingBox;
import org.bukkit.util.Vector;

import com.joojet.plugins.agcraft.main.AGCraftPlugin;
import com.joojet.plugins.mobs.DamageDisplayListener;
import com.joojet.plugins.mobs.bossbar.BossBarController;
import com.joojet.plugins.mobs.interpreter.MonsterTypeInterpreter;
import com.joojet.plugins.mobs.util.MathUtil;

import net.md_5.bungee.api.ChatColor;

public class ThrowEnderPearlSkill extends AbstractAttackSkill 
{
	/** Min. distance the player needs to be for the peral to be thrown */
	protected double minDistance;
	
	/** Allows the skillcaster to throw an ender-pearl to a player's location. */
	public ThrowEnderPearlSkill(int range, int cooldown, int maxUses, int weight, double minDistance) 
	{
		super(range, cooldown, maxUses, weight);
		this.minDistance = minDistance;
	}

	@Override
	protected void handleSkill(LivingEntity caster, List<LivingEntity> allies, List<LivingEntity> enemies,
			DamageDisplayListener damageDisplayListener, MonsterTypeInterpreter monsterTypeInterpreter,
			BossBarController bossBarController) 
	{
		// Gets the player who is most farthest away from the caster within the skill's range		
		List <LivingEntity> targets = this.convertStreamToList(
				this.sortByClosestProximity(
						this.filterByLineOfSight(
						this.filterNonPlayerEntities(enemies.stream()).
						filter(entity -> checkEntityWithinRange (entity, caster)), caster), caster));
		
		// Do nothing if no player is nearby
		if (targets.isEmpty())
		{
			return;
		}
		
		this.sortByClosestProximity(targets, caster);
		LivingEntity farthestTarget = targets.get(targets.size() - 1);
		
		Location farthestTargetLocation = farthestTarget.getLocation().clone();
		
		damageDisplayListener.displayStringAboveEntity(caster, ChatColor.YELLOW + "" + ChatColor.BOLD + "I don't think so...");
		caster.getWorld().playSound(caster.getLocation(), Sound.ENTITY_EVOKER_CAST_SPELL, 1.0f, 1.0f);
		farthestTarget.sendMessage(caster.getCustomName() + "" + ChatColor.LIGHT_PURPLE + " threw an ender pearl to your location!");
		
		new BukkitRunnable () 
		{
			@Override
			public void run() 
			{
				// Calculate the velocity vector between the caster and the farthest target
				Vector velocity = MathUtil.calculateArcBetweenPoints(caster.getLocation().toVector().clone(), farthestTargetLocation.toVector(), 
						(int) (caster.getHeight()));
				
				// Check if the velocity vector is finite. If not, skip spawning this anvil.
				try
				{
					velocity.checkFinite();
				}
				catch (IllegalArgumentException iae)
				{
					return;
				}
				
				caster.getWorld().playSound(caster.getLocation(), Sound.ENTITY_ENDER_PEARL_THROW, 1.0f, 1.0f);
				caster.launchProjectile(EnderPearl.class, velocity);
			}
			
		}.runTaskLater(AGCraftPlugin.plugin, 20);
	}

	@Override
	protected boolean checkConditons(LivingEntity caster, List<LivingEntity> allies, List<LivingEntity> enemies) 
	{
		return this.sortByClosestProximity(
				this.filterByLineOfSight(
				this.filterNonPlayerEntities(enemies.stream()).
				filter(entity -> checkEntityWithinRange (entity, caster)), caster), caster).toArray().length != 0;
	}

	@Override
	protected boolean checkConditions(LivingEntity caster) 
	{
		return this.checkHealthIsBelowThreshold(caster, 0.70);
	}
	
	/** Returns true if the entity's location is between the passed min. and max range of the caster */
	protected boolean checkEntityWithinRange (LivingEntity entity, LivingEntity caster)
	{
		BoundingBox minBound = caster.getBoundingBox().clone().expand(this.minDistance);
		BoundingBox maxBound = caster.getBoundingBox().clone().expand(this.range / 2.0);
		BoundingBox entityBox = entity.getBoundingBox();
		return !minBound.contains(entityBox) && maxBound.contains(entityBox);
	}

}
