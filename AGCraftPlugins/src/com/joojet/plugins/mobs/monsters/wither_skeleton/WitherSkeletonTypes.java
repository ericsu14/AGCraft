package com.joojet.plugins.mobs.monsters.wither_skeleton;

import com.joojet.plugins.mobs.interfaces.MonsterTypes;

public class WitherSkeletonTypes extends MonsterTypes
{
	public WitherSkeletonTypes ()
	{
		this.addEquipment(new SoulDestroyer(), 99);
		this.addEquipment(new DoomGuy(), 1);
	}
}
