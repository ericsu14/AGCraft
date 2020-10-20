package com.joojet.plugins.mobs.allies.horse.beatthebruins;

import com.joojet.plugins.mobs.interpreter.MonsterTypeInterpreter;
import com.joojet.plugins.mobs.interpreter.SummoningScrollInterpreter;
import com.joojet.plugins.mobs.monsters.MonsterTypes;

public class USCHorseTypes extends MonsterTypes 
{
	public USCHorseTypes (MonsterTypeInterpreter monsterTypeInterpreter, SummoningScrollInterpreter summonTypeInterpreter)
	{
		// No defined entity types since this is a mount
		super (monsterTypeInterpreter, summonTypeInterpreter);
		this.addEquipment(new TheTraveler(), 100);
	}
}
