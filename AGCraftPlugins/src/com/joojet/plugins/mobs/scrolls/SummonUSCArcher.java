package com.joojet.plugins.mobs.scrolls;

import org.bukkit.entity.EntityType;

import com.joojet.plugins.mobs.monsters.skeleton.beatthebruins.USCArcher;

public class SummonUSCArcher extends SummoningScroll
{
	public SummonUSCArcher ()
	{
		super (new USCArcher (), EntityType.SKELETON);
	}
}
