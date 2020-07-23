package com.joojet.plugins.mobs.monsters.zombie_pigmen;

import com.joojet.plugins.mobs.interfaces.MonsterTypes;

public class ZombiePigmenTypes extends MonsterTypes 
{
	public ZombiePigmenTypes ()
	{
		this.addEquipment(new VeteranZombiePigmen(), 85);
		this.addEquipment(new AkimboPigman(), 14);
		this.addEquipment(new TheTerminator(), 1);
	}
}
