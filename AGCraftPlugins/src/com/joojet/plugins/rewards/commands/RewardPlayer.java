package com.joojet.plugins.rewards.commands;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

import com.joojet.plugins.agcraft.asynctasks.AsyncTask;
import com.joojet.plugins.agcraft.asynctasks.response.DatabaseStatus;
import com.joojet.plugins.agcraft.enums.CommandType;
import com.joojet.plugins.agcraft.interfaces.AGCommandExecutor;
import com.joojet.plugins.rewards.database.RewardDatabaseManager;
import com.joojet.plugins.rewards.enums.MinigameRewardType;
import com.joojet.plugins.rewards.enums.RewardType;
import com.joojet.plugins.rewards.interpreter.MinigameRewardTypeInterpreter;
import com.joojet.plugins.rewards.interpreter.RewardTypeInterpreter;

public class RewardPlayer extends AGCommandExecutor
{
	/** A reference to the minigame type interpreter defined in main */
	protected MinigameRewardTypeInterpreter minigameRewardTypeInterpreter;
	/** A reference to the reward type interpreter defined in main */
	protected RewardTypeInterpreter rewardTypeInterpreter;
	
	/** Creates a new instance of the reward players command
	 *  @param rewardTypeInterpreter - A reference to the reward type interpreter defined in main
	 * 	@param minigameRewardTypeInterpreter - A reference to the minigame type interpreter defined in main */
	public RewardPlayer() 
	{
		super(CommandType.GRANT_REWARD);
		this.rewardTypeInterpreter = new RewardTypeInterpreter();
		this.minigameRewardTypeInterpreter = new MinigameRewardTypeInterpreter();
	}

	/** Usage:
	 * 		/rewardplayer <Reward Type> <Event Type> [List of Players] */
	@SuppressWarnings("deprecation")
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String [] args) 
	{		
		if (sender instanceof Player || sender instanceof ConsoleCommandSender)
		{
			int n = args.length;
			
			if (n < 3)
			{
				sender.sendMessage ("Not enough parameters");
				return false;
			}
			
			RewardType rewardType = this.rewardTypeInterpreter.searchTrie(args[0]);
			MinigameRewardType eventType = this.minigameRewardTypeInterpreter.searchTrie(args[1]);
			
			if (rewardType == null)
			{
				sender.sendMessage ("Invalid reward");
				return false;
			}
			
			if (eventType == null)
			{
				sender.sendMessage ("Invalid event");
				return false;
			}
			
			// Grabs all offline / online players
			String username;
			ArrayList <UUID> players = new ArrayList <UUID> ();
			for (int i = 2; i < n; ++i)
			{
				username = args[i];
				
				if (username.isEmpty() || (Bukkit.getOfflinePlayer(username) == null && Bukkit.getPlayer(username) == null))
				{
					sender.sendMessage ("Cannot find player " + username);
					return false;
				}
				
				UUID uuid = Bukkit.getOfflinePlayer(username) == null ? Bukkit.getPlayer(username).getUniqueId() : Bukkit.getOfflinePlayer(username).getUniqueId();
				players.add(uuid);
			}
			
			// Rewards each player listed in this command
			new AsyncTask <DatabaseStatus> ()
			{
				@Override
				protected DatabaseStatus getAsyncData() throws SQLException 
				{
					StringBuilder message = new StringBuilder ();
					for (UUID p : players)
					{
						RewardDatabaseManager.grantReward(p, rewardType, eventType);
						message.append ("Sucessfully rewarded player with uuid " + p.toString() + " | " + rewardType.toString());
						message.append ('\n');
					}

					return new DatabaseStatus (message.toString(), true);
				}

				@Override
				protected void handlePromise(DatabaseStatus data) 
				{
					sender.sendMessage(data.getMessage());
				}
				
			}.runAsyncTask();

			return true;
		}
		sender.sendMessage ("Invalid source");
		return false;
	}

}
