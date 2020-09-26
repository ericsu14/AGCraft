package com.joojet.plugins.mobs.monsters.skeleton;

import org.bukkit.entity.EntityType;

import com.joojet.plugins.mobs.monsters.MonsterTypes;

public class SkeletonTypes extends MonsterTypes {
	public SkeletonTypes ()
	{
		super (EntityType.SKELETON);
		this.addEquipment(new UncommonSkeleton(), 80);
		this.addEquipment(new HurtfulSkeleton(), 18);
		this.addEquipment(new UltimateBadassSkeleton(), 2);
		this.addEquipment(new SoulEater(), 70);
		this.addEquipment(new SkullKid (), 10);
	}
}
