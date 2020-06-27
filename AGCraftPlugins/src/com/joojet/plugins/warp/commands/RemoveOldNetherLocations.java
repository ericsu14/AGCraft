package com.joojet.plugins.warp.commands;

import java.sql.SQLException;
import java.util.ArrayList;

import org.bukkit.ChatColor;
import org.bukkit.World.Environment;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

import com.joojet.plugins.warp.database.LocationDatabaseManager;
import com.joojet.plugins.warp.database.LocationEntry;

public class RemoveOldNetherLocations implements CommandExecutor
{
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
			try
			{
				ArrayList <LocationEntry> entries = LocationDatabaseManager.getLocationsAsList(Environment.NETHER);
				LocationDatabaseManager.removeAllLocationsUnderEnviroment(Environment.NETHER);
				System.out.println ("Removed the following locations: ");
				for (LocationEntry e : entries)
				{
					System.out.println (e.toString());
				}
				System.out.println ("Size: " + entries.size());
				return true;
			}
			catch (SQLException e)
			{
				System.err.println (e.getMessage());
				return false;
			}
			catch (RuntimeException e)
			{
				System.err.println (e.getMessage());
				return false;
			}
		}
		
		return false;
	}
}
