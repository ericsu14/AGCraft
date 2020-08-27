package com.joojet.plugins.mobs.monsters.husk;

import org.bukkit.entity.EntityType;

import com.joojet.plugins.mobs.monsters.MonsterTypes;

public class HuskTypes extends MonsterTypes
{
	public HuskTypes ()
	{
		super (EntityType.HUSK);
		this.addEquipment(new WanderingHusk(), 85);
		this.addEquipment(new FallenPharaoh (), 15);
	}
}
