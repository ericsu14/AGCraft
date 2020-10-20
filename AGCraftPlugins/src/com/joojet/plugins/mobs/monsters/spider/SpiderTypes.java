package com.joojet.plugins.mobs.monsters.spider;

import org.bukkit.entity.EntityType;

import com.joojet.plugins.mobs.interpreter.MonsterTypeInterpreter;
import com.joojet.plugins.mobs.interpreter.SummoningScrollInterpreter;
import com.joojet.plugins.mobs.monsters.MonsterTypes;

public class SpiderTypes extends MonsterTypes 
{
	public SpiderTypes (MonsterTypeInterpreter monsterTypeInterpreter, SummoningScrollInterpreter summonTypeInterpreter)
	{
		super (monsterTypeInterpreter, summonTypeInterpreter, EntityType.SPIDER);
		this.addEquipment(new AgressiveSpider(), 90);
		this.addEquipment(new EnragedSpider(), 10);
	}
}
