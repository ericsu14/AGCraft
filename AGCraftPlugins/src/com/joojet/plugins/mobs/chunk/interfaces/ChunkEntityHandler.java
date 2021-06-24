package com.joojet.plugins.mobs.chunk.interfaces;

import org.bukkit.entity.Entity;

/** Classes who implements this interface are able to process entities who are recently loaded
 *  into a chunk load event. */
public interface ChunkEntityHandler 
{
	/** Processes an entity when a chunk loads into a world.
	 *  @param entity Entity being processed */
	public void processEntityOnChunkLoad (Entity entity);
}
