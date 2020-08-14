package com.joojet.plugins.mobs.monsters.skeleton.beatthebruins;

import com.joojet.plugins.mobs.interfaces.MonsterTypes;

public class CollegeSkeletonTypes extends MonsterTypes 
{
	public CollegeSkeletonTypes ()
	{
		this.addEquipment(new UCLAArcher(), 60);
		this.addEquipment(new USCArcher(), 40);
	}
}
