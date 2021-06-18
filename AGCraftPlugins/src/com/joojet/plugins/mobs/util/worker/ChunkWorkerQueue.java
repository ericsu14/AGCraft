package com.joojet.plugins.mobs.util.worker;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.bukkit.Chunk;
import org.bukkit.entity.Entity;
import org.bukkit.scheduler.BukkitRunnable;

import com.joojet.plugins.agcraft.asynctasks.AsyncDatabaseTask;

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
	
	/** Creates a new instance of a chunk worker queue, allowing chunks to be safely loaded into the queue
	 *  and have all entities within that chunk to be processed on a timer.
	 *  @param timer Time between chunk processes
	 *  @param limit Amount of chunks processed per wave */
	public ChunkWorkerQueue ()
	{
		this.chunkQueue = new ArrayList <ChunkData> ();
	}
	
	@Override
	public void run ()
	{
		for (int i = 0; i < chunkQueue.size(); ++i)
		{
			ChunkData chunkData = chunkQueue.get(i);
			if (chunkData.canReadChunk())
			{	
				new AsyncDatabaseTask <Chunk> () 
				{
					/** Offloads delayed chunk loading to an async thread, since chunk loading is
					 *  done asynchronously in 1.17 */
					@Override
					protected Chunk getDataFromDatabase() throws SQLException 
					{
						return chunkData.getChunk();
					}
					
					/** Handles the fully loaded chunk after the delay has been served
					 *  with the provided method
					 *  @param processedChunk Chunk that is fully loaded from the current instance
					 *         of the world */
					@Override
					protected void handlePromise(Chunk processedChunk) 
					{
						if (processedChunk != null)
						{
							Entity [] chunkEntities = processedChunk.getEntities();
							for (Entity entity : chunkEntities)
							{
								processEntity (entity);
							}
						}
					}
					
				}.runDatabaseTask();
				chunkQueue.remove(i);
			}
		}
	}
	
	/** Enqueues a chunk into the worker queue by storing its world and <X,Z> coordinate pair
	 *  @param Chunk being enqueued */
	public void enqueue (Chunk chunk)
	{
		this.chunkQueue.add(new ChunkData (chunk));
	}
	
	/** A custom function used to process an entity stored within a chunk
	 *  @param entity Entity being processed in the chunk */
	public abstract void processEntity (Entity entity);
}
