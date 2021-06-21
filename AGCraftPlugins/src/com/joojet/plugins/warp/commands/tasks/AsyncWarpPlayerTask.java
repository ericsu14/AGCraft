package com.joojet.plugins.warp.commands.tasks;

import java.sql.SQLException;
import java.util.UUID;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import com.joojet.plugins.agcraft.asynctasks.AsyncTask;
import com.joojet.plugins.agcraft.asynctasks.response.DatabaseResponse;
import com.joojet.plugins.warp.commands.Warp;
import com.joojet.plugins.warp.database.LocationDatabaseManager;

public class AsyncWarpPlayerTask extends AsyncTask<DatabaseResponse <Location>>
{
	/** The player's bed spawn location */
	protected Location playerBedSpawnLocation;
	/** The player executing this command */
	protected Player player;
	/** The UUID of the player */
	protected UUID playerUUID;
	/** The name of the location the player is warping to */
	protected String locationName;
	
	public AsyncWarpPlayerTask (Player player, String locationName)
	{
		this.playerBedSpawnLocation = player.getBedSpawnLocation();
		this.player = player;
		this.playerUUID = player.getUniqueId();
		this.locationName = locationName;
	}
	
	@Override
	protected DatabaseResponse <Location> getAsyncData() throws SQLException, RuntimeException
	{
		boolean status = true;
		Location result = null;
		StringBuilder message = new StringBuilder ();
		try
		{
			switch (this.locationName)
			{
				case Warp.home:
					result = this.playerBedSpawnLocation;
					break;
				default:
					result = LocationDatabaseManager.getlocation(player, locationName);
					break;
			}
		}
		catch (RuntimeException re)
		{
			message.append(re.getMessage());
			status = false;
		}
		
		return new DatabaseResponse <Location> (result, message.toString(), status);
	}

	@Override
	protected void handlePromise(DatabaseResponse <Location> response) 
	{
		// Null check for player
		if (this.player == null)
		{
			return;
		}
		
		// Send error message when something went wrong with location retrieval
		if (!response.getStatus())
		{
			this.player.sendMessage(ChatColor.RED + response.getMessage());
			return;
		}
		
		Location location = response.getData();
		
		if (location == null)
		{
			if (locationName.equals(Warp.home))
			{
				this.player.sendMessage(ChatColor.RED + "Your home bed is either missing or obstructed.");
			}
			else
			{
				this.player.sendMessage(ChatColor.GOLD + this.locationName + ChatColor.RED + " is not a valid stored location in our database.");
			}
			return;
		}
		
		new AsyncChunkLoaderTask (this.player, this.locationName, location, 12).runAsyncTask();
	}

}
