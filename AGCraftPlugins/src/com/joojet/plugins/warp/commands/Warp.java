package com.joojet.plugins.warp.commands;

import java.sql.SQLException;

import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.ChatColor;

import com.joojet.plugins.coordinates.commands.GetCoordinates;
import com.joojet.plugins.warp.database.EWarpDatabaseManager;
import com.joojet.plugins.warp.database.LocationDatabaseManager;
import com.joojet.plugins.warp.scantools.ScanEnemies;

public class Warp implements CommandExecutor
{
	
	public final static String home = "home";
	// Max. search radius of nearby enemies check
	private int maxMobRadius = 7;
	// Min. player health needs to exceed before warping
	private double healthThreshold = 20 * 0.79;
	
	/** Warps a player to either a designated location or their bed spawn.
	 * 	Usage:
	 * 		/warp <location name> */
	@Override
	public boolean onCommand (CommandSender sender, Command command, String label, String [] args)
	{
		if (sender instanceof Player)
		{
			Player p = (Player) sender;
			
			int n = args.length;
			boolean inEWarpDatabase = false;
			
			if (n < 1)
			{
				p.sendMessage(ChatColor.RED + "Insufficient parameters.");
				return false;
			}
			
			// Check for player conditions
			if (ScanEnemies.ScanNearbyEnemies(p, maxMobRadius))
			{
				p.sendMessage(ChatColor.RED + "You cannot warp now! There are enemies nearby.");
				return false;
			}
			
			// Also deny warp if the player is either on fire or health drops below threshold
			if (p.getFireTicks() > 0 || p.getHealth() < healthThreshold)
			{
				try
				{
					// But if the player either has an emergency ticket, 
					// or isn't already registered in the ewarp database, let it slide
					inEWarpDatabase = EWarpDatabaseManager.checkIfUserExists(p.getUniqueId().toString());
					
					// If the player is not already in the database, add him into the database and spare his life
					if (!inEWarpDatabase)
					{
						EWarpDatabaseManager.registerNewUser(p.getUniqueId().toString());
						p.sendMessage(ChatColor.RED + "I will let this one slide, but warp will no longer save you from near death experiences in the future.");
						p.sendMessage(ChatColor.RED + "You have " + ChatColor.GOLD + EWarpDatabaseManager.startingTickets + " emergency warps left.");
					}
					// If the player is int the database and has some tickets remaining, decrement it and warn the player again.
					else if (inEWarpDatabase && EWarpDatabaseManager.getTicketCount(p.getUniqueId().toString()) > 0)
					{
						EWarpDatabaseManager.decrementTicketCount(p.getUniqueId().toString());
						int count = EWarpDatabaseManager.getTicketCount(p.getUniqueId().toString());
						if (count > 0)
						{
							p.sendMessage(ChatColor.RED + "You have used up one emergency warp. Please be more careful in the future.");
							p.sendMessage(ChatColor.RED + "You have " + ChatColor.YELLOW + count + ChatColor.RED + " emergency warps left.");
						}
						else
						{
							p.sendMessage(ChatColor.DARK_RED + "You have used up your last emergency warp. Warp will no longer work when you are either taking damage or lit on fire");
						}
					}
					else
					{
						p.sendMessage(ChatColor.RED + "Cannot warp while in combat. Please recover your health before trying again.");
						return false;
					}
				}
				catch (SQLException e)
				{
					System.err.println (e.getMessage());
					p.sendMessage(ChatColor.RED + "An internal error happened while retrieving your location.");
					return false;
				}
			}
			
			String locName = args[0].toLowerCase();
			Location loc = null;
			
			switch (locName)
			{
				case home:
					loc = p.getBedSpawnLocation();
					if (loc == null)
					{
						p.sendMessage(ChatColor.RED + "Error: Your home bed is either missing or obstructed.");
						return false;
					}
					break;
				default:
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
					break;
			}

			p.teleport(loc);
			p.playSound(loc, Sound.ENTITY_ENDERMAN_TELEPORT, 0.4f, 1f);
			p.sendMessage(ChatColor.GOLD + "Teleported you to location " + ChatColor.AQUA + locName + ChatColor.GOLD + " at " + GetCoordinates.getCoordinates(p));
			return true;
		}
		return false;
	}
}
