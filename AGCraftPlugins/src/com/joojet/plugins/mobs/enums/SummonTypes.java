package com.joojet.plugins.mobs.enums;

import com.joojet.plugins.mobs.interfaces.SummoningScroll;
import com.joojet.plugins.mobs.scrolls.*;

public enum SummonTypes 
{
	JOHN_JAE,
	SCRUFFY,
	ADVANCED_GOLEM,
	FROSTY;
	
	/** Summoning scroll object */
	private SummoningScroll summon;
	
	private SummonTypes ()
	{
		switch (this.name())
		{
			case "JOHN_JAE":
				this.summon = new SummonJohnJae ();
				break;
			case "SCRUFFY":
				this.summon = new SummonScruffy();
				break;
			case "ADVANCED_GOLEM":
				this.summon = new SummonAdvancedGolem();
				break;
			case "FROSTY":
				this.summon = new SummonFrosty();
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
