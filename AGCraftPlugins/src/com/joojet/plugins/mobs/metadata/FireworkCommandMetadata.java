package com.joojet.plugins.mobs.metadata;

import java.util.Calendar;

public class FireworkCommandMetadata extends CalendarMetadata
{
	public static String FIREWORK_COMMAND_TAG = "firework-command-tag";
	
	public FireworkCommandMetadata ()
	{
		super (FIREWORK_COMMAND_TAG);
	}
	
	/** Creates a new instance of a metadata value used for the firework command,
	 *  keeping track of the next available usage for the next firework show.
	 *  This is to make sure a player cannot run multiple firework shows at once, 
	 *  otherwise it might induce lag.
	 *  @param calendar - Passed calendar instance */
	public FireworkCommandMetadata (Calendar calendar)
	{
		super (FIREWORK_COMMAND_TAG, calendar);
	}
}
