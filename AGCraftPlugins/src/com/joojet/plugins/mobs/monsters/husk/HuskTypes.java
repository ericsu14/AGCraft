package com.joojet.plugins.mobs.monsters.husk;

import org.bukkit.entity.EntityType;

import com.joojet.plugins.mobs.interpreter.MonsterTypeInterpreter;
import com.joojet.plugins.mobs.interpreter.SummoningScrollInterpreter;
import com.joojet.plugins.mobs.monsters.MonsterTypes;

public class HuskTypes extends MonsterTypes
{
	public HuskTypes (MonsterTypeInterpreter monsterTypeInterpreter, SummoningScrollInterpreter summonTypeInterpreter)
	{
		super (monsterTypeInterpreter, summonTypeInterpreter, EntityType.HUSK);
		this.addEquipment(new WanderingHusk(), 85);
		this.addEquipment(new FallenPharaoh (), 15);
	}
}
