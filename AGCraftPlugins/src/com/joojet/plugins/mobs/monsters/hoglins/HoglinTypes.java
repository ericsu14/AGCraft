package com.joojet.plugins.mobs.monsters.hoglins;

import org.bukkit.entity.EntityType;

import com.joojet.plugins.mobs.interpreter.MonsterTypeInterpreter;
import com.joojet.plugins.mobs.interpreter.SummoningScrollInterpreter;
import com.joojet.plugins.mobs.monsters.MonsterTypes;

public class HoglinTypes extends MonsterTypes 
{
	public HoglinTypes (MonsterTypeInterpreter monsterTypeInterpreter, SummoningScrollInterpreter summonTypeInterpreter)
	{
		super (monsterTypeInterpreter, summonTypeInterpreter, EntityType.HOGLIN);
		this.addEquipment(new HoglinBeast (), 95);
		this.addEquipment(new EnragedHoglinBeast (), 5);
	}
}
