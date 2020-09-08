package com.joojet.plugins.mobs.monsters.pillager.julyfourth;

import org.bukkit.entity.EntityType;

import com.joojet.plugins.mobs.monsters.MonsterTypes;

public class PatrioticPillagerTypes extends MonsterTypes 
{
	public PatrioticPillagerTypes ()
	{
		super (EntityType.PILLAGER);
		this.addEquipment(new PatrioticPillager(), 100);
	}
}
