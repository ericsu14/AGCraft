package com.joojet.plugins.mobs;

import java.util.List;
import java.util.stream.Collectors;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Monster;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityTargetEvent;
import org.bukkit.event.entity.EntityTargetLivingEntityEvent;
import org.bukkit.event.entity.EntityTransformEvent;
import org.bukkit.event.entity.EntityTargetEvent.TargetReason;
import org.bukkit.event.entity.EntityTransformEvent.TransformReason;
import org.bukkit.event.world.ChunkLoadEvent;
import org.bukkit.metadata.FixedMetadataValue;

import com.joojet.plugins.agcraft.config.ServerConfigFile;
import com.joojet.plugins.agcraft.enums.ServerMode;
import com.joojet.plugins.agcraft.interfaces.AGListener;
import com.joojet.plugins.agcraft.main.AGCraftPlugin;
import com.joojet.plugins.mobs.bossbar.BossBarController;
import com.joojet.plugins.mobs.enums.MobFlag;
import com.joojet.plugins.mobs.enums.MonsterStat;
import com.joojet.plugins.mobs.event.CreatedCustomMonsterEvent;
import com.joojet.plugins.mobs.event.InjectCustomGoalsToEntityEvent;
import com.joojet.plugins.mobs.interpreter.MonsterTypeInterpreter;
import com.joojet.plugins.mobs.metadata.IgnorePlayerMetadata;
import com.joojet.plugins.mobs.monsters.MobEquipment;
import com.joojet.plugins.mobs.util.EquipmentTools;
import com.joojet.plugins.mobs.util.stream.ClosestProximity;
import com.joojet.plugins.mobs.util.worker.ChunkWorkerQueue;
import com.joojet.plugins.warp.scantools.ScanEntities;

public class PathfindTargetingEventListener extends AGListener
{
	/** Key used to distinguish entities who have already been injected with pathfinding goals */
	public final String pathfindingGoalKey = "ag-pathfinding-goals";
	/** Search trie used to lookup custom monsters by name */
	protected MonsterTypeInterpreter monsterTypeInterpreter;
	/** Stores a reference to the boss bar controller defined in main */
	protected BossBarController bossBarController;
	/** A chunk pool used to initialize pathfinding targets upon chunk loads */
	protected ChunkWorkerQueue pathfinderWorker;
	/** Stores time between async entity processing */
	protected int asyncLoadChunkDelay;
	
	public PathfindTargetingEventListener (MonsterTypeInterpreter monsterTypeInterpreter, BossBarController bossBarController)
	{
		this.monsterTypeInterpreter = monsterTypeInterpreter;
		this.bossBarController = bossBarController;
		this.asyncLoadChunkDelay = 10;
		
		// Allows entities loaded into the world to have their defined custom pathfinding behavior
		this.pathfinderWorker = new ChunkWorkerQueue () 
		{
			@Override
			public void processEntity(Entity entity) 
			{
				injectPathfindingGoals (entity);
			}
		};
	}
	
	@Override
	public void onEnable ()
	{
		this.pathfinderWorker.loadSpawnChunks(Bukkit.getWorlds());
		this.pathfinderWorker.runTaskTimer(AGCraftPlugin.plugin, 20, this.asyncLoadChunkDelay);
	}
	
	@Override
	public void onDisable() 
	{
		this.pathfinderWorker.cancel();
	}
	
	/** Listens to any AI target event and propagates the entity in question to our custom mob goals handler
	 *  if they are not loaded for them yet.  */
	@EventHandler
	public void onTargetChangeEvent (EntityTargetEvent targetEvent)
	{
		Entity entity = targetEvent.getEntity();
		if (!entity.hasMetadata(pathfindingGoalKey))
		{
			Bukkit.getPluginManager().callEvent(new InjectCustomGoalsToEntityEvent (entity));
		}
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
				|| event.getReason() == TargetReason.RANDOM_TARGET
				|| event.getReason() == TargetReason.REINFORCEMENT_TARGET)
		{
			// Check for persistent mob flags. If so, force the entity to hunt a nearby player
			MobEquipment hunterEquipment = this.monsterTypeInterpreter.getMobEquipmentFromEntity (hunter);
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
			this.bossBarController.addPlayerToBossBar((Player) hunted, hunter);
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
		MobEquipment entityEquipment = this.monsterTypeInterpreter.getMobEquipmentFromEntity(entity);
		
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
	@EventHandler(priority = EventPriority.HIGH)
	public void resetTargetsOnChunkLoad (ChunkLoadEvent event)
	{
		this.pathfinderWorker.enqueue(event.getChunk());
	}
	
	/** Captures zombie to drowned conversion events and transfers custom metadata to
	 *  that new mob, if it exists. */
	@EventHandler(priority = EventPriority.HIGH)
	public void transferMobDataOnDrownedConversionEvent (EntityTransformEvent event)
	{
		// Cancels lightning strike transformations, otherwise skill-based lightning attacks will have unintended side effects.
		if (event.getTransformReason() == TransformReason.LIGHTNING)
		{
			event.setCancelled(true);
			return;
		}
		
		if (event.getTransformReason() == TransformReason.CURED)
		{
			return;
		}
		
		LivingEntity originalZombie = (LivingEntity) event.getEntity();
		MobEquipment ogZombieEquipment = this.monsterTypeInterpreter.getMobEquipmentFromEntity(originalZombie);
		if (ogZombieEquipment != null)
		{
			LivingEntity drownedEntity = (LivingEntity) event.getTransformedEntity();
			EquipmentTools.setCustomMetadata(drownedEntity, ogZombieEquipment);
			EquipmentTools.modifyBaseStats(drownedEntity, ogZombieEquipment);
			EquipmentTools.modifyPathfindingTargets(drownedEntity, ogZombieEquipment);
			if (ogZombieEquipment.containsFlag(MobFlag.BOSS_BAR))
			{
				this.bossBarController.createBossBar(drownedEntity);
			}
		}
	}
	
	/** Captures entity spawn events and prevents them from targeting allied mobs if they are spawned as part of
	 *  zombie reinforcement event */
	@EventHandler
	public void handleReinforcementMobSpawn (CreatedCustomMonsterEvent event)
	{
		if (event.getEntity() instanceof Monster)
		{
			Monster hunter = (Monster) event.getEntity();
			
			// No action needed if the hunter isn't automatically targeting a mob
			if (hunter.getTarget() == null)
			{
				return;
			}
			
			LivingEntity hunted = hunter.getTarget();
			MobEquipment hunterEquipment = this.monsterTypeInterpreter.getMobEquipmentFromEntity(hunter);
			MobEquipment huntedEquipment = this.monsterTypeInterpreter.getMobEquipmentFromEntity(hunted);
			
			if (hunterEquipment != null)
			{
				hunter.setTarget(hunterEquipment.isAlliesOf(hunter, hunted, huntedEquipment) ? null : hunted);
			}
			else if (huntedEquipment != null)
			{
				hunter.setTarget(huntedEquipment.isAlliesOf(hunted, hunter, hunterEquipment) ? null : hunted);
			}
		}
	}
	
	/** Captures inject custom goal events and injects pathfinding goals into the entity if haven't already  */
	@EventHandler
	public void handlePathfindingGoalInjection (InjectCustomGoalsToEntityEvent event)
	{
		Entity entity = event.getEntity();
		if (entity != null && !entity.hasMetadata(this.pathfindingGoalKey))
		{
			this.injectPathfindingGoals(entity);
		}
	}
	
	/** Injects pathfinding goals into the entity if they do not exist
	 *  @param entity Entity receiving pathfinding goals if it is a custom monster */
	public void injectPathfindingGoals (Entity entity)
	{
		LivingEntity livingEntity;
		MobEquipment entityEquipment;
		if (entity != null && entity instanceof LivingEntity)
		{
			livingEntity = (LivingEntity) entity;
			entityEquipment = monsterTypeInterpreter.getMobEquipmentFromEntity(livingEntity);
			if (entityEquipment != null)
			{
				EquipmentTools.modifyBaseStats(livingEntity, entityEquipment);
				EquipmentTools.modifyPathfindingTargets(livingEntity, entityEquipment);
				livingEntity.setMetadata(this.pathfindingGoalKey, new FixedMetadataValue (AGCraftPlugin.plugin, this.pathfindingGoalKey));
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
		
		/** Ignore players who are in spectator / creative mode */
		if (this.isSpectator(hunted))
		{
			return true;
		}
				
		MobEquipment hunterEquipment = this.monsterTypeInterpreter.getMobEquipmentFromEntity(hunter);
		MobEquipment huntedEquipment = this.monsterTypeInterpreter.getMobEquipmentFromEntity(hunted);
		
		// Checks if the hunter or hunted is allies of each other. Otherwise, return false
		if (hunterEquipment != null)
		{
			return hunterEquipment.isAlliesOf(hunter, hunted, huntedEquipment);
		}
		return (huntedEquipment != null) ? huntedEquipment.isAlliesOf(hunted, hunter, hunterEquipment) : this.ignorePlayerWithMetadata (hunted);
	}
	
	/** Returns the player that is nearest to the passed entity if it exists. */
	private Player getNearbyPlayer (LivingEntity hunter, int radius)
	{
		List <Player> players = ScanEntities.ScanNearbyPlayers(hunter, radius);
		players.sort(new ClosestProximity (hunter));
		// Filter out players that are spectators
		for (Player player : players)
		{
			if (!this.isSpectator(player))
			{
				return player;
			}
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
		MobEquipment hunterEquipment = this.monsterTypeInterpreter.getMobEquipmentFromEntity(hunter);
		
		if (hunterEquipment == null)
		{
			return null;
		}
		
		// Allow phantoms to have a larger scan radius
		if (hunter.getType() == EntityType.PHANTOM)
		{
			scanRadius *= 10;
		}
		
		List <LivingEntity> entities = hunter.getWorld().getNearbyEntities(hunter.getLocation(), scanRadius, scanRadius / 4.0, scanRadius).stream().
				filter(ent -> (ent instanceof LivingEntity) && hunter.hasLineOfSight(ent) && 
						!isSpectator ((LivingEntity) ent)).
				map(ent -> (LivingEntity) ent).
				filter (other -> {
					MobEquipment otherEquipment = monsterTypeInterpreter.getMobEquipmentFromEntity(other);
					return !hunterEquipment.isAlliesOf(hunter, other, otherEquipment);
				}).
				sorted (new ClosestProximity (hunter)).
				collect(Collectors.toList());
		
		return entities.isEmpty() ? null : entities.get(0);
	}
	
	/** Returns true if the living entity is a player with an active
	 *  mob ignore player metadata. */
	private boolean ignorePlayerWithMetadata (LivingEntity entity)
	{
		if (entity instanceof Player)
		{
			Player player = (Player) entity;
			return new IgnorePlayerMetadata().canIgnorePlayer(player);
		}
		return false;
	}
	
	/** Returns true if the player is either in spectator mode or a player with flying privileges (such as creative mode)
	 *  @param player The player entity being checked */
	private boolean isSpectator (LivingEntity player)
	{
		return (player instanceof Player) && (((Player)player).getAllowFlight() ||
				((Player)player).getGameMode() == GameMode.SPECTATOR);
	}

	@Override
	public void loadConfigVariables(ServerConfigFile config) 
	{
		// TODO
	}
}
