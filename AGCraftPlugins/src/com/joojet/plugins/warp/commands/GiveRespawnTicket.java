package com.joojet.plugins.warp.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

import com.joojet.plugins.warp.database.EWarpDatabaseManager;

import java.sql.SQLException;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;

public class GiveRespawnTicket implements CommandExecutor
{
	@Override
	public boolean onCommand (CommandSender sender, Command command, String label, String [] args)
	{
		if (sender instanceof Player)
		{
			Player p = (Player) sender;
			p.sendMessage(ChatColor.RED + "I am sorry, but this command can only be executed by the server administrator.");
			return false;
		}
		else if (sender instanceof ConsoleCommandSender)
		{
			int n = args.length;
			if (n >= 1)
			{
				String username = args[0];
				@SuppressWarnings("deprecation")
				Player p = Bukkit.getOfflinePlayer(username) == null ? Bukkit.getOfflinePlayer(username).getPlayer() : Bukkit.getPlayer(username);
				if (p == null)
				{
					System.out.println ("Cannot find the player.");
					return false;
				}
				
				String uuid = p.getUniqueId().toString();
				if (EWarpDatabaseManager.checkIfUserExists(uuid))
				{
					try 
					{
						EWarpDatabaseManager.incrementTicketCount(uuid);
						System.out.println ("Gave one respawn ticket to " + username + "!");
						System.out.println (username + " now has " + EWarpDatabaseManager.getTicketCount(uuid) + " tickets.");
						p.sendMessage(ChatColor.AQUA + "You have been granted " + ChatColor.YELLOW + "one" + ChatColor.AQUA + " emergency warp ticket!");
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
