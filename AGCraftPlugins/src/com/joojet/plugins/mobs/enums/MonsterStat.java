package com.joojet.plugins.mobs.enums;

import org.bukkit.attribute.Attribute;

public enum MonsterStat 
{
	/** Entity's base health */
	HEALTH (Attribute.GENERIC_MAX_HEALTH),
	/** The amount of EXP dropped from this entity */
	EXPERIENCE,
	/** The max. radius used for determining which nearby player to hunt when the flag HUNT_ON_SPAWN is enabled */
	HUNT_ON_SPAWN_RADIUS,
	/** Modifies the entity's base attack damage */
	BASE_ATTACK_DAMAGE (Attribute.GENERIC_ATTACK_DAMAGE),
	/** Used internally by the MonsterTypes class to assign spawn weights to the entity */
	SPAWN_WEIGHT,
	/** The minimum y value the original entity must be in order for this entity to spawn */
	Y_LIMIT,
	/** Stores the enum ordinal relating to the monster's horse color */
	HORSE_COLOR,
	/** Stores the enum ordinal relating to the monster's horse style */
	HORSE_STYLE,
	/** Modifies the jump strength if this custom mob is a horse */
	HORSE_JUMP_STRENGTH (Attribute.HORSE_JUMP_STRENGTH),
	/** Modifies the base speed of the mob */
	BASE_SPEED (Attribute.GENERIC_MOVEMENT_SPEED);
	
	/** The Minecraft entity attribute this monsterstat is tied to */
	private Attribute attribute;
	
	private MonsterStat ()
	{
		this.attribute =  null;
	}
	
	private MonsterStat (Attribute attribute)
	{
		this.attribute = attribute;
	}
	
	/** Returns true if this monster stat is tied to a Minecraft entity attribute */
	public boolean containsAttribute ()
	{
		return this.attribute != null;
	}
	
	/** Returns the Minecraft entity attribute tied to this monster stat */
	public Attribute getAttribute ()
	{
		return this.attribute;
	}
}
