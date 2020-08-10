package com.joojet.plugins.rewards.commands.tabcompleter;

import java.util.List;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

import com.joojet.plugins.agcraft.enums.CommandType;
import com.joojet.plugins.agcraft.interfaces.AGTabCompleter;
import com.joojet.plugins.rewards.enums.MinigameRewardType;
import com.joojet.plugins.rewards.enums.RewardType;

public class RewardPlayerTabCompleter extends AGTabCompleter
{
	public RewardPlayerTabCompleter ()
	{
		super (CommandType.GRANT_REWARD);
	}

	@Override
	public List<String> onTabComplete(CommandSender sender, Command arg1, String arg2, String[] args) 
	{
		int n = args.length;
		
		String input = args [n-1].toLowerCase();
		switch (n)
		{
			// Reward type
			case 1:
				return this.filterArrayByInput(RewardType.values(), input);
			// Minigame Reward Type
			case 2:
				return this.filterArrayByInput(MinigameRewardType.values(), input);
			// Display list of active usernames
			default:
				break;
		}
		return null;
	}
	
	
}
