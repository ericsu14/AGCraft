package com.joojet.plugins.mobs.monsters.components.factions;

import java.util.EnumSet;

import com.joojet.plugins.mobs.enums.Faction;

/** Describes an abstract faction a custom monster can be apart of */
public abstract class AbstractFaction 
{
	/** Identifier for this faction instance */
	protected Faction faction;
	/** A list of factions that this monster is set to target.
	 *  If this set has at least one value inserted, the monster will only attack
	 *  entities whose factions are in this list. If the target in question is not part
	 *  of any faction, but in this custom monster's hitlist, that target will be hunted down as well
	 *  unless this custom monster has the IGNORE_NON_FACTIONED_MOBS flag enabled. */
	protected EnumSet <Faction> rivalFactions;
	
	public AbstractFaction (Faction faction)
	{
		this.faction = faction;
		this.rivalFactions = EnumSet.noneOf(Faction.class);
	}
	
	/** Adds a list of rivaling factions */
	public void addRivalFactions (Faction... factions)
	{
		for (Faction faction : factions)
		{
			this.rivalFactions.add(faction);
		}
	}
	
	/** Returns this faction's set of rivaling factions */
	public EnumSet <Faction> getRivalFactions ()
	{
		return this.rivalFactions;
	}
	
	/** Returns the Faction's identifier */
	public Faction getFactionIdentifier ()
	{
		return this.faction;
	}
	
	/** Checks if this faction is in the other faction's list of rivaling factions
	 *   @param otherFaction - Faction being checked against this instance */
	public boolean isRivalsOf (AbstractFaction otherFaction)
	{
		return  otherFaction.getRivalFactions().
				contains(this.getFactionIdentifier());
	}
	
	@Override
	public String toString ()
	{
		return this.faction.toString();
	}

}
