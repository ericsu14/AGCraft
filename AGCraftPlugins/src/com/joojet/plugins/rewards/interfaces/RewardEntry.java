package com.joojet.plugins.rewards.interfaces;

import java.util.UUID;

import com.joojet.plugins.rewards.enums.MinigameRewardType;
import com.joojet.plugins.rewards.enums.RewardType;
import com.joojet.plugins.rewards.interpreter.MinigameRewardTypeInterpreter;
import com.joojet.plugins.rewards.interpreter.RewardTypeInterpreter;

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
	
	/** Creates a new reward entry that uses the passed reward type and minigame reward interprers to convert
	 *  the reward and event Strings into known enums.
	 *  @param rewardID - ID of the player's reward
	 *  @param uuid - UUID of the player in which this reward belongs to
	 *  @param reward - The String identifier for the reward's reward type
	 *  @param event - The String identifier for the reward's event type
	 *  @param claimed - A boolean value determining if this reward is claimed or not
	 *  @param rewardInterpreter - A reference to a RewardTypeInterpreter, which is used to convert the 
	 *         reward String into a RewardType enum
	 *  @param minigameRewardTypeInterpreter - A reference to a MinigameTypeInterpreter, used to convert
	 *         the event String into a MinigameRewardType enum.*/
	public RewardEntry (int rewardID, String uuid, String reward, String event, boolean claimed,
			RewardTypeInterpreter rewardInterpreter, MinigameRewardTypeInterpreter minigameRewardTypeInterpreter)
	{
		this.rewardID = rewardID;
		this.uuid = UUID.fromString(uuid);
		this.reward = rewardInterpreter.searchTrie(reward);
		this.event = minigameRewardTypeInterpreter.searchTrie(event);
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
