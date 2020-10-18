package com.joojet.plugins.mobs.monsters.hoglins;

import org.bukkit.entity.EntityType;

import com.joojet.plugins.mobs.interpreter.MonsterTypeInterpreter;
import com.joojet.plugins.mobs.monsters.MonsterTypes;

public class HoglinTypes extends MonsterTypes 
{
	public HoglinTypes (MonsterTypeInterpreter monsterTypeInterpreter)
	{
		super (monsterTypeInterpreter, EntityType.HOGLIN);
		this.addEquipment(new HoglinBeast (), 85);
		this.addEquipment(new EnragedHoglinBeast (), 15);
	}
}
