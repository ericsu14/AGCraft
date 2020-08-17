package com.joojet.plugins.mobs.allies.wolf;

import org.bukkit.entity.EntityType;

import com.joojet.plugins.mobs.interfaces.MonsterTypes;

public class WolfTypes extends MonsterTypes
{
	public WolfTypes ()
	{
		super (EntityType.WOLF);
		this.addEquipment(new Snowball(), 90);
		this.addEquipment(new Cookie(), 10);
	}
}
