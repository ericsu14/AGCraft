package com.joojet.plugins.mobs.chunk.data;

import java.util.Objects;

import org.bukkit.Chunk;
import org.bukkit.World;

import com.joojet.plugins.agcraft.util.Pair;

public class ChunkData 
{
	/** World in which the chunk resides */
	protected World world;
	/** <X, Z> coordinate pair for the chunk */
	protected Pair <Integer, Integer> coordinates;
	/** Forces the worker queue to check the chunk at a delayed rate */
	protected boolean isDelayed;
	/** Used to track current depth of BFS search */
	protected int currentLevel;
	
	/** Constructs a new ChunkData class using the properties of an existing chunk */
	public ChunkData (Chunk chunk)
	{
		this.world = chunk.getWorld();
		this.coordinates = new Pair <Integer, Integer> (chunk.getX(), chunk.getZ());
		this.isDelayed = false;
		this.currentLevel = 0;
	}
	
	/** Constructs a new ChunkData class using known <X,Z> chunk coordinate values including its
	 *  own environment  */
	public ChunkData (int x, int z, World world, int currentLevel)
	{
		this.world = world;
		this.coordinates = new Pair <Integer, Integer> (x, z);
		this.isDelayed = false;
		this.currentLevel = currentLevel;
	}
	
	/** Checks if the chunk data can be read on this current wave and marks it as readable
	 *  if it is not already yet. */
	public boolean canReadChunk ()
	{
		boolean result = this.isDelayed && this.world.isChunkLoaded(this.coordinates.getKey(), this.coordinates.getEntry());
		this.isDelayed = true;
		return result;
	}
	
	public int getCurrentLevel ()
	{
		return this.currentLevel;
	}
	
	/** Calls the chunk's world to access the chunk at its given coordinates */
	public Chunk getChunk ()
	{
		return this.world.getChunkAt(this.coordinates.getKey(), this.coordinates.getEntry());
	}
	
	/** Gets the coordinates of the chunk */
	public Pair <Integer, Integer> getChunkCoordinates ()
	{
		return this.coordinates;
	}
	
	/** Gets the world in which the underlying chunk is located */
	public World getWorld ()
	{
		return this.world;
	}
	
	/** Returns true if the chunk is force-loaded into the world */
	public boolean isForceLoaded ()
	{
		return this.world.isChunkForceLoaded(this.coordinates.getKey(), this.coordinates.getEntry());
	}
	
	/** Returns true if both ChunkData instances points to the same chunk */
	@Override
	public boolean equals (Object obj)
	{
		if (obj == this)
		{
			return true;
		}
		
		if (!(obj instanceof ChunkData))
		{
			return false;
		}
		
		if (obj instanceof ChunkData)
		{
			ChunkData otherChunk = (ChunkData) obj;
			Pair <Integer, Integer> otherCoordinates = otherChunk.getChunkCoordinates();
			
			return (this.coordinates.getKey().equals(otherCoordinates.getKey())
					&& this.coordinates.getEntry().equals(otherCoordinates.getEntry())
					&& this.world.getEnvironment() == otherChunk.getWorld().getEnvironment());
		}
		return false;
	}
	
	/** Outputs a String representation of this ChunkData, which outputs its chunk coordinates and world */
	@Override
	public String toString ()
	{
		return "{ X: " + this.coordinates.getKey() + ", Z: " + this.coordinates.getEntry() + " | world: " + this.world.getEnvironment()  + " }";
	}
	
	/** Constructs a hash-code using the ChunkData's environment and X,Z coordinates */
	@Override
	public int hashCode ()
	{
		return Objects.hash(this.world.getEnvironment(), this.coordinates.getKey(), this.coordinates.getEntry());
	}
	
}
