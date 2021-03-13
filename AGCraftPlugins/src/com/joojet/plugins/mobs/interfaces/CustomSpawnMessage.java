package com.joojet.plugins.mobs.interfaces;

public interface CustomSpawnMessage 
{
	/** Outputs a custom String to all players when this monster spawns, which overrides the
	 *  "you felt a disturbance in the force" default message. Custom spawn messages can only be shown
	 *   when the monster has the SPAWN_LIGHTNING flag enabled */
	public String getSpawnMessage ();
}
