package com.joojet.plugins.warp.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.joojet.plugins.warp.constants.WarpAccessLevel;
import com.joojet.plugins.warp.database.LocationDatabaseManager;
import com.joojet.plugins.warp.interpreter.AccessLevelInterpreter;

import net.md_5.bungee.api.ChatColor;

public class SetLocation implements CommandExecutor
{
	private AccessLevelInterpreter interpreter;
	
	public SetLocation ()
	{
		interpreter = new AccessLevelInterpreter ();
	}
	
	/** Registers the player's current location as a new location in the database as
	 *  a specified name
	 *  	Usage:
	 *  		/setLocation <name of location> <access specifier>	 */
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
				LocationDatabaseManager.insert(p, locationName, access);
				p.sendMessage(ChatColor.GOLD + "" + "Location " + ChatColor.AQUA + locationName + ChatColor.GOLD + " has been successfully registered as a " + ChatColor.AQUA + access.name().toLowerCase() +
						ChatColor.GOLD +" location to " + (access.equals(WarpAccessLevel.PRIVATE) ? "yourself." : "everyone in the server!"));
				return true;
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
