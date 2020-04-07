package com.joojet.plugins.warp.commands;

import java.sql.SQLException;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.joojet.plugins.warp.constants.WarpAccessLevel;
import com.joojet.plugins.warp.database.LocationDatabaseManager;
import com.joojet.plugins.warp.interpreter.AccessLevelInterpreter;

import net.md_5.bungee.api.ChatColor;

public class RemoveLocation implements CommandExecutor
{
	private AccessLevelInterpreter interpreter;
	
	public RemoveLocation ()
	{
		interpreter = new AccessLevelInterpreter ();
	}
	
	
	/** Removes a location from the player's list of implemented locations.
	 * 	Usage:
	 * 		/removeLocation <locationName> <access> */
	@Override
	public boolean onCommand (CommandSender sender, Command command, String label, String [] args)
	{
		if (sender instanceof Player)
		{
			int n = args.length;
			Player p = (Player) sender;
			
			if (n < 1)
			{
				p.sendMessage(ChatColor.RED + "Insufficient parameters.");
				return false;
			}
			
			String locationName = args[0];
			WarpAccessLevel access = (n >= 2) ? interpreter.searchTrie(args[1]) : WarpAccessLevel.PRIVATE;
			access = (access == null) ? WarpAccessLevel.PRIVATE : access;
			
			try
			{
				LocationDatabaseManager.removeLocation(p.getUniqueId().toString(), locationName, p.getWorld().getEnvironment(), access);
				p.sendMessage(ChatColor.GOLD + locationName + " successfully removed from the database!");
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
