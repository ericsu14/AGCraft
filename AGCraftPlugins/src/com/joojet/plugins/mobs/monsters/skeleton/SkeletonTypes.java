package com.joojet.plugins.mobs.monsters.skeleton;

import com.joojet.plugins.mobs.interfaces.MonsterTypes;

public class SkeletonTypes extends MonsterTypes {
	public SkeletonTypes ()
	{
		this.addEquipment(new UncommonSkeleton(), 75);
		this.addEquipment(new WitheringSkeleton(), 6);
		this.addEquipment(new HurtfulSkeleton(), 17);
		this.addEquipment(new UltimateBadassSkeleton(), 2);
		this.addEquipment(new SkullKid (), 2);
	}
}
