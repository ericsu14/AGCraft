package com.joojet.plugins.mobs.scrolls;

import org.bukkit.entity.EntityType;

import com.joojet.plugins.mobs.monsters.zombie.beatthebruins.GiantBruinTamer;

public class SummonGiantBruinTamer extends BossScroll 
{
	public SummonGiantBruinTamer() 
	{
		super(new GiantBruinTamer(), EntityType.ZOMBIE);
	}
}
