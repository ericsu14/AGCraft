package com.joojet.plugins.mobs.skills;

import com.joojet.plugins.mobs.util.WeightedEntry;

public class WeightedMobSkill extends WeightedEntry<AbstractSkill> 
{
	public WeightedMobSkill(AbstractSkill entry, int weight)
	{
		super (entry, weight);
	}
	
}
