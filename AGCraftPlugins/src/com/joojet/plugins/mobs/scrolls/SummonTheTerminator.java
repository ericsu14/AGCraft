package com.joojet.plugins.mobs.scrolls;

import org.bukkit.entity.EntityType;

import com.joojet.plugins.mobs.monsters.zombie_pigmen.TheTerminator;

public class SummonTheTerminator extends BossScroll 
{
	public SummonTheTerminator ()
	{
		super (new TheTerminator(), EntityType.ZOMBIFIED_PIGLIN);
	}
}
