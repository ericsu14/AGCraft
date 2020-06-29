package com.joojet.plugins.mobs.monsters.spider;

import com.joojet.plugins.mobs.interfaces.MonsterTypes;

public class SpiderTypes extends MonsterTypes 
{
	public SpiderTypes ()
	{
		this.addEquipment(new AgressiveSpider(), 5);
		this.addEquipment(new EnragedSpider(), 1);
	}
}
