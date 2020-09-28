package com.joojet.plugins.mobs.spawnhandlers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

import org.bukkit.block.Biome;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.entity.CreatureSpawnEvent.SpawnReason;

import com.joojet.plugins.agcraft.main.AGCraftPlugin;
import com.joojet.plugins.mobs.monsters.MobEquipment;
import com.joojet.plugins.mobs.monsters.MonsterTypes;
import com.joojet.plugins.mobs.spawnhandlers.task.HandleSpawnEventTask;

public abstract class AbstractSpawnHandler 
{
	/** Used to control mob spawns based on the passed spawn reason.
	 *  Thus, mobs can only be converted into custom mobs if its given spawn
	 *  reason exists in this set */
	protected HashSet <SpawnReason> spawnReasonFilter;
	/** Stores a hash table of custom Monster type instances, where its key is the entity type
	 *  that class supports. */
	protected HashMap <EntityType, MonsterTypes> mobEquipmentTable;
	
	/** Creates a new instance of an Abstract Spawn Handler */
	public AbstractSpawnHandler ()
	{
		this.spawnReasonFilter = new HashSet <SpawnReason> ();
		this.mobEquipmentTable = new HashMap <EntityType, MonsterTypes> ();
	}
	
	/** Offloads the handleSpawnEvent task to Bukkit's scheduler. This ensures that our custom entity modification code 
	 *  runs on the next tick, which should decrease the chances of the server throwing a ConcurrentModification exception.
	 *  @param entity - The entity potentially being transformed into a custom mob
	 *  @param type - The entity's EntityType
	 *  @param reason - The reason on why this entity is spawned
	 *  @param biome - The biome in which this entity is spawned in
	 *  @param roll - A random number determining if this entity should spawn */
	public void createSpawnEventHandlerTask (LivingEntity entity, EntityType type, SpawnReason reason, Biome biome, double roll)
	{
		new HandleSpawnEventTask(this, entity, type, reason, biome, roll).runTask(AGCraftPlugin.plugin);
	}
	
	/** Handles a mob spawn event caught in the Amplified Mob Spawn listener.
	 *  @param entity - The entity potentially being transformed into a custom mob
	 *  @param type - The entity's EntityType
	 *  @param reason - The reason on why this entity is spawned
	 *  @param biome - The biome in which this entity is spawned in
	 *  @param roll - A random number determining if this entity should spawn */
	public abstract void handleSpawnEvent (LivingEntity entity, EntityType type, SpawnReason reason, Biome biome, double roll);
	
	
	/** Adds a list of spawn reasons into the internal spawn reason filter */
	public void addSpawnReasons (SpawnReason... reasons)
	{
		for (SpawnReason reason : reasons)
		{
			this.spawnReasonFilter.add(reason);
		}
	}
	
	/** Adds mob equipment to the mob equipment table */
	public void addMonsterTypes (MonsterTypes... mobTypes)
	{
		for (MonsterTypes mobType : mobTypes)
		{
			ArrayList <EntityType> supportedEntities = mobType.getSupportedEntities();
			for (EntityType entity : supportedEntities)
			{
				this.mobEquipmentTable.put(entity, mobType);
			}
		}
	}
	
	/** Gets random mob equipment from the mob equipment table
	 *  @param type - Monster's entity type
	 *  @param biome - The biome the original entity spawns in */
	public MobEquipment getRandomEqipment (EntityType type, Biome biome)
	{
		MobEquipment result = null;
		MonsterTypes mobTypes = this.mobEquipmentTable.get(type);
		if (mobTypes != null)
		{
			result = mobTypes.getRandomEquipment(biome);
		}
		return result;
	}
	
	/** Returns true if the passed spawn reason exists in our spawn reason filter.
	 *  @param reason - Spawn Reason being checked */
	public boolean reasonFilter (SpawnReason reason)
	{
		return spawnReasonFilter.contains(reason);
	}
}
