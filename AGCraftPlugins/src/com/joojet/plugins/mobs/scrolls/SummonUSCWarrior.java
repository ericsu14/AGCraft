package com.joojet.plugins.mobs.scrolls;

import org.bukkit.entity.EntityType;

import com.joojet.plugins.mobs.monsters.zombie.beatthebruins.USCWarrior;

public class SummonUSCWarrior extends SummoningScroll
{
	public SummonUSCWarrior() 
	{
		super(new USCWarrior(), EntityType.HUSK);
	}
}
