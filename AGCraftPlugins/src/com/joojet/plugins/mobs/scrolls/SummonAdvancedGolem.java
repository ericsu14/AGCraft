package com.joojet.plugins.mobs.scrolls;

import org.bukkit.entity.EntityType;

import com.joojet.plugins.mobs.allies.golem.AdvancedGolem;
import com.joojet.plugins.mobs.interfaces.SummoningScroll;

public class SummonAdvancedGolem extends SummoningScroll
{
	public SummonAdvancedGolem ()
	{
		super (new AdvancedGolem (), EntityType.IRON_GOLEM);
	}
}
