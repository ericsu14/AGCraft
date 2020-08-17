package com.joojet.plugins.mobs.monsters.zombie_pigmen;

import org.bukkit.entity.EntityType;

import com.joojet.plugins.mobs.interfaces.MonsterTypes;

public class ZombiePigmenTypes extends MonsterTypes 
{
	public ZombiePigmenTypes ()
	{
		super (EntityType.ZOMBIFIED_PIGLIN);
		this.addEquipment(new VeteranZombiePigmen(), 93);
		this.addEquipment(new AkimboPigman(), 6);
		this.addEquipment(new TheTerminator(), 1);
	}
}
