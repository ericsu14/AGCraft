package com.joojet.plugins.mobs.monsters.giant.beatthebruins;

import org.bukkit.entity.EntityType;

import com.joojet.plugins.mobs.interpreter.MonsterTypeInterpreter;
import com.joojet.plugins.mobs.interpreter.SummoningScrollInterpreter;
import com.joojet.plugins.mobs.monsters.MonsterTypes;

public class BruinGiantTypes extends MonsterTypes 
{
	public BruinGiantTypes (MonsterTypeInterpreter monsterTypeInterpreter, SummoningScrollInterpreter summonTypeInterpreter)
	{
		super (monsterTypeInterpreter, summonTypeInterpreter, EntityType.GIANT);
		this.addEquipment(new GiantBruin(), 100);
	}
}
