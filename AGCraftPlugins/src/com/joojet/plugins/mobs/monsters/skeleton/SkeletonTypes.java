package com.joojet.plugins.mobs.monsters.skeleton;

import com.joojet.plugins.mobs.interfaces.MonsterTypes;

public class SkeletonTypes extends MonsterTypes {
	public SkeletonTypes ()
	{
		this.addEquipment(new UncommonSkeleton(), 12);
		this.addEquipment(new WitheringSkeleton(), 2);
		this.addEquipment(new HurtfulSkeleton(), 3);
		this.addEquipment(new UltimateBadassSkeleton(), 1);
		this.addEquipment(new SkullKid (), 1);
	}
}
