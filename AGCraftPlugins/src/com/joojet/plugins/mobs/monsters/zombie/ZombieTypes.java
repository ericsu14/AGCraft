package com.joojet.plugins.mobs.monsters.zombie;

import org.bukkit.entity.EntityType;

import com.joojet.plugins.mobs.interfaces.MonsterTypes;

public class ZombieTypes extends MonsterTypes
{
	public ZombieTypes ()
	{
		super(EntityType.ZOMBIE);
		this.addEquipment(new UncommonZombie(), 85);
		this.addEquipment(new BadassZombie(), 13);
		this.addEquipment(new UltimateBadassZombie(), 2);
		this.addEquipment(new Shrek(), 30);
		this.addEquipment(new BarneyTheDinosaur (), 1);
	}
}
