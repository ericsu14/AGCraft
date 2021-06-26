package com.joojet.plugins.mobs.chunk.data;

import org.bukkit.Chunk;
import org.bukkit.World;

public class UnloadedChunkData extends ChunkData 
{
	public UnloadedChunkData(Chunk chunk) 
	{
		super(chunk);
	}
	
	public UnloadedChunkData (int x, int z, World world, int currentLevel)
	{
		super (x, z, world, currentLevel);
	}
	
	@Override
	public boolean canReadChunk ()
	{
		boolean result = this.isDelayed && !this.world.isChunkLoaded(this.coordinates.getKey(), this.coordinates.getEntry());
		this.isDelayed = true;
		return result;
	}

}
