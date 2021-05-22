package com.joojet.plugins.mobs.skills.enums;

/** Determines the type of entities the skill should target */
public enum TargetSelector 
{
	/* The custom skill selects all nearby entities around a given radius */
	ALL,
	/* The custom skill only selects allied entities around a given radius */
	ALLIES,
	/* The custom skill only selects enemy entities around a given radius */
	ENEMIES,
	/* The custom skill only selects the skillcaster itself */
	SELF
}
