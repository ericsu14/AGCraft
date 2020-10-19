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
	BARNEY,
	SKULL_KID,
	SPIRIT_OF_TROY,
	USC_ARCHER,
	AG_SPOTTED,
	UCLA_BEAR_TAMER,
	GIANT_BRUIN_TAMER,
	USC_WARRIOR,
	TROJAN_WARRIOR,
	ETERNAL_TROJAN_ARCHER,
	SHADOW_CLONE_JOOJETSU,
	THE_TERMINATOR,
	JOHNNY_RUSNAK,
	HELL_WALKER,
	SOUL_OBLITERATOR;
	
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
			case SKULL_KID:
				return new SummonSkullKid();
			case SPIRIT_OF_TROY:
				return new SummonSpiritOfTroy();
			case USC_ARCHER:
				return new SummonUSCArcher();
			case AG_SPOTTED:
				return new SummonAGSpotted();
			case UCLA_BEAR_TAMER:
				return new SummonUCLABearTamer();
			case GIANT_BRUIN_TAMER:
				return new SummonGiantBruinTamer();
			case USC_WARRIOR:
				return new SummonUSCWarrior();
			case TROJAN_WARRIOR:
				return new SummonTrojanWarrior();
			case ETERNAL_TROJAN_ARCHER:
				return new SummonEternalTrojanArcher();
			case SHADOW_CLONE_JOOJETSU:
				return new SummonShadowClonejoojetsu();
			case THE_TERMINATOR:
				return new SummonTheTerminator();
			case JOHNNY_RUSNAK:
				return new SummonJohnnyRusnak();
			case HELL_WALKER:
				return new SummonHellWalker ();
			case SOUL_OBLITERATOR:
				return new SummonSoulObliterator();
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
