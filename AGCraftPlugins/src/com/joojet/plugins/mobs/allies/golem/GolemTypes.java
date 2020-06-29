package com.joojet.plugins.mobs.allies.golem;

import com.joojet.plugins.mobs.interfaces.MonsterTypes;

public class GolemTypes extends MonsterTypes 
{
	public GolemTypes ()
	{
		this.addEquipment(new JohnJae(), 1);
		this.addEquipment(new AdvancedGolem(), 4);
	}
}
