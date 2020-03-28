package com.joojet.plugins.coordinates.commands;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import net.md_5.bungee.api.ChatColor;

public class GetCoordinates implements CommandExecutor 
{
	/** A simple command that tells the player their current coordinates
	 * 	Usage:
	 * 		/coordinates - Returns your current position
	 * 		/coordinates <player> Returns the coordinates of another player in the server */
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String [] args) 
	{
		int n = args.length;
		
		if (sender instanceof Player)
		{
			Player player = (Player) sender;
			if (n == 0)
			{
				player.sendMessage(ChatColor.GRAY + "" + ChatColor.ITALIC + "Your "+ GetCoordinates.getCoordinates(player));
			}
			if (n >= 1)
			{
				String otherPlayer = args[0];
				Player p2 = Bukkit.getServer().getPlayer(otherPlayer);
				
				if (p2 != null)
				{
					player.sendMessage(ChatColor.GRAY + "" + ChatColor.ITALIC + otherPlayer + "'s " + getCoordinates (p2));
				}
				else
				{
					player.sendMessage(ChatColor.RED + "" + ChatColor.ITALIC + "Either " + otherPlayer + " is not logged in or " + otherPlayer + " is not a valid user in this server.");
				}
			}
		}
		
		return true;
	}
	
	/** Returns the player's current coordinates as a formatted String */
	public static String getCoordinates (Player player)
	{
		Location loc = player.getLocation();
		StringBuilder coordinates = new StringBuilder ();
		coordinates.append ("coordinates: { X=");
		coordinates.append (String.format("%.3f", loc.getX()));
		coordinates.append (", Y=");
		coordinates.append (String.format("%.3f", loc.getY()));
		coordinates.append (", Z=");
		coordinates.append (String.format("%.3f", loc.getZ()));
		coordinates.append (" }");
		
		return coordinates.toString();
	}
}
