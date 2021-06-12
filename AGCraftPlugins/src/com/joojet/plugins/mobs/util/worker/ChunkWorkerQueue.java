package com.joojet.plugins.mobs.util.worker;

import java.util.LinkedList;
import java.util.Queue;

import org.bukkit.Chunk;
import org.bukkit.entity.Entity;
import org.bukkit.scheduler.BukkitRunnable;

import com.joojet.plugins.agcraft.main.AGCraftPlugin;

/** An abstract worker pool module used to queue chunks loaded into and out of the game, 
 *  so that all entities within these chunks can be processed on a timer.  */
public abstract class ChunkWorkerQueue 
{
	/** Time before the next wave of chunks are processed */
	protected int timer;
	/** Limits the amount of chunks processed per wave */
	protected int limit;
	/** A queue used to store incoming chunks to be processed */
	protected Queue <ChunkData> chunkQueue;
	
	/** Creates a new instance of a chunk worker queue, allowing chunks to be safely loaded into the queue
	 *  and have all entities within that chunk to be processed on a timer.
	 *  @param timer Time between chunk processes
	 *  @param limit Amount of chunks processed per wave */
	public ChunkWorkerQueue (int timer, int limit)
	{
		this.timer = timer;
		this.limit = limit;
		this.chunkQueue = new LinkedList <ChunkData> ();
		
		new BukkitRunnable () 
		{
			@Override
			public void run() 
			{
				while (!chunkQueue.isEmpty())
				{
					ChunkData chunkData = chunkQueue.poll();
					Chunk processedChunk = chunkData.getChunk();
					if (processedChunk != null)
					{
						Entity [] chunkEntities = processedChunk.getEntities();
						for (Entity entity : chunkEntities)
						{
							processEntity (entity);
						}
					}
				}
			}
			
		}.runTaskTimer(AGCraftPlugin.plugin, 20, this.timer);
	}
	
	/** Enqueues a chunk into the worker queue by storing its world and <X,Z> coordinate pair
	 *  @param Chunk being enqueued */
	public void enqueue (Chunk chunk)
	{
		this.chunkQueue.add(new ChunkData (chunk));
	}
	
	/** A custom function used to  process an entity stored within a chunk
	 *  @param entity Entity being processed in the chunk */
	public abstract void processEntity (Entity entity);
}
