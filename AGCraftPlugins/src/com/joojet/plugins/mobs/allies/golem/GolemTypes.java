package com.joojet.plugins.mobs.allies.golem;

import org.bukkit.entity.EntityType;

import com.joojet.plugins.mobs.interpreter.MonsterTypeInterpreter;
import com.joojet.plugins.mobs.interpreter.SummoningScrollInterpreter;
import com.joojet.plugins.mobs.monsters.MonsterTypes;

public class GolemTypes extends MonsterTypes 
{
	public GolemTypes (MonsterTypeInterpreter monsterTypeInterpreter, SummoningScrollInterpreter summonTypeInterpreter)
	{
		super (monsterTypeInterpreter, summonTypeInterpreter, EntityType.IRON_GOLEM);
		this.addEquipment(new JohnJae(), 1);
		this.addEquipment(new AdvancedGolem(), 2);
	}
}
