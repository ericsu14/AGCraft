package com.joojet.plugins.mobs.monsters;

import com.joojet.plugins.mobs.util.WeightedEntry;

public class WeightedMob extends WeightedEntry <MobEquipment>
{
	public WeightedMob (MobEquipment equipment, int min, int max)
	{
		super (equipment, min, max);
	}
}

