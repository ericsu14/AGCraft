package com.joojet.plugins.mobs.monsters.creeper.julyfourth;

import org.bukkit.entity.EntityType;

import com.joojet.plugins.mobs.interpreter.MonsterTypeInterpreter;
import com.joojet.plugins.mobs.interpreter.SummoningScrollInterpreter;
import com.joojet.plugins.mobs.monsters.MonsterTypes;

public class JulyFourthCreeperTypes extends MonsterTypes 
{

	public JulyFourthCreeperTypes(MonsterTypeInterpreter monsterTypeInterpreter,
			SummoningScrollInterpreter summonTypeInterpreter) 
	{
		super(monsterTypeInterpreter, summonTypeInterpreter, EntityType.CREEPER);
		this.addEquipment(new PatrioticCreeper (), 100);
	}

}
