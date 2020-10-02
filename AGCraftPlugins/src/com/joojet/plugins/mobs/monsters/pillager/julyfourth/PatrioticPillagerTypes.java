package com.joojet.plugins.mobs.monsters.pillager.julyfourth;

import org.bukkit.entity.EntityType;

import com.joojet.plugins.mobs.interpreter.MonsterTypeInterpreter;
import com.joojet.plugins.mobs.monsters.MonsterTypes;

public class PatrioticPillagerTypes extends MonsterTypes 
{
	public PatrioticPillagerTypes (MonsterTypeInterpreter monsterTypeInterpreter)
	{
		super (monsterTypeInterpreter, EntityType.PILLAGER);
		this.addEquipment(new PatrioticPillager(), 100);
	}
}
