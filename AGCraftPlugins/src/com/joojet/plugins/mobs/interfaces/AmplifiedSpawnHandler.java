package com.joojet.plugins.mobs.interfaces;

import org.bukkit.block.Biome;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.entity.CreatureSpawnEvent.SpawnReason;

public interface AmplifiedSpawnHandler 
{
	/** Handles a mob spawn event caught in the Amplified Mob Spawn listener.
	 *  @param entity - The entity potentially being transformed into a custom mob
	 *  @param type - The entity'EntityType
	 *  @param reason - The reason on why this entity is spawned
	 *  @param biome - The biome in which this entity is spawned in
	 *  @param roll - The random number determining if this entity should spawn */
	public void handleSpawnEvent (LivingEntity entity, EntityType type, SpawnReason reason, Biome biome, double roll);
	
	/** Returns true if the passed spawn reason agrees with the set filters */
	public boolean reasonFilter (SpawnReason reason);
}
