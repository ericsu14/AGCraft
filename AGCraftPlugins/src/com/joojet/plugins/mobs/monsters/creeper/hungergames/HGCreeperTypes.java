package com.joojet.plugins.mobs.monsters.creeper.hungergames;

import org.bukkit.entity.EntityType;

import com.joojet.plugins.mobs.interpreter.MonsterTypeInterpreter;
import com.joojet.plugins.mobs.interpreter.SummoningScrollInterpreter;
import com.joojet.plugins.mobs.monsters.MonsterTypes;

public class HGCreeperTypes extends MonsterTypes 
{

	public HGCreeperTypes(MonsterTypeInterpreter monsterTypeInterpreter,
			SummoningScrollInterpreter summonTypeInterpreter) 
	{
		super(monsterTypeInterpreter, summonTypeInterpreter, EntityType.CREEPER);
		this.addEquipment(new HGCreeper (), 100);
	}

}
