package com.joojet.plugins.mobs.monsters.zombie_pigmen;

import org.bukkit.entity.EntityType;

import com.joojet.plugins.mobs.interpreter.MonsterTypeInterpreter;
import com.joojet.plugins.mobs.interpreter.SummoningScrollInterpreter;
import com.joojet.plugins.mobs.monsters.MonsterTypes;

public class ZombiePigmenTypes extends MonsterTypes 
{
	public ZombiePigmenTypes (MonsterTypeInterpreter monsterTypeInterpreter, SummoningScrollInterpreter summonTypeInterpreter)
	{
		super (monsterTypeInterpreter, summonTypeInterpreter, EntityType.ZOMBIFIED_PIGLIN);
		this.addEquipment(new VeteranZombiePigmen(), 93);
		this.addEquipment(new AkimboPigman(), 6);
		this.addEquipment(new TheTerminator(), 1);
	}
}
