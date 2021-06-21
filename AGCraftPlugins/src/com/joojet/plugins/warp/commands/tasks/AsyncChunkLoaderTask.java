package com.joojet.plugins.warp.commands.tasks;

import java.sql.SQLException;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.stream.Collectors;

import org.bukkit.ChatColor;
import org.bukkit.Chunk;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.World;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;

import com.joojet.plugins.agcraft.asynctasks.AsyncTask;
import com.joojet.plugins.agcraft.main.AGCraftPlugin;
import com.joojet.plugins.agcraft.util.Pair;
import com.joojet.plugins.coordinates.commands.GetCoordinates;
import com.joojet.plugins.mobs.util.worker.ChunkData;
import com.joojet.plugins.warp.scantools.ScanEntities;

public class AsyncChunkLoaderTask extends AsyncTask<List <Chunk>> 
{
	/** Player being teleported */
	protected Player player;
	/** Name of the warp location the player is teleporting into */
	protected String locationName;
	/** Controls how many chunks are pre-loaded before the player warps to that location */
	protected int renderDistance;
	/** The location where the player is teleporting to */
	protected Location teleportLocation;
	
	public AsyncChunkLoaderTask (Player player, String locationName, Location teleportLocation, int renderDistance)
	{
		this.player = player;
		this.locationName = locationName;
		this.renderDistance = renderDistance;
		this.teleportLocation = teleportLocation;
	}
	
	/** Uses BFS to get a hashset of all possible chunks to render */
	public HashSet <ChunkData> getNearbyChunks (int x, int z, World world)
	{
		HashSet <ChunkData> chunkData = new HashSet <ChunkData> ();
		Queue <ChunkData> neighboringChunks = new LinkedList <ChunkData> ();
		neighboringChunks.add(new ChunkData (x, z, world, 0));
		
		while (!neighboringChunks.isEmpty())
		{
			ChunkData currentChunkData = neighboringChunks.poll();
			int currentChunkLevel = currentChunkData.getCurrentLevel();
			
			if (!chunkData.contains(currentChunkData) && currentChunkLevel < this.renderDistance)
			{
				chunkData.add(currentChunkData);
				
				Pair <Integer, Integer> currentCoordinates = currentChunkData.getChunkCoordinates();
				if (currentChunkLevel < this.renderDistance)
				{
					neighboringChunks.add(new ChunkData (currentCoordinates.getKey() + 1, currentCoordinates.getEntry(), world, currentChunkLevel + 1));
					neighboringChunks.add(new ChunkData (currentCoordinates.getKey() - 1, currentCoordinates.getEntry(), world, currentChunkLevel + 1));
					neighboringChunks.add(new ChunkData (currentCoordinates.getKey(), currentCoordinates.getEntry() + 1, world, currentChunkLevel + 1));
					neighboringChunks.add(new ChunkData (currentCoordinates.getKey(), currentCoordinates.getEntry() - 1, world, currentChunkLevel + 1));
				}
			}
		}
		
		return chunkData;
	}
	
	@Override
	protected List<Chunk> getAsyncData() throws SQLException 
	{
		Chunk origin = this.teleportLocation.getChunk();
		HashSet <ChunkData> chunkData = this.getNearbyChunks(origin.getX(), origin.getZ(), origin.getWorld());
		
		AGCraftPlugin.logger.info("Found " + chunkData.size() + " nearby chunks with a search radius of " + this.renderDistance);
		// TODO: Find a better way to parallelize this
		return chunkData.parallelStream().map((c) -> c.getChunk()).collect(Collectors.toList());
	}

	@Override
	protected void handlePromise(List<Chunk> data) 
	{
		AGCraftPlugin.logger.info("Pre-loaded " + data.size() + " chunks while warping to " + locationName);
		
		List <Entity> ownedEntities = ScanEntities.ScanNearbyPlayerOwnedEntities(player, 40);
		
		// Teleports the player
		player.teleport(teleportLocation);
		player.playSound(teleportLocation, Sound.ENTITY_ENDERMAN_TELEPORT, 0.4f, 1f);
		
		// Teleports any player-owned entities to the player's current location as well
		StringBuilder teleportedEntities = new StringBuilder ();
		int index = 0;
		for (Entity entity : ownedEntities)
		{
			entity.teleport(player.getLocation());
			
			// Appends an "and" to the last element of the string if there is more than one owned entity to teleport
			if (index == ownedEntities.size() - 1 && ownedEntities.size() > 1)
			{
				teleportedEntities = new StringBuilder (teleportedEntities.substring(0, teleportedEntities.length() - 2));
				teleportedEntities.append(ChatColor.GOLD);
				teleportedEntities.append(" and ");
			}
			
			teleportedEntities.append(ChatColor.AQUA);
			teleportedEntities.append(entity.getName());
			
			// Appends a comma on the end of the string if there is more than one entity to teleport
			if (index < ownedEntities.size() - 1)
			{
				teleportedEntities.append(", ");
			}
			
			++index;
		}
		// Notifies the player that their owned entities are teleported with them.
		if (!ownedEntities.isEmpty())
		{
			player.sendMessage(ChatColor.GOLD + "Teleported " + teleportedEntities.toString() + ChatColor.GOLD + " to your location. Please rejoin the server if they are invisible.");
		}
		player.sendMessage(ChatColor.GOLD + "Teleported you to location " + ChatColor.AQUA + locationName + ChatColor.GOLD + " at " + GetCoordinates.getCoordinates(player));
	}

}
