package com.joojet.plugins.warp.commands.tasks;

import java.sql.SQLException;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import com.joojet.plugins.agcraft.asynctasks.AsyncDatabaseTask;
import com.joojet.plugins.agcraft.asynctasks.response.DatabaseResponse;
import com.joojet.plugins.warp.database.EWarpDatabaseManager;

public class AsyncEWarpCheckerTask extends AsyncDatabaseTask<DatabaseResponse <String>>  
{
	/** The name of the location the player is warping to */
	protected String locationName;
	/** The player executing this command */
	protected Player player;
	/** The UUID of the player */
	protected String playerUUID;
	
	public AsyncEWarpCheckerTask (Player player, String locationName)
	{
		this.player = player;
		this.locationName = locationName;
		this.playerUUID = player.getUniqueId().toString();
	}
	
	@Override
	protected DatabaseResponse<String> getDataFromDatabase() throws SQLException, RuntimeException 
	{
		boolean status = true;
		StringBuilder message = new StringBuilder ();
		
		// But if the player either has an emergency ticket, 
		// or isn't already registered in the ewarp database, let it slide
		boolean inEWarpDatabase = EWarpDatabaseManager.checkIfUserExists(playerUUID);
		
		// If the player is not already in the database, add him into the database and spare his life
		if (!inEWarpDatabase)
		{
			EWarpDatabaseManager.registerNewUser(playerUUID);
			message.append(ChatColor.RED + "I will let this one slide, but warp will no longer save you from near death experiences in the future.");
			message.append('\n');
			message.append(ChatColor.RED + "You have " + ChatColor.GOLD + EWarpDatabaseManager.startingTickets + " emergency warps left.");
		}
		// If the player is int the database and has some tickets remaining, decrement it and warn the player again.
		else if (inEWarpDatabase && EWarpDatabaseManager.getTicketCount(playerUUID) > 0)
		{
			EWarpDatabaseManager.decrementTicketCount(playerUUID);
			int count = EWarpDatabaseManager.getTicketCount(playerUUID);
			if (count > 0)
			{
				message.append(ChatColor.RED + "You have used up one emergency warp. Please be more careful in the future.");
				message.append('\n');
				message.append(ChatColor.RED + "You have " + ChatColor.YELLOW + count + ChatColor.RED + " emergency warps left.");
			}
			else
			{
				message.append(ChatColor.DARK_RED + "You have used up your last emergency warp. Warp will no longer work when you are either taking damage or lit on fire.");
			}
		}
		else
		{
			message.append(ChatColor.RED + "Cannot warp while in combat. Please recover your health before trying again.");
			status = false;
		}
		return new DatabaseResponse <String> ("", message.toString(), status);
	}

	@Override
	protected void handlePromise(DatabaseResponse<String> data) 
	{
		if (!data.getMessage().isEmpty())
		{
			this.player.sendMessage(data.getMessage());
		}
		
		// Warp the player to the location iff the status is true
		if (data.getStatus())
		{
			new AsyncWarpPlayerTask (this.player, this.locationName).runDatabaseTask();
		}
	}
	
}
