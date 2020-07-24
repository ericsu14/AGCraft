package com.joojet.plugins.mobs.scrolls;

import org.bukkit.entity.EntityType;

import com.joojet.plugins.mobs.allies.golem.JohnJae;

public class SummonJohnJae extends SummoningScroll
{
	public SummonJohnJae ()
	{
		super (new JohnJae (), EntityType.IRON_GOLEM);
	}
}
