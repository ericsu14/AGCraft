package com.joojet.plugins.mobs.skills.weightedentries;

import com.joojet.plugins.mobs.equipment.AbstractPotionEquipment;
import com.joojet.plugins.mobs.util.WeightedEntry;

public class WeightedPotion extends WeightedEntry<AbstractPotionEquipment> 
{
	public WeightedPotion(AbstractPotionEquipment entry, int weight) 
	{
		super(entry, weight);
	}
}
