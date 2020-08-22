package com.joojet.plugins.mobs.enums;

public enum MobFlag 
{
	/** The entity will spawn with a permanent burning effect */
	ON_FIRE,
	/** The entity will have its nametag visible to everyone */
	SHOW_NAME,
	/** A lightning bolt is summoned upon spawning the monster */
	SPAWN_LIGHTNING,
	/** Determines if the monster should automatically hunt a random nearby player upon spawning */
	HUNT_ON_SPAWN,
	/** Displays a boss bar for this custom mob */
	BOSS_BAR,
	/** When enabled, the monster will never lose sight of its prey (meaning that it will never "forget" its enemy) */
	PERSISTENT_ATTACKER;
}
