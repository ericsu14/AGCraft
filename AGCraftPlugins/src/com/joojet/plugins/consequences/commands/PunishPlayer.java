package com.joojet.plugins.consequences.commands;

import java.sql.SQLException;
import java.util.Calendar;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

import com.joojet.plugins.agcraft.asynctasks.AsyncDatabaseTask;
import com.joojet.plugins.agcraft.config.ServerConfigFile;
import com.joojet.plugins.agcraft.enums.CommandType;
import com.joojet.plugins.agcraft.interfaces.AGCommandExecutor;
import com.joojet.plugins.consequences.database.ConsequenceDatabaseManager;
import com.joojet.plugins.consequences.enums.CalendarField;
import com.joojet.plugins.consequences.interpreter.CalendarFieldInterpreter;

public class PunishPlayer extends AGCommandExecutor
{
	/** Used to interpret calendar field parameters */
	protected CalendarFieldInterpreter interpreter;
	
	public PunishPlayer ()
	{
		super (CommandType.PUNISH_PLAYER);
		this.interpreter = new CalendarFieldInterpreter();
	}
	
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
		if (sender instanceof ConsoleCommandSender || sender instanceof Player)
		{
			int n = args.length;
			if (n < 3)
			{
				sender.sendMessage ("Not enough parameters.");
				return false;
			}
			
			// Grabs the UUID of the player being punished
			String username;
			username = args[0];
				
			if (Bukkit.getOfflinePlayer(username) == null && Bukkit.getPlayer(username) == null)
			{
				sender.sendMessage ("Cannot find player " + username);
				return false;
			}	
			UUID uuid = Bukkit.getOfflinePlayer(username) == null ? Bukkit.getPlayer(username).getUniqueId() : Bukkit.getOfflinePlayer(username).getUniqueId();
			
			int timeModifiers [] = new int [CalendarField.values().length];
			int modifier;
			CalendarField field;
			try
			{
				// Extracts punish time parameters from the command
				for (int i = 1; i < n; i += 2)
				{
					// Prevents out of bounds exception
					if ((i + 1) >= n)
					{
						break;
					}
					
					// Extracts time modifier and field from arguments
					modifier = Integer.parseInt(args[i]);
					field = this.interpreter.searchTrie(args[i + 1]);
					
					if (field == null)
					{
						sender.sendMessage ("Invalid timefield detected | " + args[i + 1]);
						return false;
					}
					
					timeModifiers[field.ordinal()] += modifier;
					System.out.println ("Modifier: " + modifier + " |  " + "Field: " + field.toString());
				}
			}
			
			catch (NumberFormatException e)
			{
				sender.sendMessage ("Detected an invalid input.");
				return false;
			}
			
			// Creates a new timestamp and adds any time modifiers to the timestamp to determine the expiration date
			Calendar cal = Calendar.getInstance();
			for (CalendarField calField : CalendarField.values())
			{
				cal.add(calField.getField(), timeModifiers[calField.ordinal()]);
			}
			
			new AsyncDatabaseTask <Boolean> ()
			{

				@Override
				protected Boolean getDataFromDatabase() throws SQLException 
				{
					ConsequenceDatabaseManager.punishPlayer(uuid, cal);
					return true;
				}

				@Override
				protected void handlePromise(Boolean data) 
				{
					sender.sendMessage ("Consequence expires in: " + cal.getTime().toString());
				}
				
			}.runDatabaseTask();
			
			return true;
		}
		
		return false;
	}

	@Override
	public void loadConfigVariables(ServerConfigFile config) 
	{
		// TODO Auto-generated method stub
		
	}
	
}
