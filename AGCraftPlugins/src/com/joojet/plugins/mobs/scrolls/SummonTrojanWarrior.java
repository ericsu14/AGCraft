package com.joojet.plugins.mobs.scrolls;

import org.bukkit.entity.EntityType;

import com.joojet.plugins.mobs.monsters.zombie.beatthebruins.TrojanWarrior;

public class SummonTrojanWarrior extends SummoningScroll 
{
	public SummonTrojanWarrior ()
	{
		super (new TrojanWarrior(), EntityType.HUSK);
	}
}
