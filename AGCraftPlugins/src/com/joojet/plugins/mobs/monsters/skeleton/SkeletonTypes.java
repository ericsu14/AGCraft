package com.joojet.plugins.mobs.monsters.skeleton;

import com.joojet.plugins.mobs.interfaces.MonsterTypes;

public class SkeletonTypes extends MonsterTypes {
	public SkeletonTypes ()
	{
		this.addEquipment(new UncommonSkeleton(), 75);
		this.addEquipment(new WitheringSkeleton(), 8);
		this.addEquipment(new HurtfulSkeleton(), 14);
		this.addEquipment(new UltimateBadassSkeleton(), 3);
		this.addEquipment(new SkullKid (), 5);
	}
}
