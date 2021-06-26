package com.joojet.plugins.mobs.chunk;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Chunk;
import org.bukkit.entity.Entity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.world.ChunkUnloadEvent;

import com.joojet.plugins.mobs.chunk.data.ChunkData;
import com.joojet.plugins.mobs.chunk.data.UnloadedChunkData;
import com.joojet.plugins.mobs.chunk.interfaces.ChunkUnloadHandler;

/** A worker queue used for handling chunk unload events */
public class ChunkUnloaderQueue extends AbstractChunkWorkerQueue implements Listener
{
	/** A list of classes that implemented the ChunkUnloadHandler interface, allowing those
	 *  classes to process recent chunk-unloaded entities */
	protected List <ChunkUnloadHandler> chunkUnloadHandlers;
	
	public ChunkUnloaderQueue ()
	{
		super();
		this.chunkUnloadHandlers = new ArrayList <ChunkUnloadHandler> ();
	}
	
	@Override
	public void processEntity(Entity entity) 
	{
		for (ChunkUnloadHandler handler : this.chunkUnloadHandlers)
		{
			handler.processEntityOnChunkUnload(entity);
		}
	}
	
	@Override
	/** Enqueues a chunk into the worker queue by storing its world and <X,Z> coordinate pair
	 *  @param Chunk being enqueued */
	public void enqueue (Chunk chunk)
	{
		ChunkData newChunkData = new UnloadedChunkData (chunk);
		if (!this.duplicateChunkDataChecker.contains(newChunkData))
		{
			this.duplicateChunkDataChecker.add(newChunkData);
			this.chunkQueue.add(newChunkData);
		}
	}
	
	/** Listens to chunk unload events and processes the entities found within those events */
	@EventHandler 
	public void onChunkUnload (ChunkUnloadEvent chunkUnloadEvent)
	{
		this.enqueue(chunkUnloadEvent.getChunk());
	}
	
	/** Adds a new chunk unload handler instance into the worker queue for processing unloaded entities */
	public void addChunkUnloadHandler (ChunkUnloadHandler handler)
	{
		this.chunkUnloadHandlers.add(handler);
	}

}
