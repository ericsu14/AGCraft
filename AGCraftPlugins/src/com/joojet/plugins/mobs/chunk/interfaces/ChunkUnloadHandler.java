package com.joojet.plugins.mobs.chunk.interfaces;

import org.bukkit.entity.Entity;

/** Classes who implements this interface are able to process entities who are recently unloaded
 *  into a chunk unload event. */
public interface ChunkUnloadHandler 
{
	/** Processes an entity apart of a chunk when a chunk unloads from the world.
	 *  @param entity Entity being processed */
	public void processEntityOnChunkUnload (Entity entity);
}
