package com.joojet.plugins.mobs.scrolls;

import org.bukkit.entity.EntityType;

import com.joojet.plugins.mobs.allies.wolf.Cookie;

public class SummonCookie extends SummoningScroll
{
	public SummonCookie ()
	{
		super (new Cookie(), EntityType.WOLF);
	}
}
