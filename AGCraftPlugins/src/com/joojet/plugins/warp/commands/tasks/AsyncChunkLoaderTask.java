package com.joojet.plugins.warp.commands.tasks;

import java.sql.SQLException;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.World;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;

import com.joojet.plugins.agcraft.asynctasks.AsyncTask;
import com.joojet.plugins.agcraft.asynctasks.response.DatabaseStatus;
import com.joojet.plugins.agcraft.main.AGCraftPlugin;
import com.joojet.plugins.agcraft.util.Pair;
import com.joojet.plugins.coordinates.commands.GetCoordinates;
import com.joojet.plugins.mobs.chunk.data.ChunkData;
import com.joojet.plugins.warp.scantools.ScanEntities;

public class AsyncChunkLoaderTask extends AsyncTask<DatabaseStatus> 
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
				neighboringChunks.add(new ChunkData (currentCoordinates.getKey() + 1, currentCoordinates.getEntry(), world, currentChunkLevel + 1));
				neighboringChunks.add(new ChunkData (currentCoordinates.getKey() - 1, currentCoordinates.getEntry(), world, currentChunkLevel + 1));
				neighboringChunks.add(new ChunkData (currentCoordinates.getKey(), currentCoordinates.getEntry() + 1, world, currentChunkLevel + 1));
				neighboringChunks.add(new ChunkData (currentCoordinates.getKey(), currentCoordinates.getEntry() - 1, world, currentChunkLevel + 1));
			}
		}
		return chunkData;
	}
	
	@Override
	protected DatabaseStatus getAsyncData() throws SQLException 
	{
		HashSet <ChunkData> chunkData = this.getNearbyChunks( (int)Math.floor(this.teleportLocation.getX() / 16), 
				(int) Math.floor(this.teleportLocation.getZ() / 16), this.teleportLocation.getWorld());
		ExecutorService chunkWorkerPool = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
		for (ChunkData data : chunkData)
		{
			chunkWorkerPool.execute(new Runnable () {

				@Override
				public void run() {
					data.getWorld().getChunkAtAsync(data.getChunkCoordinates().getKey(), data.getChunkCoordinates().getEntry());
				}
				
			});
		}
		chunkWorkerPool.shutdown();
		boolean terminate = false;
		try 
		{
			terminate = chunkWorkerPool.awaitTermination(512, TimeUnit.MILLISECONDS);
		} catch (InterruptedException e) 
		{
			AGCraftPlugin.logger.warning("Unable to load all tasks");
		}
		finally 
		{
			if (!terminate)
			{
				chunkWorkerPool.shutdownNow();
				AGCraftPlugin.logger.warning("Unable to load all chunks. Time limit exceeded.");
			}
		}
		return new DatabaseStatus ("", true);
	}

	@Override
	protected void handlePromise(DatabaseStatus data) 
	{
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
