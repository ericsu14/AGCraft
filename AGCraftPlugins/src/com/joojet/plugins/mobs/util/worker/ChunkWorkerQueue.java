package com.joojet.plugins.mobs.util.worker;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import org.bukkit.Chunk;
import org.bukkit.World;
import org.bukkit.entity.Entity;
import org.bukkit.scheduler.BukkitRunnable;

import com.joojet.plugins.agcraft.util.Pair;

/** An abstract worker pool module used to queue chunks loaded into and out of the game, 
 *  so that all entities stored on those chunks can be processed at a later time.
 *  
 *  This is because chunk-loading is done asynchronously in the 1.17 releases of Spigot,
 *  therefore making the chunk.getEntities() call unreliable. A delay is necessary
 *  to ensure that all entities are loaded before processing its entities. */
public abstract class ChunkWorkerQueue extends BukkitRunnable
{
	/** A queue used to store incoming chunks to be processed */
	protected List <ChunkData> chunkQueue;
	/** Ensures that no duplicate data is inserted */
	protected HashSet <ChunkData> duplicateChunkDataChecker;
	
	/** Creates a new instance of a chunk worker queue, allowing chunks to be safely loaded into the queue
	 *  and have all entities within that chunk to be processed on a timer.
	 *  @param timer Time between chunk processes
	 *  @param limit Amount of chunks processed per wave */
	public ChunkWorkerQueue ()
	{
		this.chunkQueue = new ArrayList <ChunkData> ();
		this.duplicateChunkDataChecker = new HashSet <ChunkData> ();
	}
	
	@Override
	public void run ()
	{
		List <ChunkData> finishedChunks = new ArrayList <ChunkData> ();
		for (int i = 0; i < chunkQueue.size(); ++i)
		{
			ChunkData chunkData = chunkQueue.get(i);
			if (chunkData.canReadChunk())
			{	
				Pair <Integer, Integer> chunkDataLocation = chunkData.getChunkCoordinates();
				/** Offloads delayed chunk loading to an async thread, since chunk loading is
				 *  done asynchronously in 1.17 */
				chunkData.getWorld().getChunkAtAsync(chunkDataLocation.getKey(), chunkDataLocation.getEntry()).thenAccept(chunk -> {
					if (chunk != null)
					{
						Entity[] chunkEntities = chunk.getEntities();
						
						for (Entity entity : chunkEntities)
						{
							processEntity (entity);
						}
					}
				});
				finishedChunks.add(chunkData);
			}
		}
		
		this.chunkQueue.removeAll(finishedChunks);
		this.duplicateChunkDataChecker.removeAll(finishedChunks);
	}
	
	/** Enqueues all loaded chunks from a list of active worlds
	 *  @param worlds A collection of worlds */
	public void loadSpawnChunks (List <World> worlds)
	{
		for (World world : worlds)
		{
			Chunk [] loadedChunks = world.getLoadedChunks();
			
			for (Chunk loadedChunk : loadedChunks)
			{
				this.enqueue(loadedChunk);
			}
		}
	}
	
	/** Enqueues a chunk into the worker queue by storing its world and <X,Z> coordinate pair
	 *  @param Chunk being enqueued */
	public void enqueue (Chunk chunk)
	{
		ChunkData newChunkData = new ChunkData (chunk);
		if (!this.duplicateChunkDataChecker.contains(newChunkData))
		{
			this.duplicateChunkDataChecker.add(newChunkData);
			this.chunkQueue.add(newChunkData);
		}
	}
	
	/** A custom function used to process an entity stored within a chunk
	 *  @param entity Entity being processed in the chunk */
	public abstract void processEntity (Entity entity);
}
