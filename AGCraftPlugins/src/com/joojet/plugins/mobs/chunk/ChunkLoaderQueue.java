package com.joojet.plugins.mobs.chunk;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Chunk;
import org.bukkit.World;
import org.bukkit.entity.Entity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.world.ChunkLoadEvent;

import com.joojet.plugins.agcraft.interfaces.AGListener;
import com.joojet.plugins.mobs.chunk.interfaces.ChunkEntityHandler;

/** A worker pool module used to queue chunks loaded into and out of the game, 
 *  so that all entities stored on those chunks can be processed at a later time.
 *  
 *  This is because chunk-loading is done asynchronously in the 1.17 releases of Spigot,
 *  therefore making the chunk.getEntities() call unreliable. A delay is necessary
 *  to ensure that all entities are loaded before processing its entities. */
public class ChunkLoaderQueue extends AbstractChunkWorkerQueue implements Listener, AGListener
{
	/** A list of classes that implemented the ChunkEntityHandler interface, allowing those
	 *  classes to process recent chunk-loaded entities */
	protected List <ChunkEntityHandler> chunkEntityHandlers;

	
	/** Creates a new instance of a chunk worker queue, allowing chunks to be safely loaded into the queue
	 *  and have all entities within that chunk to be processed on a timer. */
	public ChunkLoaderQueue ()
	{
		super ();
		this.chunkEntityHandlers = new ArrayList <ChunkEntityHandler> ();
	}
	
	
	/** Listens to chunk load events and enqueues the loaded chunks into the queue */
	@EventHandler
	public void onChunkLoad (ChunkLoadEvent chunkLoadEvent)
	{
		this.enqueue(chunkLoadEvent.getChunk());
	}
	
	@Override
	public void processEntity(Entity entity) 
	{
		for (ChunkEntityHandler handler : this.chunkEntityHandlers)
		{
			handler.processEntityOnChunkLoad(entity);
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
