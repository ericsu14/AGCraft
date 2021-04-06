package com.joojet.plugins.mobs.skills.weightedentries;

import com.joojet.plugins.mobs.skills.AbstractSkill;
import com.joojet.plugins.mobs.util.WeightedEntry;

public class WeightedMobSkill extends WeightedEntry<AbstractSkill> 
{
	public WeightedMobSkill(AbstractSkill entry, int weight)
	{
		super (entry, weight);
	}
	
}
