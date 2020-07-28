package com.joojet.plugins.warp.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

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
				if (EWarpDatabaseManager.checkIfUserExists(uuid))
				{
					try 
					{
						EWarpDatabaseManager.incrementTicketCount(uuid);
						sender.sendMessage ("Gave one respawn ticket to " + username + "!");
						sender.sendMessage (username + " now has " + EWarpDatabaseManager.getTicketCount(uuid) + " tickets.");
					} 
					catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
			return true;
		}
		else
		{
			return false;
		}
	}
}
