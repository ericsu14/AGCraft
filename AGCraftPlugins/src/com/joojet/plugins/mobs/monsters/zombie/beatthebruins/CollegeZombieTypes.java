package com.joojet.plugins.mobs.monsters.zombie.beatthebruins;

import com.joojet.plugins.mobs.interfaces.MonsterTypes;

public class CollegeZombieTypes extends MonsterTypes
{
	public CollegeZombieTypes ()
	{
		this.addEquipment(new UCLAJock(), 60);
		this.addEquipment(new USCWarrior(), 40);
	}
}
