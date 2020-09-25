package com.joojet.plugins.mobs.monsters.zombie.beatthebruins;

import org.bukkit.entity.EntityType;

import com.joojet.plugins.mobs.monsters.MonsterTypes;

public class CollegeZombieTypes extends MonsterTypes
{
	public CollegeZombieTypes ()
	{
		super (EntityType.ZOMBIE, EntityType.HUSK);
		this.addEquipment(new UCLAJock(), 115);
		this.addEquipment(new USCWarrior(), 43);
		this.addEquipment(new SpiritOfTroy(), 28);
		this.addEquipment(new GiantBruinTamer(), 5);
		this.addEquipment(new TrojanWarrior(), 9);
	}
}
