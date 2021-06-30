package com.joojet.plugins.mobs.chunk;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.HashSet;
import java.util.List;

import org.bukkit.Chunk;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.World.Environment;
import org.bukkit.entity.Entity;
import org.bukkit.scheduler.BukkitRunnable;

import com.joojet.plugins.mobs.chunk.data.ChunkData;

public abstract class AbstractChunkWorkerQueue extends BukkitRunnable
{
	/** A queue used to store incoming chunks to be processed */
	protected List <ChunkData> chunkQueue;
	/** Ensures that no duplicate data is inserted */
	protected HashSet <ChunkData> duplicateChunkDataChecker;
	
	public AbstractChunkWorkerQueue ()
	{
		this.chunkQueue = new ArrayList <ChunkData> ();
		this.duplicateChunkDataChecker = new HashSet <ChunkData> ();
	}
	
	@Override
	public void run ()
	{
		List <ChunkData> finishedChunks = new ArrayList <ChunkData> ();
		List <World> worlds = new ArrayList <World> ();
		EnumSet <Environment> loadedEnvironments = EnumSet.noneOf(Environment.class);
		HashSet <ChunkData> chunkCoordinateMap = new HashSet <ChunkData> ();
		
		for (int i = 0; i < chunkQueue.size(); ++i)
		{
			ChunkData chunkData = chunkQueue.get(i);
			if (chunkData.canReadChunk())
			{	
				Environment environment = chunkData.getWorld().getEnvironment();
				finishedChunks.add(chunkData);
				chunkCoordinateMap.add(chunkData);
				if (!loadedEnvironments.contains(environment))
				{
					loadedEnvironments.add(environment);
					worlds.add(chunkData.getWorld());
				}
			}
		}
		
		if (!finishedChunks.isEmpty())
		{
			this.chunkQueue.removeAll(finishedChunks);
			this.duplicateChunkDataChecker.removeAll(finishedChunks);
			
			/** For each world, get all entities in the world, filter them based on if they exist in the list of
			 *  chunks and process them */
			for (World world : worlds)
			{
				List <Entity> worldEntities = world.getEntities();
				for (Entity entity : worldEntities)
				{
					// Construct a new ChunkData with the entity's current location and check it against
					// the hashmap of loaded chunk coordinates
					Location entityLocation = entity.getLocation();
					ChunkData entityChunk = new ChunkData (toChunkCoord (entityLocation.getX()), 
							toChunkCoord (entityLocation.getZ()), 
							world, 
							0);
					// If the entity lies on a chunk being loaded, process it
					if (chunkCoordinateMap.contains(entityChunk))
					{
						processEntity (entity);
					}
				}
			}
		}
	}
	
	/** Processes an entity on chunk events
	 *  @param entity entity to be processed */
	public abstract void processEntity (Entity entity);
	
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
	
	/** Converts a coordinate to a chunk coordinate */
	protected int toChunkCoord (double coord)
	{
		return (int) Math.floor (coord / 16.0);
	}
}
