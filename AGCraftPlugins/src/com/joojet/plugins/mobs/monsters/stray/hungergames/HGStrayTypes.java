package com.joojet.plugins.mobs.monsters.stray.hungergames;

import org.bukkit.entity.EntityType;

import com.joojet.plugins.mobs.interpreter.MonsterTypeInterpreter;
import com.joojet.plugins.mobs.interpreter.SummoningScrollInterpreter;
import com.joojet.plugins.mobs.monsters.MonsterTypes;

public class HGStrayTypes extends MonsterTypes 
{

	public HGStrayTypes(MonsterTypeInterpreter monsterTypeInterpreter, SummoningScrollInterpreter summonTypeInterpreter) 
	{
		super(monsterTypeInterpreter, summonTypeInterpreter, EntityType.STRAY);
		this.addEquipment(new HGMrJohnson (), 900);
	}

}
