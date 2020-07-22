package com.joojet.plugins.mobs.enums;

import com.joojet.plugins.mobs.interfaces.SummoningScroll;
import com.joojet.plugins.mobs.scrolls.*;

public enum SummonTypes 
{
	JOHN_JAE,
	SCRUFFY,
	ADVANCED_GOLEM,
	FROSTY,
	FROLF,
	COOKIE;
	
	public SummoningScroll getSummon ()
	{
		switch (this)
		{
			case JOHN_JAE:
				return new SummonJohnJae ();
			case SCRUFFY:
				return new SummonScruffy();
			case ADVANCED_GOLEM:
				return new SummonAdvancedGolem();
			case FROSTY:
				return new SummonFrosty();
			case FROLF:
				return new SummonFrolf ();
			case COOKIE:
				return new SummonCookie();
		}
		
		return null;
	}
	
	@Override
	public String toString ()
	{
		return this.getSummon().getName();
	}
}
