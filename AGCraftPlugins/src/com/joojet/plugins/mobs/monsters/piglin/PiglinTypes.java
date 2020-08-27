package com.joojet.plugins.mobs.monsters.piglin;

import org.bukkit.entity.EntityType;

import com.joojet.plugins.mobs.monsters.MonsterTypes;

public class PiglinTypes extends MonsterTypes 
{
	public PiglinTypes ()
	{
		super (EntityType.PIGLIN);
		this.addEquipment(new PiglinHunter (), 55);
		this.addEquipment(new PiglinSoldier (), 35);
		this.addEquipment(new PiglinCaptain (), 10);
	}
}
