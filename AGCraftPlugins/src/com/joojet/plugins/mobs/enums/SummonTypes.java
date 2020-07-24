package com.joojet.plugins.mobs.enums;

import com.joojet.plugins.mobs.scrolls.*;

public enum SummonTypes 
{
	JOHN_JAE,
	SCRUFFY,
	ADVANCED_GOLEM,
	FROSTY,
	FROLF,
	COOKIE,
	SNOWBALL;
	
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
			case SNOWBALL:
				return new SummonSnowball ();
			default:
				break;
		}
		
		return null;
	}
	
	@Override
	public String toString ()
	{
		return this.getSummon().getName();
	}
}
