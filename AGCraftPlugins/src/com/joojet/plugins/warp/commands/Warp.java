package com.joojet.plugins.warp.commands;

import java.sql.SQLException;

import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.joojet.plugins.coordinates.commands.GetCoordinates;
import com.joojet.plugins.warp.constants.WarpAccessLevel;
import com.joojet.plugins.warp.constants.WarpType;
import com.joojet.plugins.warp.database.LocationDatabaseManager;
import com.joojet.plugins.warp.interpreter.WarpCommandInterpreter;

import net.md_5.bungee.api.ChatColor;

public class Warp implements CommandExecutor
{
	
	private WarpCommandInterpreter interpreter;
	
	public Warp ()
	{
		this.interpreter = new WarpCommandInterpreter();
	}
	
	/** Warps a player to either a designated location or their bed spawn.
	 * 	Usage:
	 * 		/warp <tag> <location name>
	 * 
	 *  Examples:
	 *  	/warp home
	 *  	/warp location <name>*/
	@Override
	public boolean onCommand (CommandSender sender, Command command, String label, String [] args)
	{
		if (sender instanceof Player)
		{
			Player p = (Player) sender;
			
			int n = args.length;
			
			if (n < 1)
			{
				p.sendMessage(ChatColor.RED + "Insufficient parameters.");
				return false;
			}
			
			WarpType type = this.interpreter.searchWarpTypeTrie(args[0]);
			
			
			// Idiot proofing incase nobody reads the docs.
			if (type == null)
			{
				type = WarpType.LOCATION;
				args[1] = args[0];
			}
				
			Location loc = null;
			String name = "";
			switch (type)
			{
				case HOME:
					loc = p.getBedSpawnLocation();
					if (loc == null)
					{
						p.sendMessage(ChatColor.RED + "Error: Your home bed is either missing or obstructed.");
						return false;
					}
					name = "home";
					break;
				case LOCATION:
					if (n <= 1)
					{
						p.sendMessage(ChatColor.RED + "No location name specified.");
						return false;
					}
					String locName = args[1];
					try 
					{
						loc = LocationDatabaseManager.getlocation(p, locName);
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
					name = locName;
					break;
				default:
					return false;
			}
			
			p.teleport(loc);
			p.playSound(loc, Sound.ENTITY_ENDERMAN_TELEPORT, 0.4f, 1f);
			p.sendMessage(ChatColor.GOLD + "Teleported you to location " + name + " at " + GetCoordinates.getCoordinates(p));
			return true;
		}
		return false;
	}
}
