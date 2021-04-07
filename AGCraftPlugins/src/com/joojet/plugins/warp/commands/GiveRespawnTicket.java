package com.joojet.plugins.warp.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

import com.joojet.plugins.agcraft.asynctasks.AsyncDatabaseTask;
import com.joojet.plugins.agcraft.asynctasks.response.DatabaseResponse;
import com.joojet.plugins.agcraft.config.ServerConfigFile;
import com.joojet.plugins.agcraft.enums.CommandType;
import com.joojet.plugins.agcraft.interfaces.AGCommandExecutor;
import com.joojet.plugins.warp.database.EWarpDatabaseManager;

import java.sql.SQLException;

import org.bukkit.Bukkit;

public class GiveRespawnTicket extends AGCommandExecutor
{
	public GiveRespawnTicket ()
	{
		super (CommandType.GIVE_RESPAWN_TICKET);
	}
	
	@SuppressWarnings("deprecation")
	@Override
	public boolean onCommand (CommandSender sender, Command command, String label, String [] args)
	{
		if (sender instanceof ConsoleCommandSender || sender instanceof Player)
		{
			int n = args.length;
			if (n >= 1)
			{
				String username = args[0];
				if (Bukkit.getOfflinePlayer(username) == null && Bukkit.getPlayer(username) == null)
				{
					sender.sendMessage ("Cannot find the player.");
					return false;
				}
				
				String uuid = Bukkit.getOfflinePlayer(username) == null ? Bukkit.getPlayer(username).getUniqueId().toString() : 
																		  Bukkit.getOfflinePlayer(username).getUniqueId().toString();
				new AsyncDatabaseTask <DatabaseResponse<Integer>> ()
				{
					@Override
					protected DatabaseResponse<Integer> getDataFromDatabase() throws SQLException, RuntimeException 
					{
						StringBuilder message = new StringBuilder ();
						boolean result;
						
						if (EWarpDatabaseManager.checkIfUserExists(uuid))
						{
							EWarpDatabaseManager.incrementTicketCount(uuid);
							message.append("Gave one respawn ticket to " + username + "!");
							message.append('\n');
							message.append(username + " now has " + EWarpDatabaseManager.getTicketCount(uuid) + " tickets.");
							result = true;
						}
						else
						{
							result = false;
							message.append("Cannot find " + username + " in the database");
						}
								
						return new DatabaseResponse <Integer> (0, message.toString(), result);
					}

					@Override
					protected void handlePromise(DatabaseResponse<Integer> data) 
					{
						sender.sendMessage(data.getMessage());
					}
					
				}.runDatabaseTask();
			}
			return true;
		}
		else
		{
			return false;
		}
	}

	@Override
	public void loadConfigVariables(ServerConfigFile config) 
	{
		
	}
}
