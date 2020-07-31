package com.joojet.plugins.utility.enums;

public enum JunkClassifier 
{
	COMMON, STONE, BREWING, ARMOR, NATURAL, WEAPONS, NETHER, ALL;
	
	/** Returns the enum's name as a string */
	@Override
	public String toString ()
	{
		return this.name();
	}
	
	/** Returns the enum's config file key */
	public String getKey ()
	{
		return this.toString().toLowerCase();
	}
}
