package com.joojet.plugins.mobs.util.worker;

import org.bukkit.Chunk;
import org.bukkit.World;

import com.joojet.plugins.agcraft.util.Pair;

public class ChunkData 
{
	/** Stored chunk */
	protected Chunk chunk;
	/** World in which the chunk resides */
	protected World world;
	/** <X, Z> coordinate pair for the chunk */
	protected Pair <Integer, Integer> coordinates;
	/** Forces the worker queue to check the chunk at a delayed rate */
	protected boolean isDelayed;
	
	/** Wraps a chunk instance allowing the worker queue to delay its processing */
	public ChunkData (Chunk chunk)
	{
		this.chunk = chunk;
		this.world = chunk.getWorld();
		this.coordinates = new Pair <Integer, Integer> (chunk.getX(), chunk.getZ());
		this.isDelayed = false;
	}
	
	/** Checks if the chunk data can be read on this current wave and marks it as readable
	 *  if it is not already yet. */
	public boolean canReadChunk ()
	{
		boolean result = this.isDelayed;
		if (this.chunk.isLoaded())
		{
			this.isDelayed = true;
		}
		return result;
	}
	
	public Chunk getChunk ()
	{
		return this.chunk;
	}
	
}
