package com.joojet.plugins.mobs.monsters.phantom.beatthebruins;

import org.bukkit.entity.EntityType;

import com.joojet.plugins.mobs.interpreter.MonsterTypeInterpreter;
import com.joojet.plugins.mobs.interpreter.SummoningScrollInterpreter;
import com.joojet.plugins.mobs.monsters.MonsterTypes;

public class BeatTheBruinPhantomTypes extends MonsterTypes 
{
	public BeatTheBruinPhantomTypes (MonsterTypeInterpreter mobInterpreter, SummoningScrollInterpreter summonTypeInterpreter)
	{
		super (mobInterpreter, summonTypeInterpreter, EntityType.PHANTOM);
		this.addEquipment(new PhantomMenace(), 100);
	}
}
