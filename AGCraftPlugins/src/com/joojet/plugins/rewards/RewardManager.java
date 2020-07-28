package com.joojet.plugins.rewards;

import java.sql.SQLException;
import java.util.UUID;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.meta.ItemMeta;

import com.joojet.plugins.agcraft.main.AGCraftPlugin;
import com.joojet.plugins.rewards.database.RewardDatabaseManager;
import com.joojet.plugins.rewards.enums.EventType;
import com.joojet.plugins.rewards.enums.RewardType;
import com.joojet.plugins.rewards.util.StringUtil;

import org.bukkit.ChatColor;

public class RewardManager implements Listener
{
	/** If we are hosting a minigame, specify which event we are running to
	 *  determine participation rewards for joining the server */
	private final EventType currentEventType = EventType.UHC_I;
	
	/** Alerts the player of any unclaimed items if he has some */
	@EventHandler
	public void handlePlayerLogin (PlayerJoinEvent event)
	{
		Player player = event.getPlayer();
		switch (AGCraftPlugin.serverMode)
		{
			case NORMAL:
				this.handleNormalLoginEvent(player);
				break;
			case UHC:
				this.handleUHCLoginEvent(player);
				break;
			default:
				return;
		}
	}
	
	/** Handles normal player login events */
	public void handleNormalLoginEvent (Player player)
	{
		int size = 0;
		try 
		{
			size = RewardDatabaseManager.fetchUnclaimedRewards(player.getUniqueId()).size();
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		
		if (size > 0)
		{
			player.sendMessage(ChatColor.AQUA + "You have " + ChatColor.GOLD + size + ChatColor.AQUA + " unclaimed rewards waiting for you! Use " + ChatColor.GOLD + "/rewards"
					+ ChatColor.AQUA + " to claim your rewards!");
		}
	}
	
	/** Handles player login events during UHC gamenights */
	public void handleUHCLoginEvent (Player player)
	{
		// For now, distribute generic UHC participation rewards
		UUID playerUUID = player.getUniqueId();
		
		try 
		{
			// If the player does not already have a reward from this current event, give them the rewards
			if (!RewardDatabaseManager.checkIfPlayerHasReward(playerUUID, this.currentEventType))
			{
				player.sendMessage(ChatColor.GREEN + "Thanks for taking part in " + ChatColor.GOLD + this.currentEventType.getFullName() + "!");
				player.sendMessage(ChatColor.GREEN + "As a token of apprication, I have added the following rewards to your account, which are: ");
				switch (this.currentEventType)
				{
					case UHC_I:
						this.grantReward(player, RewardType.DIAMONDS);
						this.grantReward(player, RewardType.FROLF);
						this.grantReward(player, RewardType.SNOWBALL);
						this.grantReward(player, RewardType.ENCHANTED_GOLDEN_APPLE);
						this.grantReward(player, RewardType.STRAWBERRY_MOCKTAIL);
						this.grantReward(player, RewardType.GOLDEN_CARROTS);
						break;
					default:
						break;
				}
				player.sendMessage(ChatColor.GREEN + "Be sure to run " + ChatColor.GOLD + "/rewards" + ChatColor.GREEN + " to claim your rewards once we revert back to the main server!");
				System.out.println ("I just awarded " + player.getDisplayName() + " prizes for " + this.currentEventType.toString() + "!");
			}
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
			player.sendMessage(ChatColor.RED + "There seems to be an error automatically distributing your participation rewards. Please contact jooj about this immediately.");
		}
	}
	
	/** Grants a reward to a player and messages that player on what reward they are getting 
	 * 		@param player - The player this reward is being granted to
	 * 		@param reward - Type of reward being granted
	 * 		@throws SQLException - Internal server error */
	private void grantReward (Player player, RewardType reward) throws SQLException
	{
		UUID playerUUID = player.getUniqueId();
		RewardDatabaseManager.grantReward(playerUUID, reward , this.currentEventType);
		
		String displayName;
		ItemMeta meta = reward.getReward().getItemMeta();
		if (meta.hasDisplayName())
		{
			displayName = meta.getDisplayName(); 
		}
		else
		{
			displayName = reward.getReward().getType().toString();
			displayName = StringUtil.generateReadableName(displayName, "_");
		}
		
		player.sendMessage(ChatColor.AQUA + "Acquired " + ChatColor.GOLD + displayName + " ( x" + reward.getReward().getAmount() + ")");
	}
}
