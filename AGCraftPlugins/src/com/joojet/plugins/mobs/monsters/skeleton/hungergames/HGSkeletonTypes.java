package com.joojet.plugins.mobs.monsters.skeleton.hungergames;

import org.bukkit.entity.EntityType;

import com.joojet.plugins.mobs.interpreter.MonsterTypeInterpreter;
import com.joojet.plugins.mobs.interpreter.SummoningScrollInterpreter;
import com.joojet.plugins.mobs.monsters.MonsterTypes;

public class HGSkeletonTypes extends MonsterTypes 
{

	public HGSkeletonTypes(MonsterTypeInterpreter monsterTypeInterpreter,
			SummoningScrollInterpreter summonTypeInterpreter) 
	{
		super(monsterTypeInterpreter, summonTypeInterpreter, EntityType.SKELETON, EntityType.STRAY);
		this.addEquipment(new HGUCLAArcher (), 60);
		this.addEquipment(new HGUSCArcher (), 40);
	}

}
