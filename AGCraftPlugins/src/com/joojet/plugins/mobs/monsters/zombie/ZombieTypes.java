package com.joojet.plugins.mobs.monsters.zombie;

import com.joojet.plugins.mobs.interfaces.MonsterTypes;

public class ZombieTypes extends MonsterTypes
{
	
	public ZombieTypes ()
	{
		super();
		this.addEquipment(new UncommonZombie(), 12);
		this.addEquipment(new BadassZombie(), 2);
		this.addEquipment(new UltimateBadassZombie(), 1);
	}

}
