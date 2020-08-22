package com.joojet.plugins.mobs.metadata;

public class RewardIDMetadata extends AbstractMetadata<Integer> 
{
	public static String REWARD_ID_TAG = "reward-id";
	
	public RewardIDMetadata ()
	{
		super (REWARD_ID_TAG, Integer.MIN_VALUE);
	}
	
	public RewardIDMetadata (Integer value)
	{
		super (REWARD_ID_TAG, value);
	}
}
