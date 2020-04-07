package com.joojet.plugins.warp.constants;

public enum WarpType 
{
	HOME, LOCATION, PLAYER;
	
	
	/** Returns the enum's name as a string */
	@Override
	public String toString ()
	{
		return this.name();
	}
}
