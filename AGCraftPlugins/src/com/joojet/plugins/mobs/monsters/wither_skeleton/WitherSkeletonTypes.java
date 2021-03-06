package com.joojet.plugins.mobs.monsters.wither_skeleton;

import org.bukkit.entity.EntityType;

import com.joojet.plugins.mobs.interpreter.MonsterTypeInterpreter;
import com.joojet.plugins.mobs.interpreter.SummoningScrollInterpreter;
import com.joojet.plugins.mobs.monsters.MonsterTypes;

public class WitherSkeletonTypes extends MonsterTypes
{
	public WitherSkeletonTypes (MonsterTypeInterpreter monsterTypeInterpreter, SummoningScrollInterpreter summonTypeInterpreter)
	{
		super (monsterTypeInterpreter, summonTypeInterpreter, EntityType.WITHER_SKELETON);
		this.addEquipment(new SoulDestroyer(), 60);
		this.addEquipment(new SoulObliterator(), 35);
		this.addEquipment(new HellWalker (), 10);
		this.addEquipment(new DoomGuy(), 5);
	}
}
