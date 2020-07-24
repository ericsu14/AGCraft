package com.joojet.plugins.mobs.scrolls;

import org.bukkit.entity.EntityType;

import com.joojet.plugins.mobs.allies.snowman.Scruffy;

public class SummonScruffy extends SummoningScroll
{
	public SummonScruffy ()
	{
		super (new Scruffy (), EntityType.SNOWMAN);
	}
}
