package com.joojet.plugins.warp.commands;

import java.sql.SQLException;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.ChatColor;

import com.joojet.plugins.agcraft.asynctasks.AsyncTask;
import com.joojet.plugins.agcraft.asynctasks.response.DatabaseStatus;
import com.joojet.plugins.agcraft.enums.CommandType;
import com.joojet.plugins.agcraft.interfaces.AGCommandExecutor;
import com.joojet.plugins.warp.database.LocationDatabaseManager;

public class RemoveLocation extends AGCommandExecutor
{
	public RemoveLocation ()
	{
		super (CommandType.REMOVE_LOCATION);
	}
	
	/** Removes a location from the player's list of implemented locations.
	 * 	Usage:
	 * 		/removeLocation <locationName> */
	@Override
	public boolean onCommand (CommandSender sender, Command command, String label, String [] args)
	{
		if (sender instanceof Player)
		{
			int n = args.length;
			Player p = (Player) sender;
			
			if (n < 1)
			{
				p.sendMessage(ChatColor.RED + "Insufficient parameters.");
				return false;
			}
			
			String locationName = args[0];
			
			new AsyncTask <DatabaseStatus> ()
			{
				@Override
				protected DatabaseStatus getAsyncData() throws SQLException 
				{
					boolean status;
					StringBuilder message = new StringBuilder ();
					try
					{
						LocationDatabaseManager.removeLocation(p.getUniqueId().toString(), locationName, p.getWorld().getEnvironment());
						message.append(ChatColor.GOLD + "Successfully removed location " + ChatColor.AQUA + locationName + ChatColor.GOLD + " from the database!");
						status = true;
					}
					catch (RuntimeException re)
					{
						message.append(ChatColor.RED + re.getMessage());
						status = false;
					}
					return new DatabaseStatus (message.toString(), status);
				}

				@Override
				protected void handlePromise(DatabaseStatus data) 
				{
					p.sendMessage(data.getMessage());
				}
				
			}.runAsyncTask();
			
			return true;
		}
		return false;
	}
}
