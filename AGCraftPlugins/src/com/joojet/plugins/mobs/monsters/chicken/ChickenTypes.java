package com.joojet.plugins.mobs.monsters.chicken;

import org.bukkit.entity.EntityType;

import com.joojet.plugins.mobs.interpreter.MonsterTypeInterpreter;
import com.joojet.plugins.mobs.interpreter.SummoningScrollInterpreter;
import com.joojet.plugins.mobs.monsters.MonsterTypes;

public class ChickenTypes extends MonsterTypes {

	public ChickenTypes(MonsterTypeInterpreter monsterTypeInterpreter, SummoningScrollInterpreter summonTypeInterpreter) 
	{
		super(monsterTypeInterpreter, summonTypeInterpreter, EntityType.CHICKEN);
		this.addEquipment(new SuperChicken (), 1);
		this.addEquipment(new CluckFreak (), 0);
		this.addEquipment(new CrazyNugget (), 0);
		this.addEquipment(new FriendChicken (), 0);
		this.addEquipment(new ChickenFighter (), 0);
	}

}
