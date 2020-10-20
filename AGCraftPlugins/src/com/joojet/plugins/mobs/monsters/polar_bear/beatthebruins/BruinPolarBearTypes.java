package com.joojet.plugins.mobs.monsters.polar_bear.beatthebruins;

import com.joojet.plugins.mobs.interpreter.MonsterTypeInterpreter;
import com.joojet.plugins.mobs.interpreter.SummoningScrollInterpreter;
import com.joojet.plugins.mobs.monsters.MonsterTypes;

public class BruinPolarBearTypes extends MonsterTypes
{
	public BruinPolarBearTypes (MonsterTypeInterpreter monsterTypeInterpreter, SummoningScrollInterpreter summonTypeInterpreter)
	{
		// No defined entity type since this is a mount
		super (monsterTypeInterpreter, summonTypeInterpreter);
		this.addEquipment(new TheBruinBear(), 100);
	}
}
