package com.joojet.plugins.mobs.allies.snowman;

import org.bukkit.entity.EntityType;

import com.joojet.plugins.mobs.interpreter.MonsterTypeInterpreter;
import com.joojet.plugins.mobs.interpreter.SummoningScrollInterpreter;
import com.joojet.plugins.mobs.monsters.MonsterTypes;

public class SnowmanTypes extends MonsterTypes 
{
	public SnowmanTypes (MonsterTypeInterpreter monsterTypeInterpreter, SummoningScrollInterpreter summonTypeInterpreter)
	{
		super (monsterTypeInterpreter, summonTypeInterpreter, EntityType.SNOWMAN);
		this.addEquipment(new Frosty (), 2);
		this.addEquipment(new Scruffy(), 1);
	}
}
