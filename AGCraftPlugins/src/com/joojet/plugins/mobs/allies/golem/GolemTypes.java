package com.joojet.plugins.mobs.allies.golem;

import org.bukkit.entity.EntityType;

import com.joojet.plugins.mobs.interfaces.MonsterTypes;

public class GolemTypes extends MonsterTypes 
{
	public GolemTypes ()
	{
		super (EntityType.IRON_GOLEM);
		this.addEquipment(new JohnJae(), 1);
		this.addEquipment(new AdvancedGolem(), 2);
	}
}
