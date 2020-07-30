package com.joojet.plugins.rewards.interfaces;

import java.util.UUID;

import com.joojet.plugins.agcraft.main.AGCraftPlugin;
import com.joojet.plugins.rewards.enums.MinigameRewardType;
import com.joojet.plugins.rewards.enums.RewardType;

public class RewardEntry 
{
	/** ID of the reward entry in the database */
	private int rewardID;
	/** UUID of the player the reward is granted to */
	private UUID uuid;
	/** The type of prize being distributed */
	private RewardType reward;
	/** The name of the event in which this reward is distributed from */
	private MinigameRewardType event;
	/** True if the prize is already claimed by the user */
	private boolean claimed;
	
	public RewardEntry (int rewardID, String uuid, String reward, String event, boolean claimed)
	{
		this.rewardID = rewardID;
		this.uuid = UUID.fromString(uuid);
		this.reward = AGCraftPlugin.rewardInterpreter.searchTrie(reward);
		this.event = AGCraftPlugin.minigameRewardTypeInterpreter.searchTrie(event);
		this.claimed = claimed;
		
		if (this.reward == null)
		{
			this.reward = RewardType.TEST_STICK;
			System.out.println ("Warning: Reward is null | " + event);
		}
		
		if (this.event == null)
		{
			this.event = MinigameRewardType.UHC_I;
			System.out.println ("Warning: Event is null | " + event);
		}
	}
	
	public RewardEntry (int rewardID, UUID uuid, RewardType reward, MinigameRewardType event, boolean claimed)
	{
		this.rewardID = rewardID;
		this.uuid = uuid;
		this.reward = reward;
		this.event = event;
		this.claimed = claimed;
	}
	
	public int getRewardID() 
	{
		return rewardID;
	}

	public void setRewardID(int rewardID) 
	{
		this.rewardID = rewardID;
	}

	public UUID getUUID() 
	{
		return uuid;
	}

	public void setUUID(UUID uuid) 
	{
		this.uuid = uuid;
	}

	public RewardType getReward() 
	{
		return reward;
	}

	public void setReward(RewardType reward) 
	{
		this.reward = reward;
	}

	public MinigameRewardType getEvent() 
	{
		return event;
	}

	public void setEvent(MinigameRewardType event) 
	{
		this.event = event;
	}

	public boolean isClaimed() 
	{
		return claimed;
	}

	public void setClaimed(boolean claimed) 
	{
		this.claimed = claimed;
	}
}
