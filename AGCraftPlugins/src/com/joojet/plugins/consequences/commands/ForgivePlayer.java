package com.joojet.plugins.consequences.commands;

import java.sql.SQLException;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

import com.joojet.plugins.consequences.database.ConsequenceDatabaseManager;

import net.md_5.bungee.api.ChatColor;

public class ForgivePlayer implements CommandExecutor
{

	@SuppressWarnings("deprecation")
	@Override
	public boolean onCommand(CommandSender sender, Command arg1, String arg2, String[] args) 
	{
		// TODO Auto-generated method stub
		if (sender instanceof Player)
		{
			Player player = (Player) sender;
			player.sendMessage(ChatColor.RED + "I am sorry, but this command can only be executed by the server administrator.");
			return false;
		}
		
		else if (sender instanceof ConsoleCommandSender)
		{
			int n = args.length;
			if (n < 1)
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
			
			try 
			{
				ConsequenceDatabaseManager.forgivePlayer(uuid);
				System.out.println (username + "'s consequences have been lifted.");
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
