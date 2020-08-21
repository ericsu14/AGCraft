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
	SNOWBALL,
	THE_DOOM_SLAYER,
	BARNEY;
	
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
			case THE_DOOM_SLAYER:
				return new SummonDoomGuy();
			case BARNEY:
				return new SummonBarney();
			default:
				break;
		}
		
		return null;
	}
	
	/** Returns the summon's monster type as a String */
	@Override
	public String toString ()
	{
		return this.getSummon().getMobType().toString();
	}
	
	/** Used for legacy scrolls that uses the monster's name
	 *  in their localized meta */
	public String getMonsterName ()
	{
		return this.getSummon().getName();
	}
}
