package com.joojet.plugins.warp.commands;
import java.util.List;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.ChatColor;

import com.joojet.plugins.agcraft.config.ServerConfigFile;
import com.joojet.plugins.agcraft.enums.CommandType;
import com.joojet.plugins.agcraft.interfaces.AGCommandExecutor;
import com.joojet.plugins.agcraft.util.Pair;
import com.joojet.plugins.warp.commands.tasks.AsyncGetLocations;
import com.joojet.plugins.warp.constants.WarpAccessLevel;
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
			
			new AsyncGetLocations (n, access, p) 
			{
				@Override
				protected void handlePromise(Pair<List<LocationEntry>, List<LocationEntry>> data) 
				{
					String str = (n >= 1) ? this.accessLevel.toString().toUpperCase() : "WARP";
					p.sendMessage(ChatColor.AQUA + "==========================");
					p.sendMessage(ChatColor.GOLD + "ALL " + str + " LOCATIONS");
					p.sendMessage(ChatColor.AQUA + "==========================");
					
					// Prints all public locations
					if (n == 0 || this.accessLevel == WarpAccessLevel.PUBLIC)
					{
						printLocationstoPlayer (this.player, data.getKey(), WarpAccessLevel.PUBLIC);
					}
					
					if (n == 0 || this.accessLevel == WarpAccessLevel.PRIVATE)
					{
						printLocationstoPlayer (this.player, data.getEntry(), WarpAccessLevel.PRIVATE);
					}
				}
				
			}.runAsyncTask();
			
			return true;
		}
		
		return false;
	}
	
	private void printLocationstoPlayer (Player p, List <LocationEntry> locations, WarpAccessLevel access)
	{
		if (access.equals(WarpAccessLevel.PUBLIC))
		{
			p.sendMessage(ChatColor.GOLD  + "All " + ChatColor.AQUA + access.name().toLowerCase() + ChatColor.GOLD + " warp locations: ");
		}
		else
		{
			p.sendMessage(ChatColor.GOLD + p.getName() + "'s " + ChatColor.AQUA + access.name().toLowerCase() + ChatColor.GOLD + " warp locations: ");
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
