package com.joojet.plugins.mobs.monsters.skeleton;

import org.bukkit.entity.EntityType;

import com.joojet.plugins.mobs.interpreter.MonsterTypeInterpreter;
import com.joojet.plugins.mobs.monsters.MonsterTypes;

public class SkeletonTypes extends MonsterTypes {
	public SkeletonTypes (MonsterTypeInterpreter monsterTypeInterpreter)
	{
		super (monsterTypeInterpreter, EntityType.SKELETON);
		this.addEquipment(new UncommonSkeleton(), 40);
		this.addEquipment(new PotentSkeleton(), 40);
		this.addEquipment(new HurtfulSkeleton(), 18);
		this.addEquipment(new UltimateBadassSkeleton(), 2);
		this.addEquipment(new SoulEater(), 75);
		this.addEquipment(new SkullKid (), 5);
	}
}
