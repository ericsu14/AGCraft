package com.joojet.plugins.mobs.monsters.ghast.UHC;

import org.bukkit.entity.EntityType;

import com.joojet.plugins.mobs.interpreter.MonsterTypeInterpreter;
import com.joojet.plugins.mobs.interpreter.SummoningScrollInterpreter;
import com.joojet.plugins.mobs.monsters.MonsterTypes;

/** In UHC, ghasts are modified to drop Golden Ignots instead of tears */
public class UHCGhastTypes extends MonsterTypes
{
	public UHCGhastTypes (MonsterTypeInterpreter monsterTypeInterpreter, SummoningScrollInterpreter summonTypeInterpreter)
	{
		super (monsterTypeInterpreter, summonTypeInterpreter, EntityType.GHAST);
		this.addEquipment(new UHCGhast(), 100);
	}
}
