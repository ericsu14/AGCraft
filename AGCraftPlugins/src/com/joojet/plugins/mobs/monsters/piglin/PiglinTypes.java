package com.joojet.plugins.mobs.monsters.piglin;

import com.joojet.plugins.mobs.interfaces.MonsterTypes;

public class PiglinTypes extends MonsterTypes 
{
	public PiglinTypes ()
	{
		this.addEquipment(new PiglinHunter (), 60);
		this.addEquipment(new PiglinSoldier (), 40);
	}
}
