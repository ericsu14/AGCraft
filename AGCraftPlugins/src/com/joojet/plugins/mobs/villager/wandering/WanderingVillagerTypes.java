package com.joojet.plugins.mobs.villager.wandering;

import org.bukkit.entity.EntityType;

import com.joojet.plugins.mobs.interpreter.MonsterTypeInterpreter;
import com.joojet.plugins.mobs.monsters.MonsterTypes;

public class WanderingVillagerTypes extends MonsterTypes 
{
	public WanderingVillagerTypes (MonsterTypeInterpreter monsterTypeInterpreter)
	{
		super(monsterTypeInterpreter, EntityType.WANDERING_TRADER);
		this.addEquipment(new Frolf(), 85);
		this.addEquipment(new JohnnyRusnak(), 15);
	}
}
