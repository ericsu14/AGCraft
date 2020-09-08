package com.joojet.plugins.mobs.monsters.zombie.julyfourth;

import org.bukkit.entity.EntityType;

import com.joojet.plugins.mobs.monsters.MonsterTypes;

public class PatrioticZombieTypes extends MonsterTypes 
{
	public PatrioticZombieTypes ()
	{
		super (EntityType.ZOMBIE, EntityType.HUSK);
		this.addEquipment(new PatrioticZombie (), 100);
	}
}
