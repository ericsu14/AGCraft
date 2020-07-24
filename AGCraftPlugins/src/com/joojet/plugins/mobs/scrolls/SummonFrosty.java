package com.joojet.plugins.mobs.scrolls;

import org.bukkit.entity.EntityType;

import com.joojet.plugins.mobs.allies.snowman.Frosty;

public class SummonFrosty extends SummoningScroll 
{
	public SummonFrosty ()
	{
		super (new Frosty(), EntityType.SNOWMAN);
	}
}
