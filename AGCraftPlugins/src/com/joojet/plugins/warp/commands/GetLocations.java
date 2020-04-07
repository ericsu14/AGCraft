package com.joojet.plugins.warp.commands;

import java.sql.SQLException;
import java.util.ArrayList;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.joojet.plugins.warp.constants.WarpAccessLevel;
import com.joojet.plugins.warp.database.LocationDatabaseManager;
import com.joojet.plugins.warp.interpreter.AccessLevelInterpreter;

import net.md_5.bungee.api.ChatColor;

import com.joojet.plugins.warp.database.LocationEntry;

public class GetLocations implements CommandExecutor 
{
	private AccessLevelInterpreter interpreter;
	
	public GetLocations ()
	{
		interpreter = new AccessLevelInterpreter ();
	}
	
	/** Prints all known location name to your chat as a list */
	@Override
	public boolean onCommand (CommandSender sender, Command command, String label, String [] args)
	{
		if (sender instanceof Player)
		{
			int n = args.length;
			Player p = (Player) sender;
			
			WarpAccessLevel access = (n >= 1) ? interpreter.searchTrie(args[0]) : WarpAccessLevel.PRIVATE;
			access = (access == null) ? WarpAccessLevel.PRIVATE : access;
			
			try
			{
				ArrayList <LocationEntry> entries = LocationDatabaseManager.getLocationsAsList(p, access);
				
				p.sendMessage(ChatColor.GRAY + p.getDisplayName() + "'s " + access.name().toLowerCase() + " locations: ");
				StringBuilder locationList = new StringBuilder ();
				for (LocationEntry entry : entries)
				{
					locationList.append(entry.getLocationName());
					locationList.append(" | ");
				}
				int ln = locationList.length();
				if (entries.isEmpty())
				{
					p.sendMessage(ChatColor.GRAY + "" + ChatColor.ITALIC + "No locations found.");
				}
				else
				{
					p.sendMessage(ChatColor.GRAY + "" + ChatColor.ITALIC +locationList.substring(0, ln - 3).toString());
				}
				return true;
			}
			catch (SQLException e)
			{
				System.err.println (e.getMessage());
				p.sendMessage(ChatColor.RED + "An internal error happened while retrieving your location.");
				return false;
			}
			catch (RuntimeException e)
			{
				p.sendMessage(ChatColor.RED + e.getMessage());
				return false;
			}
		}
		
		return false;
	}
}
