package com.joojet.plugins.mobs.enums;

public enum MonsterStat 
{
	/** Entity's base health */
	HEALTH,
	/** The amount of EXP dropped from this entity */
	EXPERIENCE,
	/** The max. radius used for determining which nearby player to hunt when the flag HUNT_ON_SPAWN is enabled */
	HUNT_ON_SPAWN_RADIUS,
	/** Modifies the entity's base attack damage */
	BASE_ATTACK_DAMAGE,
	/** Used internally by the MonsterTypes class to assign spawn weights to the entity */
	SPAWN_WEIGHT,
	/** The minimum y value the original entity must be in order for this entity to spawn */
	Y_LIMIT,
	/** Stores the enum ordinal relating to the monster's horse color */
	HORSE_COLOR,
	/** Stores the enum ordinal relating to the monster's horse style */
	HORSE_STYLE;
}
