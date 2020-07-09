package com.joojet.plugins.rewards;

import java.sql.SQLException;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import com.joojet.plugins.rewards.database.RewardDatabaseManager;

import org.bukkit.ChatColor;

public class RewardManager implements Listener
{
	
	/** Alerts the player of any unclaimed items if he has some */
	@EventHandler
	public void handlePlayerLogin (PlayerJoinEvent event)
	{
		Player p = event.getPlayer();
		int size = 0;
		try 
		{
			size = RewardDatabaseManager.fetchUnclaimedRewards(p.getUniqueId()).size();
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		
		if (size > 0)
		{
			p.sendMessage(ChatColor.AQUA + "You have " + ChatColor.GOLD + size + ChatColor.AQUA + " unclaimed rewards waiting for you! Use " + ChatColor.GOLD + "/rewards"
					+ ChatColor.AQUA + " to claim your rewards!");
		}
	}
}
