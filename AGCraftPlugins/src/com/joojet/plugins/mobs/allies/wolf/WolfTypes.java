package com.joojet.plugins.mobs.allies.wolf;

import com.joojet.plugins.mobs.interfaces.MonsterTypes;

public class WolfTypes extends MonsterTypes
{
	public WolfTypes ()
	{
		this.addEquipment(new Snowball(), 90);
		this.addEquipment(new Cookie(), 10);
	}
}
