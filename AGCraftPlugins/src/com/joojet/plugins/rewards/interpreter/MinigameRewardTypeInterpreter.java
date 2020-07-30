package com.joojet.plugins.rewards.interpreter;

import com.joojet.plugins.agcraft.interfaces.AbstractInterpreter;
import com.joojet.plugins.rewards.enums.MinigameRewardType;

public class MinigameRewardTypeInterpreter extends AbstractInterpreter<MinigameRewardType> 
{
	public MinigameRewardTypeInterpreter ()
	{
		super (MinigameRewardType.values());
	}
	
}
