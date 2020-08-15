package com.joojet.plugins.mobs;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.NamespacedKey;
import org.bukkit.block.Biome;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Mob;
import org.bukkit.entity.Player;
import org.bukkit.entity.WanderingTrader;
import org.bukkit.entity.Wolf;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.event.entity.CreatureSpawnEvent.SpawnReason;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityTargetEvent.TargetReason;
import org.bukkit.event.entity.EntityTargetLivingEntityEvent;
import org.bukkit.event.world.ChunkLoadEvent;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.plugin.Plugin;

import com.joojet.plugins.agcraft.main.AGCraftPlugin;
import com.joojet.plugins.mobs.enums.Faction;
import com.joojet.plugins.mobs.interpreter.MonsterTypeInterpreter;
import com.joojet.plugins.mobs.metadata.MonsterTypeMetadata;
import com.joojet.plugins.mobs.monsters.MobEquipment;
import com.joojet.plugins.mobs.spawnhandlers.AmplifiedMobHandler;
import com.joojet.plugins.mobs.spawnhandlers.BeatTheBruinsHandler;
import com.joojet.plugins.mobs.spawnhandlers.JulyFourthHandler;
import com.joojet.plugins.mobs.spawnhandlers.UHCHandler;
import com.joojet.plugins.mobs.util.EquipmentTools;
import com.joojet.plugins.mobs.villager.VillagerEquipment;
import com.joojet.plugins.mobs.villager.wandering.WanderingVillagerTypes;
import com.joojet.plugins.warp.scantools.ScanEntities;

public class AmplifiedMobSpawner implements Listener 
{	
	/** Key used to reference the Amplified mob spawner's spawn chance */
	public final static String spawnChanceKey = "amplified-spawn-chance";
	
	/** Key used to reference the amplified mob spawner's debug mode */
	public final static String debugModeKey = "amplified-debug-mode";
	
	/** Search trie used to lookup custom monsters by name */
	public static MonsterTypeInterpreter mobTable = new MonsterTypeInterpreter ();
	
	/** Used to generate random numbers */
	private Random rand = new Random ();
	
	/** Mob equipment factories */
	private WanderingVillagerTypes wanderingTypes;
	
	/** A list of spawn handlers for custom events */
	private JulyFourthHandler julyFourthHandler;
	private UHCHandler uhcHandler;
	private AmplifiedMobHandler amplifiedMobHandler;
	private BeatTheBruinsHandler bruinHandler;
	
	/** Creates a new instance of this mob spawner class,
	 *  which adds listeners to Minecraft's mob spawn events for
	 *  having a certain chance of equipping them with custom armor, buffs, and weapons. */
	public AmplifiedMobSpawner ()
	{
		this.wanderingTypes = new WanderingVillagerTypes();
		this.julyFourthHandler = new JulyFourthHandler ();
		this.amplifiedMobHandler = new AmplifiedMobHandler();
		this.bruinHandler = new BeatTheBruinsHandler ();
	}
	
	public void onEnable ()
	{
		Bukkit.getPluginManager().registerEvents(this, (Plugin) this);
	}
	
	/** For debugging purposes */
	@EventHandler
	public void showDamageInfo (EntityDamageByEntityEvent event)
	{
		if (!AGCraftPlugin.plugin.enableDebugMode)
		{
			return;
		}
		
		if (event.getDamager() instanceof Player)
		{
			Player p = (Player) event.getDamager();
			LivingEntity e = (LivingEntity) event.getEntity();
			p.sendMessage("Dealt " + event.getDamage() + " damage.");
			p.sendMessage("The enemy has " + (e.getHealth() - event.getFinalDamage()) + " health remaining");
			
			// Test metadata
			NamespacedKey key = MonsterTypeMetadata.generateGenericNamespacedKey();
			if (!e.getPersistentDataContainer().isEmpty() && 
					e.getPersistentDataContainer().has(key, PersistentDataType.STRING))
			{
				p.sendMessage("Custom name: " + e.getPersistentDataContainer().get(key, PersistentDataType.STRING));
			}
		}
		
		if (event.getDamager() instanceof Arrow)
		{
			Arrow arr = (Arrow) event.getDamager();
			if (arr.getShooter() instanceof Player && event.getEntity() instanceof LivingEntity)
			{
				Player p = (Player) arr.getShooter();
				LivingEntity e = (LivingEntity) event.getEntity();
				p.sendMessage("Dealt " + event.getDamage() + " damage.");
				p.sendMessage("The enemy has " + (e.getHealth() - event.getFinalDamage()) + " health remaining");
			}
		}
		
		if (event.getDamager() instanceof Wolf)
		{
			ArrayList <Player> players = ScanEntities.ScanNearbyPlayers((LivingEntity) event.getDamager(), 50);
			for (Player p : players)
			{
				p.sendMessage(event.getDamager().getName() + " dealt " + event.getDamage() + " damage");
			}
		}
		
		if (event.getEntity() instanceof Player)
		{
			Player p = (Player) event.getEntity();
			p.sendMessage("Taken " + event.getDamage() + " damage.");
		}
	}
	
	/** Listens to AI target events and cancels targeting events based on certain conditions */
	@EventHandler
	public void onTargetEvent (EntityTargetLivingEntityEvent event)
	{
		// Do nothing if entity is not a living entity
		if (!(event.getEntity() instanceof LivingEntity))
		{
			return;
		}
		
		// Do nothing if the targeting event is set by plugin
		if (event.getReason() == TargetReason.CUSTOM
				|| event.getReason() == TargetReason.FORGOT_TARGET)
		{
			return;
		}
		
		LivingEntity hunter = (LivingEntity) event.getEntity();
		LivingEntity hunted = event.getTarget();
		
		boolean cancelEvent = this.checkTargetEvent(hunter, hunted);
		
		if (cancelEvent)
		{
			event.setCancelled(true);
			this.retargetCustomMob(hunter);
		}
	}
	
	/** If a custom mob is damaged by another entity, retarget to that entity */
	@EventHandler
	public void onDamageEvent (EntityDamageByEntityEvent event)
	{
		if (!(event.getEntity() instanceof LivingEntity)
				|| !(event.getDamager() instanceof LivingEntity))
		{
			return;
		}
		
		LivingEntity entity = (LivingEntity) event.getEntity();
		MobEquipment entityEquipment = getMobEquipmentFromEntity(entity);
		
		if (entityEquipment == null)
		{
			return;
		}
		
		LivingEntity damager = (LivingEntity) event.getDamager();
		
		if (entity instanceof Mob && 
				!entityEquipment.getIgnoreList().contains(damager.getType()))
		{
			Mob mob = (Mob) entity;
			mob.setTarget(damager);
		}
	}
	
	/** Resets custom mob targets upon chunk load events */
	@EventHandler
	public void resetTargetsonChunkLoad (ChunkLoadEvent event)
	{
		Entity[] chunkEntities = event.getChunk().getEntities();
		
		LivingEntity livingEntity;
		MobEquipment entityEquipment;
		for (Entity entity : chunkEntities)
		{
			if (entity != null && entity instanceof LivingEntity)
			{
				livingEntity = (LivingEntity) entity;
				entityEquipment = getMobEquipmentFromEntity(livingEntity);
				if (entityEquipment != null)
				{
					EquipmentTools.modifyPathfindingTargets(livingEntity, entityEquipment);
				}
			}
		}
	}
	
	/** Amplifies mob spawns */
	@EventHandler
	public void onEntitySpawn (CreatureSpawnEvent event)
	{
		
		EntityType type = event.getEntityType();
		SpawnReason reason = event.getSpawnReason();
		LivingEntity entity = event.getEntity();
		Biome biome = entity.getLocation().getBlock().getBiome();
		
		double roll = rand.nextDouble();
		
		// Handles Server Mode mob spawns
		switch (AGCraftPlugin.plugin.serverMode)
		{
			case UHC:
				this.uhcHandler.handleSpawnEvent(entity, type, reason, biome, roll);
				return;
			case MINIGAME:
				return;
			default:
				break;
		}
		
		// Handles server wide event mob spawns
		switch (AGCraftPlugin.plugin.serverEventMode)
		{
			case JULY_FOURTH:
				this.julyFourthHandler.handleSpawnEvent(entity, type, reason, biome, roll);
				break;
			case BEAT_THE_BRUINS:
				this.bruinHandler.handleSpawnEvent(entity, type, reason, biome, roll);
				break;
			default:
				break;
		}
		
		// If the entity is a wandering trader, transform him
		if (type.equals(EntityType.WANDERING_TRADER))
		{
			this.transformWanderingTrader(entity, biome);
			return;
		}
		
		// Switch to raider handler if the spawn reason is RAID
		if (reason.equals(SpawnReason.RAID))
		{
			this.makeRaiderNameVisible(entity, type);
			return;
		}
		
		// Handles normal spawn events
		this.amplifiedMobHandler.handleSpawnEvent(entity, type, reason, biome, roll);
	}
	
	/** Makes the names of raider mobs visible */
	public void makeRaiderNameVisible (LivingEntity entity, EntityType type)
	{
		StringBuilder name = new StringBuilder (type.name().toLowerCase());
		name.replace(0, 1, type.name().toUpperCase().substring(0,1));
		name.append(" Raider");
		entity.setCustomName(ChatColor.RED + name.toString());
		entity.setCustomNameVisible(true);
	}
	
	/** Transforms a wandering trader into Frolf */
	public void transformWanderingTrader (LivingEntity entity, Biome biome)
	{
		WanderingTrader trader = (WanderingTrader) entity;
		VillagerEquipment equipment = (VillagerEquipment) wanderingTypes.getRandomEquipment(biome);
		trader.setRecipes(equipment.getRecipes());
		EquipmentTools.equipEntity(trader, (MobEquipment) equipment);
	}
	
	/** Finds and returns a LivingEntity's custom mob equipment object.
	 *  Returns null if the entity does not have custom mob metadata.
	 *  @param entity - The living entity where we are extracting its custom mob equipment from */
	public static MobEquipment getMobEquipmentFromEntity (LivingEntity entity)
	{
		// First check if the entity has custom mob metadata
		PersistentDataContainer metadata = entity.getPersistentDataContainer();
		NamespacedKey key = MonsterTypeMetadata.generateGenericNamespacedKey();
		if (entity == null ||
				metadata.isEmpty() ||
				!metadata.has(key, PersistentDataType.STRING))
		{
			return null;
		}
		
		// Extract custom metadata from the entity and use its string to lookup its own mob equipment
		String entityMeta = metadata.get(key, PersistentDataType.STRING);
		MobEquipment entityEquipment = mobTable.searchTrie(entityMeta);
		
		return entityEquipment;
	}
	
	/** Returns true if a monster should cancel their AI targeting event based on its hitlist, ignore list, and rivaling factions
	 * 		@param hunter - The entity hunting the hunted
	 * 		@param hunted - The entity being hu */
	private boolean checkTargetEvent (LivingEntity hunter, LivingEntity hunted)
	{
		// Null check
		if (hunted == null || hunter == null)
		{
			return false;
		}
				
		MobEquipment hunterEquipment = getMobEquipmentFromEntity(hunter);
		MobEquipment huntedEquipment = getMobEquipmentFromEntity(hunted);
				
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
				
		// Require both hunted and hunter equipment to be active
		if (hunterEquipment == null || huntedEquipment == null)
		{
			return false;
		}
		
		// Return true if the hunter and hunted is in the same faction
		
				
		// Lastly, check if the hunter has any rivaling factions and
		// the hunted is in at least one faction
		if (!hunterEquipment.getRivalFactions().isEmpty()
				&& !huntedEquipment.getFactions().isEmpty())
		{
			// Cancel the target event if the hunted is not in the hunter's rivaling factions
			HashSet <Faction> huntedFactions = huntedEquipment.getFactions();
			for (Faction faction : huntedFactions)
			{
				if (hunterEquipment.getRivalFactions().contains(faction))
				{
					return false;
				}
			}
			return true;
		}
		return false;
	}
	
	/** Forcefully causes a custom monster to retarget another eligible mob based on its properties */
	private void retargetCustomMob (LivingEntity hunter)
	{
		double scanRadius = 20.0;
		
		// If the event is canceled, tell the hunter to scan for
		// any nearby entities and hunt a nearby entity that
		// are in its hitlist and satisfies its properties.
		MobEquipment hunterEquipment = getMobEquipmentFromEntity(hunter);
		
		if (hunterEquipment == null)
		{
			return;
		}
		
		LivingEntity victim = null;
		MobEquipment victimEquipment;
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
				victimEquipment = getMobEquipmentFromEntity(victim);
				if (victimEquipment != null
						&& !hunterEquipment.getRivalFactions().isEmpty()
						&& !victimEquipment.getFactions().isEmpty())
				{
					// Check if the victim's faction is in the hunter's rivaling factions
					HashSet <Faction> victimFactions = victimEquipment.getFactions();
					for (Faction victimFaction : victimFactions)
					{
						if (hunterEquipment.getRivalFactions().contains(victimFaction))
						{
							foundVictim = true;
							break;
						}
					}
				}
				else
				{
					foundVictim = true;
				}
			}
		}
		
		// Retargets the monster to another eligible mob, if it exists.
		if (foundVictim && victim != null)
		{
			Mob hunterMob = (Mob) hunter;
			hunterMob.setTarget(victim);
		}
	}
}
