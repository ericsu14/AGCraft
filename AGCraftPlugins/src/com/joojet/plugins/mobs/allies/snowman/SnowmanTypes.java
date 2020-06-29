package com.joojet.plugins.mobs.allies.snowman;

import com.joojet.plugins.mobs.interfaces.MonsterTypes;

public class SnowmanTypes extends MonsterTypes 
{
	public SnowmanTypes ()
	{
		this.addEquipment(new Frosty (), 2);
		this.addEquipment(new Scruffy(), 1);
	}
}
