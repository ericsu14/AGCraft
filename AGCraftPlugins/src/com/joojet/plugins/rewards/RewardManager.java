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
import com.joojet.plugins.rewards.enums.MinigameRewardType;
import com.joojet.plugins.rewards.enums.RewardType;
import com.joojet.plugins.rewards.util.StringUtil;

import org.bukkit.ChatColor;

public class RewardManager implements Listener
{	
	/** Creates a new instance of a reward manager with a set minigame type
	 * 		@param minigameType - Type of minigame being played, which is used to determine types of participation rewards */
	public RewardManager ()
	{
		super ();
	}
	
	/** Alerts the player of any unclaimed items if he has some */
	@EventHandler
	public void handlePlayerLogin (PlayerJoinEvent event)
	{
		Player player = event.getPlayer();
		switch (AGCraftPlugin.plugin.serverMode)
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
		MinigameRewardType type = AGCraftPlugin.plugin.minigameEventType;
		try 
		{
			// If the player does not already have a reward from this current event, give them the rewards
			if (!RewardDatabaseManager.checkIfPlayerHasReward(playerUUID, type))
			{
				player.sendMessage(ChatColor.GREEN + "Thanks for taking part in " + ChatColor.GOLD + type.getFullName() + "!");
				player.sendMessage(ChatColor.GREEN + "As a token of apprication, I have added the following rewards to your account, which are: ");
				switch (type)
				{
					case UHC_I:
						this.grantRewards(player, RewardType.DIAMONDS,
								RewardType.FROLF,
								RewardType.SNOWBALL,
								RewardType.ENCHANTED_GOLDEN_APPLE,
								RewardType.STRAWBERRY_MOCKTAIL,
								RewardType.GOLDEN_CARROTS);
						break;
					case UHC_RUSH_REWARDS:
						this.grantRewards(player, RewardType.DIAMONDS, 
								RewardType.FROLF, 
								RewardType.GOLDEN_CARROTS, 
								RewardType.ENCHANTED_GOLDEN_APPLE,
								RewardType.ETERNAL_MOCKTAIL,
								RewardType.USC_CREEPER_SHIELD,
								RewardType.SNOWBALL);
						break;
					default:
						break;
				}
				player.sendMessage(ChatColor.GREEN + "Be sure to run " + ChatColor.GOLD + "/rewards" + ChatColor.GREEN + " to claim your rewards once we revert back to the main server!");
				System.out.println ("I just awarded " + player.getDisplayName() + " prizes for " + type.toString() + "!");
			}
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
			player.sendMessage(ChatColor.RED + "There seems to be an error automatically distributing your participation rewards. Please contact jooj about this immediately.");
		}
	}
	
	/** Grants a list of rewards to the player */
	private void grantRewards (Player player, RewardType... rewards) throws SQLException
	{
		for (RewardType reward : rewards)
		{
			this.grantReward(player, reward);
		}
	}
	
	/** Grants a reward to a player and messages that player on what reward they are getting 
	 * 		@param player - The player this reward is being granted to
	 * 		@param reward - Type of reward being granted
	 * 		@throws SQLException - Internal server error */
	private void grantReward (Player player, RewardType reward) throws SQLException
	{
		UUID playerUUID = player.getUniqueId();
		RewardDatabaseManager.grantReward(playerUUID, reward , AGCraftPlugin.plugin.minigameEventType);
		
		String displayName;
		ItemMeta meta = reward.getReward().getItemMeta();
		if (meta.hasDisplayName())
		{
			StringBuilder temp = new StringBuilder (meta.getDisplayName().substring(0, 2));
			temp.append(StringUtil.generateReadableName (meta.getDisplayName().substring(2), "\\s+")); 
			displayName = temp.toString();
		}
		else
		{
			displayName = StringUtil.generateReadableName(reward.getReward().getType().toString(), "_");
		}
		
		player.sendMessage(ChatColor.AQUA + "Acquired " + ChatColor.GOLD + displayName + " ( x" + reward.getReward().getAmount() + ")");
	}
}
