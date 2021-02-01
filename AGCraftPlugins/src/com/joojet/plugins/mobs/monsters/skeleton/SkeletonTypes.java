package com.joojet.plugins.mobs.monsters.skeleton;

import org.bukkit.entity.EntityType;

import com.joojet.plugins.mobs.interpreter.MonsterTypeInterpreter;
import com.joojet.plugins.mobs.interpreter.SummoningScrollInterpreter;
import com.joojet.plugins.mobs.monsters.MonsterTypes;

public class SkeletonTypes extends MonsterTypes {
	public SkeletonTypes (MonsterTypeInterpreter monsterTypeInterpreter, SummoningScrollInterpreter summonTypeInterpreter)
	{
		super (monsterTypeInterpreter, summonTypeInterpreter, EntityType.SKELETON);
		this.addEquipment(new UncommonSkeleton(), 40);
		this.addEquipment(new PotentSkeleton(), 40);
		this.addEquipment(new HurtfulSkeleton(), 18);
		this.addEquipment(new UltimateBadassSkeleton(), 5);
		this.addEquipment(new SoulEater(), 75);
		this.addEquipment(new SkullKid (), 2);
	}
}
