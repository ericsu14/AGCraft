package com.joojet.plugins.mobs.monsters.stray;

import org.bukkit.entity.EntityType;

import com.joojet.plugins.mobs.interpreter.MonsterTypeInterpreter;
import com.joojet.plugins.mobs.interpreter.SummoningScrollInterpreter;
import com.joojet.plugins.mobs.monsters.MonsterTypes;

public class StrayTypes extends MonsterTypes 
{

	public StrayTypes(MonsterTypeInterpreter monsterTypeInterpreter, SummoningScrollInterpreter summonTypeInterpreter)
	{
		super(monsterTypeInterpreter, summonTypeInterpreter, EntityType.STRAY);
		this.addEquipment(new MrJohnsonHidden (), 0);
	}

}
