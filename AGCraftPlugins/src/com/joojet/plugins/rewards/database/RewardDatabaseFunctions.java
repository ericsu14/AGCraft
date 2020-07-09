package com.joojet.plugins.rewards.database;

import java.util.ArrayList;
import java.util.UUID;

import com.joojet.plugins.rewards.enums.RewardType;
import com.joojet.plugins.rewards.interfaces.RewardEntry;

public class RewardDatabaseFunctions 
{
	/** Grants a new reward to a player
	 * 		@param uuid - The UUID of the player the reward is being granted to
	 * 		@param reward - The type of reward that is being granted to the player
	 * 		@param event - The name of the event from where the reward is distributed */
	public static void grantReward (UUID uuid, RewardType reward, String event)
	{
		
	}
	
	/** Claims a reward given a passed reward ID
	 * 		@param rewardID - The unique ID of the reward being claimed */
	public static void claimReward (int rewardID)
	{
		
	}
	
	/** Fetches all unclaimed rewards granted to a specific player
	 * 		@param uuid - UUID of the player we are fetching the rewards for. */
	public static ArrayList <RewardEntry> fetchRewards (UUID uuid)
	{
		return new ArrayList <RewardEntry> ();
	}
}
