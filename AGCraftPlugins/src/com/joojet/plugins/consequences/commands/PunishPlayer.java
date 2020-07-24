package com.joojet.plugins.consequences.commands;

import java.sql.SQLException;
import java.util.Calendar;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

import com.joojet.plugins.consequences.ConsequenceManager;
import com.joojet.plugins.consequences.database.ConsequenceDatabaseManager;
import com.joojet.plugins.consequences.enums.CalendarField;

import net.md_5.bungee.api.ChatColor;

public class PunishPlayer implements CommandExecutor
{
	
	/** Punishes a specific player for a certain period of time, forcing him to wear a clown mask the
	 *  next time he/she logs on.
	 *  	Usage:
	 *  		/punishPlayer <player> { <amount> <time_type> }
	 *      Exampe:
	 *      	/punishPlayer CompleteEdd 7 DAYS - punishes me for 7 days
	 *      	/punishPlayer CompleteEdd 30 SECONDS 1 MINUTE - punishes me for 1 minute and 30 seconds */
	@SuppressWarnings("deprecation")
	@Override
	public boolean onCommand(CommandSender sender, Command arg1, String arg2, String[] args) 
	{
		if (sender instanceof Player)
		{
			Player player = (Player) sender;
			player.sendMessage(ChatColor.RED + "I am sorry, but this command can only be executed by the server administrator.");
			return false;
		}
		else if (sender instanceof ConsoleCommandSender)
		{
			int n = args.length;
			if (n < 3)
			{
				System.out.println ("Not enough parameters.");
				return false;
			}
			
			// Grabs the UUID of the player being punished
			String username;
			username = args[0];
				
			if (Bukkit.getOfflinePlayer(username) == null && Bukkit.getPlayer(username) == null)
			{
				System.out.println ("Cannot find player " + username);
				return false;
			}	
			UUID uuid = Bukkit.getOfflinePlayer(username) == null ? Bukkit.getPlayer(username).getUniqueId() : Bukkit.getOfflinePlayer(username).getUniqueId();
			
			int timeModifiers [] = new int [CalendarField.values().length];
			int modifier;
			CalendarField field;
			try
			{
				for (int i = 1; i < n; i += 2)
				{
					// Prevents out of bounds exception
					if ((i + 1) >= n)
					{
						break;
					}
					
					// Extracts time modifier and field from arguments
					modifier = Integer.parseInt(args[i]);
					field = ConsequenceManager.interpreter.searchTrie(args[i + 1]);
					
					if (field == null)
					{
						System.out.println ("Invalid timefield detected | " + args[i + 1]);
						return false;
					}
					
					timeModifiers[field.ordinal()] += modifier;
					System.out.println ("Modifier: " + modifier + " |  " + "Field: " + field.toString());
				}
			}
			
			catch (NumberFormatException e)
			{
				System.out.println ("Detected an invalid input.");
				return false;
			}
			
			// Creates a new timestamp and adds any time modifiers to the timestamp to determine the expiration date
			Calendar cal = Calendar.getInstance();
			for (CalendarField calField : CalendarField.values())
			{
				cal.add(calField.getField(), timeModifiers[calField.ordinal()]);
			}
			
			System.out.println ("Consequence expires in: " + cal.toString());
			
			try 
			{
				ConsequenceDatabaseManager.punishPlayer(uuid, cal);
			} 
			catch (SQLException e) 
			{
				e.printStackTrace();
				return false;
			}
			
			return true;
		}
		
		return false;
	}
	
}
