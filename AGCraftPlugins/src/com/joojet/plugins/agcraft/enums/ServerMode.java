package com.joojet.plugins.agcraft.enums;

public enum ServerMode 
{
	NORMAL, UHC, MINIGAME, HUNGER_GAMES;
	
	/** Returns the key used to identify where this entry is in the YAML config file */
	public static String getKey ()
	{
		return "server-mode";
	}
}
