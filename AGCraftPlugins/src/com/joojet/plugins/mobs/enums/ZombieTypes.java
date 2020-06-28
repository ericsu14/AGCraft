package com.joojet.plugins.mobs.enums;

import java.util.Random;

import com.joojet.plugins.mobs.interfaces.MobEquipment;
import com.joojet.plugins.mobs.monsters.zombie.*;

public enum ZombieTypes {
	UNCOMMON_ZOMBIE (new UncommonZombie());
	
	private MobEquipment equipment;
	private ZombieTypes (MobEquipment equip)
	{
		this.equipment = equip;
	}
	
	public MobEquipment getEquipment ()
	{
		return this.equipment;
	}
	
	public static MobEquipment getRandomEquipment ()
	{
		Random rand = new Random();
		return values() [rand.nextInt(values().length)].getEquipment();
	}
}
