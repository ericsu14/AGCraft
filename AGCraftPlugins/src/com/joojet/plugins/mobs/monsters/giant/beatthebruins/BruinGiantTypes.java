package com.joojet.plugins.mobs.monsters.giant.beatthebruins;

import com.joojet.plugins.mobs.interpreter.MonsterTypeInterpreter;
import com.joojet.plugins.mobs.interpreter.SummoningScrollInterpreter;
import com.joojet.plugins.mobs.monsters.MonsterTypes;

public class BruinGiantTypes extends MonsterTypes 
{
	public BruinGiantTypes (MonsterTypeInterpreter monsterTypeInterpreter, SummoningScrollInterpreter summonTypeInterpreter)
	{
		// No defined entity types since this is a mount
		super (monsterTypeInterpreter, summonTypeInterpreter);
		this.addEquipment(new GiantBruin(), 100);
	}
}
