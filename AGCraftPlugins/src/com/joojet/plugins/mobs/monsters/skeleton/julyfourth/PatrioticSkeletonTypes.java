package com.joojet.plugins.mobs.monsters.skeleton.julyfourth;

import org.bukkit.entity.EntityType;

import com.joojet.plugins.mobs.interpreter.MonsterTypeInterpreter;
import com.joojet.plugins.mobs.interpreter.SummoningScrollInterpreter;
import com.joojet.plugins.mobs.monsters.MonsterTypes;

public class PatrioticSkeletonTypes extends MonsterTypes 
{
	public PatrioticSkeletonTypes (MonsterTypeInterpreter monsterTypeInterpreter, SummoningScrollInterpreter summonTypeInterpreter)
	{
		super (monsterTypeInterpreter, summonTypeInterpreter, EntityType.SKELETON, EntityType.STRAY);
		this.addEquipment(new PatrioticSkeleton(), 100);
	}
}
