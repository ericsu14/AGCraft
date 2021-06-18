package com.joojet.plugins.consequences.commands;

import java.sql.SQLException;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

import com.joojet.plugins.agcraft.asynctasks.AsyncTask;
import com.joojet.plugins.agcraft.config.ServerConfigFile;
import com.joojet.plugins.agcraft.enums.CommandType;
import com.joojet.plugins.agcraft.interfaces.AGCommandExecutor;
import com.joojet.plugins.consequences.database.ConsequenceDatabaseManager;

public class ForgivePlayer extends AGCommandExecutor
{

	public ForgivePlayer() 
	{
		super(CommandType.FORGIVE_PLAYER);
	}

	@SuppressWarnings("deprecation")
	@Override
	public boolean onCommand(CommandSender sender, Command arg1, String arg2, String[] args) 
	{		
		if (sender instanceof ConsoleCommandSender || sender instanceof Player)
		{
			int n = args.length;
			if (n < 1)
			{
				sender.sendMessage (ChatColor.RED + "Not enough parameters.");
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
			
			new AsyncTask <Boolean> ()
			{
				@Override
				protected Boolean getAsyncData() throws SQLException 
				{
					ConsequenceDatabaseManager.forgivePlayer(uuid);
					return null;
				}

				@Override
				protected void handlePromise(Boolean data) 
				{
					sender.sendMessage (username + "'s consequences have been lifted.");
				}
				
			}.runAsyncTask();
			
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
