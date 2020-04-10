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
				// Prints all public locations
				ArrayList <String> publicLocations = new ArrayList <String> ();
				LocationDatabaseManager.getLocationsAsList(p, WarpAccessLevel.PUBLIC).
					forEach(entry -> publicLocations.add(entry.getLocationName()));
				printLocationstoPlayer (p, publicLocations, WarpAccessLevel.PUBLIC);
				
				// Prints all private locations
				ArrayList <String> privateLocations = new ArrayList <String> ();
				LocationDatabaseManager.getLocationsAsList(p, WarpAccessLevel.PRIVATE).
					forEach(entry -> privateLocations.add(entry.getLocationName()));
				printLocationstoPlayer (p, privateLocations, WarpAccessLevel.PRIVATE);
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
	
	private void printLocationstoPlayer (Player p, ArrayList <String> locations, WarpAccessLevel access)
	{
		if (access.equals(WarpAccessLevel.PUBLIC))
		{
			p.sendMessage(ChatColor.GOLD  + "All" + ChatColor.AQUA + access.name().toLowerCase() + ChatColor.GOLD + "warp locations: ");
		}
		else
		{
			p.sendMessage(ChatColor.GOLD + p.getDisplayName() + "'s " + ChatColor.AQUA + access.name().toLowerCase() + ChatColor.GOLD + " warp locations: ");
		}
		
		StringBuilder locationList = new StringBuilder ();
		for (String entry : locations)
		{
			locationList.append(ChatColor.AQUA + "");
			locationList.append(entry);
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
}
