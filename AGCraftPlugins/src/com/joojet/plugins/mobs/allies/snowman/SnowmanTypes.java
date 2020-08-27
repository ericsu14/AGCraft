package com.joojet.plugins.mobs.allies.snowman;

import org.bukkit.entity.EntityType;

import com.joojet.plugins.mobs.monsters.MonsterTypes;

public class SnowmanTypes extends MonsterTypes 
{
	public SnowmanTypes ()
	{
		super (EntityType.SNOWMAN);
		this.addEquipment(new Frosty (), 2);
		this.addEquipment(new Scruffy(), 1);
	}
}
