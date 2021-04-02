package com.joojet.plugins.mobs.enums;

public enum DamageDisplayMode 
{
	/** Damage display module should always activate regardless of server mode */
	ENABLED,
	/** Damage display module is activated iff the server's mode is set to NORMAL */
	AUTO,
	/** Disables the damage display module */
	DISABLED
}
