package com.joojet.plugins.mobs.monsters.zombie.julyfourth;

import org.bukkit.entity.EntityType;

import com.joojet.plugins.mobs.interpreter.MonsterTypeInterpreter;
import com.joojet.plugins.mobs.interpreter.SummoningScrollInterpreter;
import com.joojet.plugins.mobs.monsters.MonsterTypes;

public class PatrioticZombieTypes extends MonsterTypes 
{
	public PatrioticZombieTypes (MonsterTypeInterpreter monsterTypeInterpreter, SummoningScrollInterpreter summonTypeInterpreter)
	{
		super (monsterTypeInterpreter, summonTypeInterpreter, EntityType.ZOMBIE, EntityType.HUSK);
		this.addEquipment(new PatrioticZombie (), 100);
	}
}
