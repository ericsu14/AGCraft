package com.joojet.plugins.mobs.monsters.ghast.UHC;

import org.bukkit.entity.EntityType;

import com.joojet.plugins.mobs.monsters.MonsterTypes;

/** In UHC, ghasts are modified to drop Golden Ignots instead of tears */
public class UHCGhastTypes extends MonsterTypes
{
	public UHCGhastTypes ()
	{
		super (EntityType.GHAST);
		this.addEquipment(new UHCGhast(), 100);
	}
}
