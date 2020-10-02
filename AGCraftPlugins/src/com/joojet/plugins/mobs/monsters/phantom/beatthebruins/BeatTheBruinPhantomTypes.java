package com.joojet.plugins.mobs.monsters.phantom.beatthebruins;

import org.bukkit.entity.EntityType;

import com.joojet.plugins.mobs.interpreter.MonsterTypeInterpreter;
import com.joojet.plugins.mobs.monsters.MonsterTypes;

public class BeatTheBruinPhantomTypes extends MonsterTypes 
{
	public BeatTheBruinPhantomTypes (MonsterTypeInterpreter mobInterpreter)
	{
		super (mobInterpreter, EntityType.PHANTOM);
		this.addEquipment(new PhantomMenace(), 100);
	}
}
