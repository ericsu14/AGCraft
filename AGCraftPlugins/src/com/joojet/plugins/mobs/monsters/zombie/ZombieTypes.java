package com.joojet.plugins.mobs.monsters.zombie;

import org.bukkit.entity.EntityType;

import com.joojet.plugins.mobs.interpreter.MonsterTypeInterpreter;
import com.joojet.plugins.mobs.interpreter.SummoningScrollInterpreter;
import com.joojet.plugins.mobs.monsters.MonsterTypes;

public class ZombieTypes extends MonsterTypes
{
	public ZombieTypes (MonsterTypeInterpreter monsterTypeInterpreter, SummoningScrollInterpreter summonTypeInterpreter)
	{
		super(monsterTypeInterpreter, summonTypeInterpreter, EntityType.ZOMBIE);
		this.addEquipment(new UncommonZombie(), 45);
		this.addEquipment(new StrongZombie(), 40);
		this.addEquipment(new BadassZombie(), 13);
		this.addEquipment(new UltimateBadassZombie(), 2);
		this.addEquipment(new Shrek(), 30);
		this.addEquipment(new BarneyTheDinosaur (), 1);
	}
}
