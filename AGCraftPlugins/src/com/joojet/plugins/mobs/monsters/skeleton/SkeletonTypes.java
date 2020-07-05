package com.joojet.plugins.mobs.monsters.skeleton;

import com.joojet.plugins.mobs.interfaces.MonsterTypes;

public class SkeletonTypes extends MonsterTypes {
	public SkeletonTypes ()
	{
		this.addEquipment(new UncommonSkeleton(), 10);
		this.addEquipment(new WitheringSkeleton(), 2);
		this.addEquipment(new HurtfulSkeleton(), 3);
		this.addEquipment(new UltimateBadassSkeleton(), 1);
	}
}
