package com.joojet.plugins.agcraft.enums;

public enum ServerMode 
{
	NORMAL, UHC, MINIGAME;
	
	/** Returns the key used to identify where this entry is in the YAML config file */
	public String getKey ()
	{
		return "server-mode";
	}
}
