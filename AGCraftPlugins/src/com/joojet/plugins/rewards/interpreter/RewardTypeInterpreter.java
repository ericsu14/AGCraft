package com.joojet.plugins.rewards.interpreter;

import com.joojet.plugins.agcraft.interfaces.AbstractInterpreter;
import com.joojet.plugins.rewards.enums.RewardType;

public class RewardTypeInterpreter extends AbstractInterpreter <RewardType>
{
	public RewardTypeInterpreter ()
	{
		super (RewardType.values());
	}
	
}
