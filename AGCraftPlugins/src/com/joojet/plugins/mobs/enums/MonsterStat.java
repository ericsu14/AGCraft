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
	SPAWN_WEIGHT
}
