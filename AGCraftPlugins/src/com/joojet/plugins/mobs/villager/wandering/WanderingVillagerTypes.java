package com.joojet.plugins.mobs.villager.wandering;

import com.joojet.plugins.mobs.interfaces.MonsterTypes;

public class WanderingVillagerTypes extends MonsterTypes 
{
	public WanderingVillagerTypes ()
	{
		super();
		this.addEquipment(new Frolf(), 1);
	}
}
