package com.joojet.plugins.mobs.monsters.ghast;

import com.joojet.plugins.mobs.interfaces.MonsterTypes;

/** In UHC, ghasts are modified to drop Golden Ignots instead of tears */
public class UHCGhastTypes extends MonsterTypes
{
	public UHCGhastTypes ()
	{
		this.addEquipment(new UHCGhast(), 100);
	}
}
