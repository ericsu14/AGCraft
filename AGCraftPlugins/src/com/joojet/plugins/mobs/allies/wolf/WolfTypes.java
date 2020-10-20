package com.joojet.plugins.mobs.allies.wolf;

import org.bukkit.entity.EntityType;

import com.joojet.plugins.mobs.interpreter.MonsterTypeInterpreter;
import com.joojet.plugins.mobs.interpreter.SummoningScrollInterpreter;
import com.joojet.plugins.mobs.monsters.MonsterTypes;

public class WolfTypes extends MonsterTypes
{
	public WolfTypes (MonsterTypeInterpreter monsterTypeInterpreter, SummoningScrollInterpreter summonTypeInterpreter)
	{
		super (monsterTypeInterpreter, summonTypeInterpreter, EntityType.WOLF);
		this.addEquipment(new Snowball(), 90);
		this.addEquipment(new Cookie(), 10);
	}
}
