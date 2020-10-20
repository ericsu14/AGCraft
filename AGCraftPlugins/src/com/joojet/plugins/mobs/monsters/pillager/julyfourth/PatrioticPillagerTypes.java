package com.joojet.plugins.mobs.monsters.pillager.julyfourth;

import org.bukkit.entity.EntityType;

import com.joojet.plugins.mobs.interpreter.MonsterTypeInterpreter;
import com.joojet.plugins.mobs.interpreter.SummoningScrollInterpreter;
import com.joojet.plugins.mobs.monsters.MonsterTypes;

public class PatrioticPillagerTypes extends MonsterTypes 
{
	public PatrioticPillagerTypes (MonsterTypeInterpreter monsterTypeInterpreter, SummoningScrollInterpreter summonTypeInterpreter)
	{
		super (monsterTypeInterpreter, summonTypeInterpreter, EntityType.PILLAGER);
		this.addEquipment(new PatrioticPillager(), 100);
	}
}
