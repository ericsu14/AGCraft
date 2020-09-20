package com.joojet.plugins.utility.commands;

import java.text.ParseException;
import java.util.Calendar;

import org.bukkit.ChatColor;
import org.bukkit.World.Environment;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.joojet.plugins.agcraft.enums.CommandType;
import com.joojet.plugins.agcraft.interfaces.AGCommandExecutor;
import com.joojet.plugins.agcraft.main.AGCraftPlugin;
import com.joojet.plugins.consequences.enums.CalendarField;
import com.joojet.plugins.mobs.fireworks.tasks.SpawnFireworksOnLocationTask;
import com.joojet.plugins.mobs.metadata.FireworkCommandMetadata;

public class FireworksCommand extends AGCommandExecutor {
	
	/** Defines the cooldown timer before a player can launch the next fireworks show in miuntes */
	public static int cooldownTimer = 3;
	
	/** Adds a limit on how many fireworks can be launched */
	public static int fireworkLimit = 200;
	
	/** Adds a limit on the firework spread radius */
	public static int fireworkSpreadLimit = 48;
	
	/** Adds a limit on the firework power limit */
	public static int fireworkPowerLimit = 4;
	
	/** Min amount of fireworks needed to be spawned upon starting a show */
	public static int minFireworkCount = 30;
	
	public FireworksCommand ()
	{
		super (CommandType.FIREWORKS);
	}
	
	/** Launches a fireworks show!
	 *  Command Usage:
	 *     /fireworks <radius> <power> <ammo-count> */
	@Override
	public boolean onCommand(CommandSender sender, Command command, String type, String[] args) 
	{
		if (sender instanceof Player)
		{
			Player player = (Player) sender;
			
			try
			{
				int n = args.length;
				
				if (n < 3)
				{
					player.sendMessage(ChatColor.RED + "Insuffient parameters!");
					return false;
				}
				
				// Prevent the player from launching fireworks in the Nether
				if (player.getWorld().getEnvironment() == Environment.NETHER)
				{
					player.sendMessage(ChatColor.RED + "Sorry, but we are unable to launch fireworks in this dimension.");
					return false;
				}
				
				// Checks if the player's cooldown timer has expired.
				Calendar lastUse = new FireworkCommandMetadata().getCalendarFromHolder(player);
				if (lastUse != null && !AGCraftPlugin.plugin.enableDebugMode)
				{
					Calendar now = Calendar.getInstance();
					if (now.compareTo(lastUse) < 0)
					{
						long seconds = (lastUse.getTimeInMillis() - now.getTimeInMillis()) / 1000;
						player.sendMessage (ChatColor.RED + "Please wait " + ChatColor.GOLD + seconds + ChatColor.RED + " seconds before using this command again!");
						return false;
					}
				}
				
				int radius = this.parseBetweenRange(args[0], 1, fireworkSpreadLimit, "Radius");
				int power = this.parseBetweenRange(args[1], 1, fireworkPowerLimit, "Power");
				int ammoCount = this.parseBetweenRange(args[2], minFireworkCount, fireworkLimit, "Ammo Count");
				
				new SpawnFireworksOnLocationTask (player.getLocation(), radius, power, ammoCount).runTaskTimer(AGCraftPlugin.plugin, 30, 15);
				AGCraftPlugin.plugin.getServer().broadcastMessage(ChatColor.GOLD + sender.getName() + ChatColor.AQUA + " started a fireworks show!");
				Calendar cooldown = Calendar.getInstance();
				cooldown.add(CalendarField.MINUTES.getField(), cooldownTimer);
				new FireworkCommandMetadata (cooldown).addStringMetadata(player);
			}
			
			catch (ParseException e)
			{
				e.printStackTrace();
				player.sendMessage (ChatColor.RED + "Detected an invalid parameter.");
				return false;
			}
			
			catch (NumberFormatException ne)
			{
				player.sendMessage(ChatColor.RED + ne.getMessage());
				return false;
			}
			
			return true;
		}
		return false;
	}
	
	protected int parseBetweenRange (String param, int min, int max, String name) throws NumberFormatException
	{
		int result = Integer.parseInt(param);
		if (!(result >= min && result <= max))
		{
			throw new NumberFormatException ("The value for " + name + " must be between " + min + " and " + max + ".");
		}
		return result;
	}

}