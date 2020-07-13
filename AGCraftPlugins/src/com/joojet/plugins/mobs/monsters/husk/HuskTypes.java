package com.joojet.plugins.mobs.monsters.husk;

import com.joojet.plugins.mobs.interfaces.MonsterTypes;

public class HuskTypes extends MonsterTypes
{
	public HuskTypes ()
	{
		this.addEquipment(new WanderingHusk(), 85);
		this.addEquipment(new FallenPharaoh (), 15);
	}
}
