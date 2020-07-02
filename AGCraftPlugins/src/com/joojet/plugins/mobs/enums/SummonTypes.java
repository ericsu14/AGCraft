package com.joojet.plugins.mobs.enums;

import org.bukkit.entity.EntityType;

import com.joojet.plugins.mobs.interfaces.SummoningScroll;
import com.joojet.plugins.mobs.scrolls.SummonJohnJae;

public enum SummonTypes 
{
	JOHN_JAE (EntityType.IRON_GOLEM);
	
	/** Summoning scroll object */
	private SummoningScroll summon;
	
	private SummonTypes (EntityType type)
	{
		switch (this.name())
		{
			case "JOHN_JAE":
				this.summon = new SummonJohnJae ();
				break;
			default:
				break;
		}
	}
	
	public SummoningScroll getSummon ()
	{
		return this.summon;
	}
	
	@Override
	public String toString ()
	{
		return this.summon.getName();
	}
}
