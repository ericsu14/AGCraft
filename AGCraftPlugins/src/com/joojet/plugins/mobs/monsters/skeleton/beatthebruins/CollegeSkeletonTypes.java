package com.joojet.plugins.mobs.monsters.skeleton.beatthebruins;

import org.bukkit.entity.EntityType;

import com.joojet.plugins.mobs.monsters.MonsterTypes;

public class CollegeSkeletonTypes extends MonsterTypes 
{
	public CollegeSkeletonTypes ()
	{
		super (EntityType.SKELETON, EntityType.STRAY);
		this.addEquipment(new UCLAArcher(), 60);
		this.addEquipment(new USCArcher(), 40);
		this.addEquipment(new UCLABearTamer(), 2);
		this.addEquipment(new EternalTrojanArcher(), 1);
	}
}
