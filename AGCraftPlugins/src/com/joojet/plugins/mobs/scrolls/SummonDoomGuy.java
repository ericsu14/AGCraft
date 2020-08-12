package com.joojet.plugins.mobs.scrolls;

import org.bukkit.entity.EntityType;

import com.joojet.plugins.mobs.monsters.wither_skeleton.DoomGuy;

public class SummonDoomGuy extends SummoningScroll 
{
	public SummonDoomGuy ()
	{
		super (new DoomGuy(), EntityType.WITHER_SKELETON);
	}
}
