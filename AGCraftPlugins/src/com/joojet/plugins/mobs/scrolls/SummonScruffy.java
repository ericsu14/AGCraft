package com.joojet.plugins.mobs.scrolls;

import org.bukkit.entity.EntityType;

import com.joojet.plugins.mobs.allies.snowman.Scruffy;
import com.joojet.plugins.mobs.interfaces.SummoningScroll;

public class SummonScruffy extends SummoningScroll
{
	public SummonScruffy ()
	{
		super (new Scruffy (), EntityType.SNOWMAN);
	}
}
