package com.joojet.plugins.mobs.monsters.wither_skeleton;

import com.joojet.plugins.mobs.interfaces.MonsterTypes;

public class WitherSkeletonTypes extends MonsterTypes
{
	public WitherSkeletonTypes ()
	{
		this.addEquipment(new SoulDestroyer(), 60);
		this.addEquipment(new SoulObliterator(), 36);
		this.addEquipment(new DoomGuy(), 3);
	}
}
