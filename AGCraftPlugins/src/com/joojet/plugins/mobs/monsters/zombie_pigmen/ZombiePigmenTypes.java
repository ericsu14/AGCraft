package com.joojet.plugins.mobs.monsters.zombie_pigmen;

import com.joojet.plugins.mobs.interfaces.MonsterTypes;
import com.joojet.plugins.mobs.monsters.wither_skeleton.DoomGuy;

public class ZombiePigmenTypes extends MonsterTypes 
{
	public ZombiePigmenTypes ()
	{
		this.addEquipment(new VeteranZombiePigmen(), 99);
		this.addEquipment(new DoomGuy(), 1);
	}
}
