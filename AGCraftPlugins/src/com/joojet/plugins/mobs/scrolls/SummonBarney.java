package com.joojet.plugins.mobs.scrolls;

import org.bukkit.entity.EntityType;

import com.joojet.plugins.mobs.monsters.zombie.BarneyTheDinosaur;

public class SummonBarney extends SummoningScroll
{
	public SummonBarney() 
	{
		super(new BarneyTheDinosaur (), EntityType.ZOMBIE);
	}
}
