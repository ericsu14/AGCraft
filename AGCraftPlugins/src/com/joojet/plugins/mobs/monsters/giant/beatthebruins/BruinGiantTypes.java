package com.joojet.plugins.mobs.monsters.giant.beatthebruins;

import com.joojet.plugins.mobs.interpreter.MonsterTypeInterpreter;
import com.joojet.plugins.mobs.monsters.MonsterTypes;

public class BruinGiantTypes extends MonsterTypes 
{
	public BruinGiantTypes (MonsterTypeInterpreter monsterTypeInterpreter)
	{
		// No defined entity types since this is a mount
		super (monsterTypeInterpreter);
		this.addEquipment(new GiantBruin(), 100);
	}
}
