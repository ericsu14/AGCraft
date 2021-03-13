package com.joojet.plugins.mobs.monsters.zombie.hungergames;

import org.bukkit.entity.EntityType;

import com.joojet.plugins.mobs.interpreter.MonsterTypeInterpreter;
import com.joojet.plugins.mobs.interpreter.SummoningScrollInterpreter;
import com.joojet.plugins.mobs.monsters.MonsterTypes;

public class HGZombieTypes extends MonsterTypes {

	public HGZombieTypes(MonsterTypeInterpreter monsterTypeInterpreter,
			SummoningScrollInterpreter summonTypeInterpreter) 
	{
		super(monsterTypeInterpreter, summonTypeInterpreter, EntityType.ZOMBIE, 
				EntityType.HUSK, EntityType.ZOMBIE_VILLAGER);
		
		this.addEquipment(new HGUCLAJock (), 60);
		this.addEquipment(new HGUSCWarrior (), 25);
		this.addEquipment(new HGSpiritOfTroy (), 15);
	}

}
