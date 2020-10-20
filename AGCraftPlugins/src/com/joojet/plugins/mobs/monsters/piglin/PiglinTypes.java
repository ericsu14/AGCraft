package com.joojet.plugins.mobs.monsters.piglin;

import org.bukkit.entity.EntityType;

import com.joojet.plugins.mobs.interpreter.MonsterTypeInterpreter;
import com.joojet.plugins.mobs.interpreter.SummoningScrollInterpreter;
import com.joojet.plugins.mobs.monsters.MonsterTypes;

public class PiglinTypes extends MonsterTypes 
{
	public PiglinTypes (MonsterTypeInterpreter monsterTypeInterpreter, SummoningScrollInterpreter summonTypeInterpreter)
	{
		super (monsterTypeInterpreter, summonTypeInterpreter, EntityType.PIGLIN);
		this.addEquipment(new PiglinHunter (), 55);
		this.addEquipment(new PiglinSoldier (), 35);
		this.addEquipment(new PiglinCaptain (), 10);
	}
}
