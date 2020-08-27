package com.joojet.plugins.mobs.villager.wandering;

import org.bukkit.entity.EntityType;

import com.joojet.plugins.mobs.monsters.MonsterTypes;

public class WanderingVillagerTypes extends MonsterTypes 
{
	public WanderingVillagerTypes ()
	{
		super(EntityType.WANDERING_TRADER);
		this.addEquipment(new Frolf(), 1);
	}
}
