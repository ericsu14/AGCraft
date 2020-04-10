package com.joojet.plugins.warp.commands;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import com.joojet.plugins.warp.constants.WarpAccessLevel;
import com.joojet.plugins.warp.constants.WarpType;
import com.joojet.plugins.warp.database.LocationDatabaseManager;

public class WarpTabCompleter implements TabCompleter 
{
	@Override
	public List <String> onTabComplete (CommandSender sender, Command command, String alias, String[] args)
	{
		int n = args.length;
		if (sender instanceof Player && n >= 1)
		{
			Player p = (Player) sender;
			String input = args[n-1].toLowerCase();
			Object [] values = null;
			switch (n)
			{
				// List all warp types
				case 1:
					values = Arrays.asList(WarpType.values()).stream().
					map(entry -> (String) entry.toString()).
					filter(entry -> entry.toLowerCase().contains(input)).
					toArray();
					break;
				// List all locations if the tag is location
				case 2:
					if (!Warp.interpreter.searchWarpTypeTrie(args[0]).equals(WarpType.LOCATION))
					{
						return new ArrayList <String> ();
					}
					
					ArrayList <String> allLocations = new ArrayList <String> ();
					// Gets all private and public locations from the database and outputs them to the autocompleter
					try
					{
						LocationDatabaseManager.getLocationsAsList(p, WarpAccessLevel.PRIVATE).
						forEach(entry -> allLocations.add(entry.getLocationName()));
						LocationDatabaseManager.getLocationsAsList(p, WarpAccessLevel.PUBLIC).
						forEach(entry -> allLocations.add(entry.getLocationName()));	
						
						values = allLocations.stream().
								filter(entry -> entry.toLowerCase().
								contains(input)).toArray();
					}
					catch (Exception e)
					{
						return new ArrayList <String> ();
					}
					break;
				default:
					return new ArrayList <String> ();
			}
			
			return (List<String>) Arrays.asList(Arrays.copyOf(values, values.length, String[].class));
		}
		return null;
	}
}
