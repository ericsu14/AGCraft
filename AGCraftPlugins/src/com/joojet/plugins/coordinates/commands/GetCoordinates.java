package com.joojet.plugins.coordinates.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.World.Environment;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.joojet.plugins.agcraft.config.ServerConfigFile;
import com.joojet.plugins.agcraft.enums.CommandType;
import com.joojet.plugins.agcraft.interfaces.AGCommandExecutor;

public class GetCoordinates extends AGCommandExecutor
{
	public GetCoordinates() 
	{
		super(CommandType.GET_COORDINATES);
	}

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
					// Only show coordinates to players in the same dimension as the player.
					Environment otherPlayerEnvironment = p2.getWorld().getEnvironment();
					if (otherPlayerEnvironment.equals(player.getWorld().getEnvironment()))
					{
						player.sendMessage(ChatColor.AQUA + "" + p2.getDisplayName() + "'s " + ChatColor.GOLD + getCoordinates (p2));
					}
					else
					{
						player.sendMessage(ChatColor.RED + "" + ChatColor.ITALIC + otherPlayer + " is currently in " + getEnvironmentName (otherPlayerEnvironment) + ".");
					}
				}
				else
				{
					player.sendMessage(ChatColor.RED + "" + ChatColor.ITALIC + "Either " + otherPlayer + " is not logged in or " + otherPlayer + " is not a whitelisted user in this server.");
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
		coordinates.append ("coordinates: ");
		coordinates.append (getCoordinates (loc));
		return coordinates.toString();
	}
	
	/** Returns the coordinates of a location as a formatted string */
	public static String getCoordinates (Location loc)
	{
		StringBuilder coordinates = new StringBuilder ();
		coordinates.append (ChatColor.AQUA + "");
		coordinates.append ("{ X=");
		coordinates.append (String.format("%.3f", loc.getX()));
		coordinates.append (", Y=");
		coordinates.append (String.format("%.3f", loc.getY()));
		coordinates.append (", Z=");
		coordinates.append (String.format("%.3f", loc.getZ()));
		coordinates.append (" }");
		
		return coordinates.toString();
	}
	
	/** Returns the actual name of the environment */
	public static String getEnvironmentName (Environment env)
	{
		switch (env)
		{
			case NETHER:
				return "the nether";
			case THE_END:
				return "the end";
			default:
				return "the overworld";
		}
	}

	@Override
	public void loadConfigVariables(ServerConfigFile config) 
	{
		
	}
}
