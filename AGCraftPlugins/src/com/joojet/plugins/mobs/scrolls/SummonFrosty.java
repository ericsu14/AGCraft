package com.joojet.plugins.mobs.scrolls;

import org.bukkit.entity.EntityType;

import com.joojet.plugins.mobs.allies.snowman.Frosty;
import com.joojet.plugins.mobs.interfaces.SummoningScroll;

public class SummonFrosty extends SummoningScroll 
{
	public SummonFrosty ()
	{
		super (new Frosty(), EntityType.SNOWMAN);
	}
}
