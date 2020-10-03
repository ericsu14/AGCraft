package com.joojet.plugins.mobs.spawnhandlers.task;

import org.bukkit.block.Biome;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.entity.CreatureSpawnEvent.SpawnReason;
import org.bukkit.scheduler.BukkitRunnable;

import com.joojet.plugins.mobs.spawnhandlers.AbstractSpawnHandler;

public class HandleSpawnEventTask extends BukkitRunnable {
	
	/** Stores the spawn handler instance */
	protected AbstractSpawnHandler handler;
	/** The entity potentially being transformed into a custom mob */
	LivingEntity entity;
	/** The entity's EntityType */
	EntityType type;
	/** The reason on why this entity is spawned */
	SpawnReason reason;
	/** The biome in which this entity is spawned in */
	Biome biome;
	
	/** Creates a new Spawn Event handler task, which calls the passed spawnhandler's handleSpawnEvent function
	 *  on the entity in the next tick.
	 *  @param handler - A reference to the spawn event handler we are using
	 *  @param entity - The entity potentially being transformed into a custom mob
	 *  @param type - The entity's EntityType
	 *  @param reason - The reason on why this entity is spawned
	 *  @param biome - The biome in which this entity is spawned in
	 *  @param roll - A random number determining if this entity should spawn */
	public HandleSpawnEventTask (AbstractSpawnHandler handler, LivingEntity entity, EntityType type, SpawnReason reason, Biome biome)
	{
		this.handler = handler;
		this.entity = entity;
		this.type = type;
		this.reason = reason;
		this.biome = biome;
	}
	
	/** Calls the spawn handler's handleSpawnEvent function to transform the monster into a custom mob. */
	@Override
	public void run() 
	{
		this.handler.handleSpawnEvent(entity, type, reason, biome);
		cancel();
	}

}
