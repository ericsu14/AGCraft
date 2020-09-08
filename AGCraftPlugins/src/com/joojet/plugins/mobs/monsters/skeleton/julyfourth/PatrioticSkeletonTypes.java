package com.joojet.plugins.mobs.monsters.skeleton.julyfourth;

import org.bukkit.entity.EntityType;

import com.joojet.plugins.mobs.monsters.MonsterTypes;

public class PatrioticSkeletonTypes extends MonsterTypes 
{
	public PatrioticSkeletonTypes ()
	{
		super (EntityType.SKELETON, EntityType.STRAY);
		this.addEquipment(new PatrioticSkeleton(), 100);
	}
}
