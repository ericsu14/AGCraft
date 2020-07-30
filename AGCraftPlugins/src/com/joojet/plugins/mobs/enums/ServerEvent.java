package com.joojet.plugins.mobs.enums;

/** Defines the type of server-wide event we are in, which can add additional
 *  themed mob spawns. */
public enum ServerEvent 
{
	DEFAULT, JULY_FOURTH;
	
	/** Returns the key used to identify where this entry is in the YAML config file */
	public static String getKey ()
	{
		return "server-event-mode";
	}
}
