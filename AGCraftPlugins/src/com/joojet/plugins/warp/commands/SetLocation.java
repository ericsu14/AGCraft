package com.joojet.plugins.warp.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.sql.SQLException;

import org.bukkit.ChatColor;

import com.joojet.plugins.agcraft.asynctasks.AsyncDatabaseTask;
import com.joojet.plugins.agcraft.asynctasks.response.DatabaseStatus;
import com.joojet.plugins.agcraft.config.ServerConfigFile;
import com.joojet.plugins.agcraft.enums.CommandType;
import com.joojet.plugins.agcraft.interfaces.AGCommandExecutor;
import com.joojet.plugins.warp.constants.WarpAccessLevel;
import com.joojet.plugins.warp.database.LocationDatabaseManager;
import com.joojet.plugins.warp.interpreter.AccessLevelInterpreter;

public class SetLocation extends AGCommandExecutor
{
	private AccessLevelInterpreter interpreter;
	
	public SetLocation ()
	{
		super (CommandType.SET_LOCATION);
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
			
			// The player cannot set their location to a hardcoded location, home.
			if (locationName.equalsIgnoreCase(Warp.home))
			{
				p.sendMessage(ChatColor.RED + "home is a hardcoded location that cannot be overridden by the player.");
				return false;
			}
			
			new AsyncDatabaseTask <DatabaseStatus> ()
			{
				@Override
				protected DatabaseStatus getDataFromDatabase() throws SQLException
				{
					WarpAccessLevel access = (n >= 2) ? interpreter.searchTrie(args[1]) : WarpAccessLevel.PRIVATE;
					access = (access == null) ? WarpAccessLevel.PRIVATE : access;
					
					StringBuilder message = new StringBuilder ();
					try
					{
						LocationDatabaseManager.insert(p, locationName, access);
						message.append(ChatColor.GOLD + "" + "Location " + ChatColor.AQUA + locationName + ChatColor.GOLD + " has been successfully registered as a " + ChatColor.AQUA + access.name().toLowerCase() +
								ChatColor.GOLD +" location " + (access.equals(WarpAccessLevel.PRIVATE) ? "to yourself." : "for everyone in the server!"));
					}
					catch (RuntimeException e)
					{
						message.append(ChatColor.RED + e.getMessage());
					}
					return new DatabaseStatus (message.toString(), true);
				}

				@Override
				protected void handlePromise(DatabaseStatus data) 
				{
					p.sendMessage(data.getMessage());
					
				}
			}.runDatabaseTask();
		}
		return false;
	}

	@Override
	public void loadConfigVariables(ServerConfigFile config) 
	{
		
	}
}
