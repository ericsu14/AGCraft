package com.joojet.plugins.warp.commands;

import java.sql.SQLException;
import java.util.ArrayList;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.ChatColor;

import com.joojet.plugins.agcraft.config.ServerConfigFile;
import com.joojet.plugins.agcraft.enums.CommandType;
import com.joojet.plugins.agcraft.interfaces.AGCommandExecutor;
import com.joojet.plugins.warp.constants.WarpAccessLevel;
import com.joojet.plugins.warp.database.LocationDatabaseManager;
import com.joojet.plugins.warp.database.LocationEntry;
import com.joojet.plugins.warp.interpreter.AccessLevelInterpreter;

public class GetLocations extends AGCommandExecutor
{
	private AccessLevelInterpreter interpreter;
	
	public GetLocations ()
	{
		super (CommandType.GET_LOCATIONS);
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
				String str = (n >= 1) ? access.toString().toUpperCase() : "WARP";
				p.sendMessage(ChatColor.AQUA + "==========================");
				p.sendMessage(ChatColor.GOLD + "ALL " + str + " LOCATIONS");
				p.sendMessage(ChatColor.AQUA + "==========================");
				
				// Prints all public locations
				if (n == 0 || access == WarpAccessLevel.PUBLIC)
				{
					ArrayList <LocationEntry> publicLocations = LocationDatabaseManager.getLocationsAsList(p, WarpAccessLevel.PUBLIC);
					printLocationstoPlayer (p, publicLocations, WarpAccessLevel.PUBLIC);
					p.sendMessage("");
				}
				
				if (n == 0 || access == WarpAccessLevel.PRIVATE)
				{
					// Prints all private locations
					ArrayList <LocationEntry> privateLocations = LocationDatabaseManager.getLocationsAsList(p, WarpAccessLevel.PRIVATE);
					printLocationstoPlayer (p, privateLocations, WarpAccessLevel.PRIVATE);
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
	
	private void printLocationstoPlayer (Player p, ArrayList <LocationEntry> locations, WarpAccessLevel access)
	{
		if (access.equals(WarpAccessLevel.PUBLIC))
		{
			p.sendMessage(ChatColor.GOLD  + "All " + ChatColor.AQUA + access.name().toLowerCase() + ChatColor.GOLD + " warp locations: ");
		}
		else
		{
			p.sendMessage(ChatColor.GOLD + p.getDisplayName() + "'s " + ChatColor.AQUA + access.name().toLowerCase() + ChatColor.GOLD + " warp locations: ");
		}
		
		StringBuilder locationList = new StringBuilder ();
		for (LocationEntry entry : locations)
		{
			locationList.append(ChatColor.YELLOW + "");
			locationList.append(entry.getLocationName());
			locationList.append(ChatColor.WHITE + "");
			locationList.append(" | ");
		}
		int ln = locationList.length();
		
		if (locations.isEmpty())
		{
			p.sendMessage(ChatColor.RED + "" + ChatColor.ITALIC + "No locations found.");
		}
		else
		{
			p.sendMessage(ChatColor.AQUA + locationList.substring(0, ln - 3).toString());
		}
	}

	@Override
	public void loadConfigVariables(ServerConfigFile config) 
	{
		
	}
}
