package com.joojet.plugins.mobs.monsters.zombie;

import com.joojet.plugins.mobs.interfaces.MonsterTypes;

public class ZombieTypes extends MonsterTypes
{
	
	public ZombieTypes ()
	{
		super();
		this.addEquipment(new UncommonZombie(), 90);
		this.addEquipment(new BadassZombie(), 8);
		this.addEquipment(new UltimateBadassZombie(), 2);
	}

}
