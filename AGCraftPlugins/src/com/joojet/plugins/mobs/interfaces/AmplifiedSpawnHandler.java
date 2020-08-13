package com.joojet.plugins.mobs.interfaces;

import java.util.HashSet;

import org.bukkit.block.Biome;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.entity.CreatureSpawnEvent.SpawnReason;

public abstract class AmplifiedSpawnHandler 
{
	/** Used to control mob spawns based on the passed spawn reason.
	 *  Thus, mobs can only be converted into custom mobs if its given spawn
	 *  reason exists in this set */
	
	protected HashSet <SpawnReason> spawnReasonFilter;
	/** Handles a mob spawn event caught in the Amplified Mob Spawn listener.
	 *  @param entity - The entity potentially being transformed into a custom mob
	 *  @param type - The entity'EntityType
	 *  @param reason - The reason on why this entity is spawned
	 *  @param biome - The biome in which this entity is spawned in
	 *  @param roll - The random number determining if this entity should spawn */
	public abstract void handleSpawnEvent (LivingEntity entity, EntityType type, SpawnReason reason, Biome biome, double roll);
	
	
	/** Adds a list of spawn reasons into the internal spawn reason filter */
	public void addSpawnReasons (SpawnReason... reasons)
	{
		for (SpawnReason reason : reasons)
		{
			this.spawnReasonFilter.add(reason);
		}
	}
	
	/** Returns true if the passed spawn reason exists in our spawn reason filter.
	 *  @param reason - Spawn Reason being checked */
	public boolean reasonFilter (SpawnReason reason)
	{
		return spawnReasonFilter.contains(reason);
	}
}
