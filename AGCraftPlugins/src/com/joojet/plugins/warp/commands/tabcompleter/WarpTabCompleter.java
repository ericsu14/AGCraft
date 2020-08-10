package com.joojet.plugins.warp.commands.tabcompleter;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.joojet.plugins.agcraft.enums.CommandType;
import com.joojet.plugins.agcraft.interfaces.AGTabCompleter;
import com.joojet.plugins.warp.commands.Warp;
import com.joojet.plugins.warp.constants.WarpAccessLevel;
import com.joojet.plugins.warp.database.LocationDatabaseManager;

public class WarpTabCompleter extends AGTabCompleter 
{
	public WarpTabCompleter ()
	{
		super (CommandType.WARP);
	}
	
	@Override
	public List <String> onTabComplete (CommandSender sender, Command command, String alias, String[] args)
	{
		int n = args.length;
		if (sender instanceof Player && n >= 1)
		{
			Player p = (Player) sender;
			String input = args[n-1].toLowerCase();
			
			switch (n)
			{
				// List all locations
				case 1:
					ArrayList <String> allLocations = new ArrayList <String> ();
					// Gets all private and public locations from the database and outputs them to the autocompleter
					try
					{
						LocationDatabaseManager.getLocationsAsList(p, WarpAccessLevel.PRIVATE).
							forEach(entry -> allLocations.add(entry.getLocationName()));
						LocationDatabaseManager.getLocationsAsList(p, WarpAccessLevel.PUBLIC).
							forEach(entry -> allLocations.add(entry.getLocationName()));	
						allLocations.add(Warp.home);
						
						return this.filterArrayByInput(allLocations.toArray(), input);
					}
					catch (Exception e)
					{
						return new ArrayList <String> ();
					}
				default:
					return new ArrayList <String> ();
			}
		}
		return null;
	}
}
