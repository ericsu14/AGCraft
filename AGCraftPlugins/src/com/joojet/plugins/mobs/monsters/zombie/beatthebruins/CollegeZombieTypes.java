package com.joojet.plugins.mobs.monsters.zombie.beatthebruins;

import org.bukkit.entity.EntityType;

import com.joojet.plugins.mobs.monsters.MonsterTypes;

public class CollegeZombieTypes extends MonsterTypes
{
	public CollegeZombieTypes ()
	{
		super (EntityType.ZOMBIE, EntityType.HUSK);
		this.addEquipment(new UCLAJock(), 60);
		this.addEquipment(new USCWarrior(), 25);
		this.addEquipment(new SpiritOfTroy(), 15);
		this.addEquipment(new GiantBruinTamer(), 1);
		this.addEquipment(new TrojanWarrior(), 1);
	}
}
