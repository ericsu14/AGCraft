package com.joojet.plugins.mobs.scrolls;

import org.bukkit.entity.EntityType;

import com.joojet.plugins.mobs.allies.wolf.Snowball;
import com.joojet.plugins.mobs.interfaces.SummoningScroll;

public class SummonSnowball extends SummoningScroll 
{
	public SummonSnowball ()
	{
		super (new Snowball (), EntityType.WOLF);
	}
}
