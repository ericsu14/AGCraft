package com.joojet.plugins.mobs.monsters.phantom.julyfourth;

import org.bukkit.entity.EntityType;

import com.joojet.plugins.mobs.interpreter.MonsterTypeInterpreter;
import com.joojet.plugins.mobs.interpreter.SummoningScrollInterpreter;
import com.joojet.plugins.mobs.monsters.MonsterTypes;

public class JulyFourthPhantomTypes extends MonsterTypes 
{
	public JulyFourthPhantomTypes (MonsterTypeInterpreter interpreter, SummoningScrollInterpreter summonTypeInterpreter)
	{
		super (interpreter, summonTypeInterpreter, EntityType.PHANTOM);
		this.addEquipment(new FireworkPhantom (), 100);
	}
}
