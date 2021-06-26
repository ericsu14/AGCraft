package com.joojet.plugins.mobs.chunk;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.HashSet;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Chunk;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.World.Environment;
import org.bukkit.entity.Entity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.world.ChunkLoadEvent;
import org.bukkit.event.world.ChunkUnloadEvent;
import org.bukkit.scheduler.BukkitRunnable;

import com.joojet.plugins.agcraft.asynctasks.AsyncTask;
import com.joojet.plugins.agcraft.interfaces.AGListener;
import com.joojet.plugins.mobs.chunk.interfaces.ChunkEntityHandler;
import com.joojet.plugins.mobs.chunk.interfaces.ChunkUnloadHandler;

/** An abstract worker pool module used to queue chunks loaded into and out of the game, 
 *  so that all entities stored on those chunks can be processed at a later time.
 *  
 *  This is because chunk-loading is done asynchronously in the 1.17 releases of Spigot,
 *  therefore making the chunk.getEntities() call unreliable. A delay is necessary
 *  to ensure that all entities are loaded before processing its entities. */
public class ChunkWorkerQueue extends BukkitRunnable implements Listener, AGListener
{
	/** A queue used to store incoming chunks to be processed */
	protected List <ChunkData> chunkQueue;
	/** Ensures that no duplicate data is inserted */
	protected HashSet <ChunkData> duplicateChunkDataChecker;
	/** A list of classes that implemented the ChunkEntityHandler interface, allowing those
	 *  classes to process recent chunk-loaded entities */
	protected List <ChunkEntityHandler> chunkEntityHandlers;
	/** A list of classes that implemented the ChunkUnloadHandler interface, allowing those
	 *  classes to process recent chunk-unloaded entities */
	protected List <ChunkUnloadHandler> chunkUnloadHandlers;
	
	/** Creates a new instance of a chunk worker queue, allowing chunks to be safely loaded into the queue
	 *  and have all entities within that chunk to be processed on a timer.
	 *  @param timer Time between chunk processes
	 *  @param limit Amount of chunks processed per wave */
	public ChunkWorkerQueue ()
	{
		this.chunkQueue = new ArrayList <ChunkData> ();
		this.duplicateChunkDataChecker = new HashSet <ChunkData> ();
		this.chunkEntityHandlers = new ArrayList <ChunkEntityHandler> ();
		this.chunkUnloadHandlers = new ArrayList <ChunkUnloadHandler> ();
	}
	
	@Override
	public void run ()
	{
		List <ChunkData> finishedChunks = new ArrayList <ChunkData> ();
		HashSet <ChunkData> chunkCoordinateMap = new HashSet <ChunkData> ();
		for (int i = 0; i < chunkQueue.size(); ++i)
		{
			ChunkData chunkData = chunkQueue.get(i);
			if (chunkData.canReadChunk())
			{	
				finishedChunks.add(chunkData);
				chunkCoordinateMap.add(chunkData);
			}
		}
		
		if (!finishedChunks.isEmpty())
		{
			this.chunkQueue.removeAll(finishedChunks);
			this.duplicateChunkDataChecker.removeAll(finishedChunks);
			
			new AsyncTask <List <World>> ()
			{
				
				/** Gets a list of worlds all of the finished chunks reside in */
				@Override
				protected List<World> getAsyncData() throws SQLException 
				{
					List <World> worlds = new ArrayList <World> ();
					EnumSet <Environment> loadedEnvironments = EnumSet.noneOf(Environment.class);
					
					World world;
					for (ChunkData finishedChunk : finishedChunks)
					{
						world = finishedChunk.getWorld();
						if (!loadedEnvironments.contains(world.getEnvironment()))
						{
							worlds.add(world);
							loadedEnvironments.add(world.getEnvironment());
						}
					}
					return worlds;
				}
				
				/** For each world, get all entities in the world, filter them based on if they exist in the list of
				 *  chunks and process them */
				@Override
				protected void handlePromise(List<World> data) 
				{
					for (World world : data)
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
								for (ChunkEntityHandler handler : chunkEntityHandlers)
								{
									handler.processEntityOnChunkLoad(entity);
								}
							}
						}
					}
				}
				
			}.runAsyncTask();
		}
	}
	
	/** Listens to chunk load events and enqueues the loaded chunks into the queue */
	@EventHandler
	public void onChunkLoad (ChunkLoadEvent chunkLoadEvent)
	{
		this.enqueue(chunkLoadEvent.getChunk());
	}
	
	/** Listens to chunk unload events and processes the entities found within those events */
	@EventHandler 
	public void onChunkUnload (ChunkUnloadEvent chunkUnloadEvent)
	{
		Entity [] chunkEntities = chunkUnloadEvent.getChunk().getEntities();
		
		for (Entity entity : chunkEntities)
		{
			for (ChunkUnloadHandler handler : this.chunkUnloadHandlers)
			{
				handler.processEntityOnChunkUnload(entity);
			}
		}
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
	
	/** Adds a new chunk entity handler instance into the worker queue for processing entities */
	public void addChunkEntityHandler (ChunkEntityHandler handler)
	{
		this.chunkEntityHandlers.add(handler);
	}
	
	/** Adds a new chunk unload handler instance into the worker queue for processing unloaded entities */
	public void addChunkUnloadHandler (ChunkUnloadHandler handler)
	{
		this.chunkUnloadHandlers.add(handler);
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
	
	/** Converts a coordinate to a chunk coordinate */
	protected int toChunkCoord (double coord)
	{
		return (int) Math.floor (coord / 16.0);
	}

	@Override
	public void onEnable() 
	{
		this.loadSpawnChunks(Bukkit.getWorlds());
	}

	@Override
	public void onDisable() 
	{
		
	}
}
