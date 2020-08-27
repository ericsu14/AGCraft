package com.joojet.plugins.mobs.monsters.spider;

import org.bukkit.entity.EntityType;

import com.joojet.plugins.mobs.monsters.MonsterTypes;

public class SpiderTypes extends MonsterTypes 
{
	public SpiderTypes ()
	{
		super (EntityType.SPIDER);
		this.addEquipment(new AgressiveSpider(), 90);
		this.addEquipment(new EnragedSpider(), 10);
	}
}
