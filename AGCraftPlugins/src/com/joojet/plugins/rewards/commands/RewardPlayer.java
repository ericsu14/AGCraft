package com.joojet.plugins.rewards.commands;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

import com.joojet.plugins.agcraft.enums.CommandType;
import com.joojet.plugins.agcraft.interfaces.AGCommandExecutor;
import com.joojet.plugins.agcraft.main.AGCraftPlugin;
import com.joojet.plugins.rewards.database.RewardDatabaseManager;
import com.joojet.plugins.rewards.enums.EventType;
import com.joojet.plugins.rewards.enums.RewardType;

import net.md_5.bungee.api.ChatColor;

public class RewardPlayer extends AGCommandExecutor
{
	
	public RewardPlayer(CommandType commandType) 
	{
		super(commandType);
	}

	/** Usage:
	 * 		/rewardplayer <Reward Type> <Event Type> [List of Players] */
	@SuppressWarnings("deprecation")
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String [] args) 
	{
		if (sender instanceof Player)
		{
			Player player = (Player) sender;
			player.sendMessage(ChatColor.RED + "I am sorry, but this command can only be executed by the server administrator.");
			return false;
		}
		
		if (sender instanceof ConsoleCommandSender)
		{
			int n = args.length;
			
			if (n < 3)
			{
				System.out.println ("Not enough parameters");
				return false;
			}
			
			RewardType rewardType = AGCraftPlugin.rewardInterpreter.searchTrie(args[0]);
			EventType eventType = AGCraftPlugin.eventInterpreter.searchTrie(args[1]);
			
			if (rewardType == null)
			{
				System.out.println ("Invalid reward");
				return false;
			}
			
			if (eventType == null)
			{
				System.out.println ("Invalid event");
				return false;
			}
			
			// Grabs all offline / online players
			String username;
			ArrayList <UUID> players = new ArrayList <UUID> ();
			for (int i = 2; i < n; ++i)
			{
				username = args[i];
				
				if (Bukkit.getOfflinePlayer(username) == null && Bukkit.getPlayer(username) == null)
				{
					System.out.println ("Cannot find player " + username);
					return false;
				}
				
				UUID uuid = Bukkit.getOfflinePlayer(username) == null ? Bukkit.getPlayer(username).getUniqueId() : Bukkit.getOfflinePlayer(username).getUniqueId();
				players.add(uuid);
			}
			
			// Rewards each player listed in this command
			try 
			{
				for (UUID p : players)
				{
					RewardDatabaseManager.grantReward(p, rewardType, eventType);
					System.out.println ("Sucessfully rewarded player with uuid " + p.toString() + " | " + rewardType.toString());
				}
			}
			catch (SQLException e)
			{
				System.out.println ("Error: " + e.getMessage());
			}
			
			return true;
		}
		System.out.println ("Invalid source");
		return false;
	}
}
