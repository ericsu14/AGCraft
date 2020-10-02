package com.joojet.plugins.mobs.monsters.skeleton.beatthebruins;

import org.bukkit.entity.EntityType;

import com.joojet.plugins.mobs.interpreter.MonsterTypeInterpreter;
import com.joojet.plugins.mobs.monsters.MonsterTypes;

public class CollegeSkeletonTypes extends MonsterTypes 
{
	public CollegeSkeletonTypes (MonsterTypeInterpreter monsterTypeInterpreter)
	{
		super (monsterTypeInterpreter, EntityType.SKELETON, EntityType.STRAY);
		this.addEquipment(new UCLAArcher(), 115);
		this.addEquipment(new USCArcher(), 75);
		this.addEquipment(new UCLABearTamer(), 5);
		this.addEquipment(new EternalTrojanArcher(), 5);
	}
}
