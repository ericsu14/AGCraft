package com.joojet.plugins.mobs;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Monster;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityTargetLivingEntityEvent;
import org.bukkit.event.entity.EntityTransformEvent;
import org.bukkit.event.entity.EntityTargetEvent.TargetReason;
import org.bukkit.event.entity.EntityTransformEvent.TransformReason;
import org.bukkit.event.world.ChunkLoadEvent;
import org.bukkit.plugin.Plugin;

import com.joojet.plugins.agcraft.enums.ServerMode;
import com.joojet.plugins.agcraft.main.AGCraftPlugin;
import com.joojet.plugins.mobs.bossbar.BossBarAPI;
import com.joojet.plugins.mobs.enums.MobFlag;
import com.joojet.plugins.mobs.enums.MonsterStat;
import com.joojet.plugins.mobs.monsters.MobEquipment;
import com.joojet.plugins.mobs.util.EquipmentTools;
import com.joojet.plugins.warp.scantools.ScanEntities;

public class PathfindTargetingEventListener implements Listener
{
	public void onEnable ()
	{
		Bukkit.getPluginManager().registerEvents(this, (Plugin) this);
	}
	
	/** Listens to AI target events and cancels targeting events if the custom mob is called
	 *  to ignore the targeted entity. */
	@EventHandler
	public void onTargetEvent (EntityTargetLivingEntityEvent event)
	{
		// Do nothing if entity is not a living entity
		if (!(event.getEntity() instanceof LivingEntity))
		{
			return;
		}
		
		LivingEntity hunter = (LivingEntity) event.getEntity();
		LivingEntity hunted = event.getTarget();
		
		if (event.getReason() == TargetReason.FORGOT_TARGET
				|| event.getReason() == TargetReason.RANDOM_TARGET)
		{
			// Check for persistent mob flags. If so, force the entity to hunt a nearby player
			MobEquipment hunterEquipment = AmplifiedMobSpawner.getMobEquipmentFromEntity (hunter);
			if (hunterEquipment != null
					&& hunterEquipment.containsFlag(MobFlag.PERSISTENT_ATTACKER)
					&& hunterEquipment.containsStat(MonsterStat.HUNT_ON_SPAWN_RADIUS))
			{
				Player p = this.getNearbyPlayer(hunter, hunterEquipment.getStat(MonsterStat.HUNT_ON_SPAWN_RADIUS).intValue());
				if (p != null)
				{
					event.setTarget(p);
					event.setCancelled(false);
				}
			}
			return;
		}
		
		boolean cancelEvent = this.checkTargetEvent(hunter, hunted);
		
		if (cancelEvent)
		{
			LivingEntity newTarget = this.retargetCustomMob(hunter);
			
			event.setCancelled(newTarget == null);
			if (newTarget != null)
			{
				event.setTarget(newTarget);
			}
			hunted = newTarget;
		}
		
		// If the entity is a player, attempt to add that player
		// to the hunters's boss bar if it exists
		if (hunted != null && hunted instanceof Player)
		{
			BossBarAPI.addPlayerToBossBar((Player) hunted, hunter);
		}
	}
	
	/** If a custom mob is damaged by another entity, retarget to that entity */
	@EventHandler
	public void onDamageEvent (EntityDamageByEntityEvent event)
	{
		if (!(event.getEntity() instanceof LivingEntity)
				|| !(event.getDamager() instanceof LivingEntity)
				|| AGCraftPlugin.plugin.serverMode != ServerMode.NORMAL)
		{
			return;
		}
		
		LivingEntity entity = (LivingEntity) event.getEntity();
		MobEquipment entityEquipment = AmplifiedMobSpawner.getMobEquipmentFromEntity(entity);
		
		if (entityEquipment == null)
		{
			return;
		}
		
		LivingEntity damager = (LivingEntity) event.getDamager();
		
		if (entity instanceof Monster && 
				!entityEquipment.getIgnoreList().contains(damager.getType()))
		{
			Monster mob = (Monster) entity;
			mob.setTarget(damager);
		}
	}
	
	/** Resets custom mob targets upon chunk load events */
	@EventHandler
	public void resetTargetsOnChunkLoad (ChunkLoadEvent event)
	{
		Entity[] chunkEntities = event.getChunk().getEntities();
		
		LivingEntity livingEntity;
		MobEquipment entityEquipment;
		for (Entity entity : chunkEntities)
		{
			if (entity != null && entity instanceof LivingEntity)
			{
				livingEntity = (LivingEntity) entity;
				entityEquipment = AmplifiedMobSpawner.getMobEquipmentFromEntity(livingEntity);
				if (entityEquipment != null)
				{
					EquipmentTools.modifyBaseStats(livingEntity, entityEquipment);
					EquipmentTools.modifyPathfindingTargets(livingEntity, entityEquipment);
				}
			}
		}
	}
	
	/** Captures zombie to drowned conversion events and transfers custom metadata to
	 *  that new mob, if it exists. */
	@EventHandler
	public void transferMobDataOnDrownedConversionEvent (EntityTransformEvent event)
	{
		if (event.getTransformReason() != TransformReason.DROWNED)
		{
			return;
		}
		LivingEntity originalZombie = (LivingEntity) event.getEntity();
		MobEquipment ogZombieEquipment = AmplifiedMobSpawner.getMobEquipmentFromEntity(originalZombie);
		if (ogZombieEquipment != null)
		{
			LivingEntity drownedEntity = (LivingEntity) event.getTransformedEntity();
			EquipmentTools.setCustomMetadata(drownedEntity, ogZombieEquipment);
			EquipmentTools.modifyBaseStats(drownedEntity, ogZombieEquipment);
			EquipmentTools.modifyPathfindingTargets(drownedEntity, ogZombieEquipment);
			if (ogZombieEquipment.containsFlag(MobFlag.BOSS_BAR))
			{
				BossBarAPI.createBossBar(drownedEntity);
			}
		}
	}
	
	/** Returns true if a monster should cancel their AI targeting event based on its hitlist, ignore list, and rivaling factions
	 * 		@param hunter - The entity hunting the hunted
	 * 		@param hunted - The entity being hunted */
	private boolean checkTargetEvent (LivingEntity hunter, LivingEntity hunted)
	{
		// Null check
		if (hunted == null || hunter == null)
		{
			return false;
		}
				
		MobEquipment hunterEquipment = AmplifiedMobSpawner.getMobEquipmentFromEntity(hunter);
		MobEquipment huntedEquipment = AmplifiedMobSpawner.getMobEquipmentFromEntity(hunted);
				
		// First, check if the hunter is in the huntee's ignore list
		// This is used to avoid iron golems, snowman, and wolves from aggro allied monsters
		if (huntedEquipment != null && huntedEquipment.getIgnoreList().contains(hunter.getType()))
		{
			return true;
		}
				
		// Secondly, check if the hunted is in the hunter's ignore list. If so, cancel
		// the targeting event
		if (hunterEquipment != null && hunterEquipment.getIgnoreList().contains(hunted.getType()))
		{
			return true;
		}
		
				
		// Thirdly, check for the case where the hunter equipment is
		// active, but the hunted equipment is not. In this case,
		// the return result will be set to whatever or not the
		// hunter has the flag IGNORE_NON_FACTION_ENTITIES enabled.
		if (hunterEquipment != null && huntedEquipment == null
				&& hunted.getType() != EntityType.PLAYER)
		{
			return hunterEquipment.containsFlag(MobFlag.IGNORE_NON_FACTION_ENTITIES);
		}
		
		// Require both equipment types to be active at this point
		if (hunterEquipment == null || huntedEquipment == null)
		{
			return false;
		}
				
		// Lastly, check if the hunter has any rivaling factions and
		// the hunted is in at least one faction
		if (!hunterEquipment.getRivalFactions().isEmpty()
				&& !huntedEquipment.getFactions().isEmpty())
		{
			// Check if the hunted is in the hunter's list of rivaling factions
			if (huntedEquipment.isRivalsOf(hunterEquipment))
			{
				return false;
			}
			return true;
		}
		return hunterEquipment.containsFlag(MobFlag.IGNORE_NON_FACTION_ENTITIES);
	}
	
	/** Returns the player that is nearest to the passed entity if it exists. */
	private Player getNearbyPlayer (LivingEntity hunter, int radius)
	{
		ArrayList <Player> nearbyPlayers = ScanEntities.ScanNearbyPlayers(hunter, radius);
		if (!nearbyPlayers.isEmpty())
		{
			return nearbyPlayers.get(0);
		}
		return null;
	}
	
	/** Returns an eligible entity (based on the living entity's hit, ignore, and faction list) that is near
	 * the passed hunter. Returns null if no entity is found within a 20 block radius of the hunter. */
	private LivingEntity retargetCustomMob (LivingEntity hunter)
	{
		double scanRadius = 20.0;
		
		// If the event is canceled, tell the hunter to scan for
		// any nearby entities and hunt a nearby entity that
		// are in its hitlist and satisfies its properties.
		MobEquipment hunterEquipment = AmplifiedMobSpawner.getMobEquipmentFromEntity(hunter);
		
		if (hunterEquipment == null)
		{
			return null;
		}
		
		LivingEntity victim = null;
		MobEquipment victimEquipment;
		
		// Allow phantoms to have a larger scan radius
		if (hunter.getType() == EntityType.PHANTOM)
		{
			scanRadius *= 10;
		}
		
		List <Entity> entities = hunter.getNearbyEntities(scanRadius, scanRadius / 4.0, scanRadius);
		
		boolean foundVictim = false;
		for (Entity target : entities)
		{
			if (foundVictim)
			{
				break;
			}
			
			victim = null;
			foundVictim = false;
			
			if (!(target instanceof LivingEntity))
			{
				continue;
			}
						
			victim = (LivingEntity) target;
			// Check if the victim is in its hitlist
			if (hunterEquipment.getHitList().contains(victim.getType()) &&
				!hunterEquipment.getIgnoreList().contains(victim.getType()))
			{
				// If so, attempt to get the victim's mob equipment
				victimEquipment = AmplifiedMobSpawner.getMobEquipmentFromEntity(victim);
				if (victimEquipment != null
						&& !hunterEquipment.getRivalFactions().isEmpty()
						&& !victimEquipment.getFactions().isEmpty())
				{
					// Check if the victim's faction is in the hunter's rivaling factions
					if (victimEquipment.isRivalsOf(hunterEquipment))
					{
						foundVictim = true;
						break;
					}
				}
				else
				{
					foundVictim = !(hunterEquipment.containsFlag(MobFlag.IGNORE_NON_FACTION_ENTITIES))
							|| victim.getType() == EntityType.PLAYER;
				}
			}
		}
		
		return foundVictim ? victim : null;
	}
}
