package com.joojet.plugins.music.duration;

/** A simple representation of a music's duration.
 *  This class parses a string formatted in
 *  MM:SS into their respective minutes and seconds
 *  and converts the parsed data into Minecraft game ticks. */
public class MusicDuration 
{
	/** Amount of minutes found in song length */
	protected int minutes;
	/** Amount of seconds found in song length */
	protected int seconds;
	/** Song duration in Minecraft game ticks */
	protected int ticks;
	/** Stores the original simple duration string */
	protected String durationString;
	
	/** Takes in a music duration String and converts it into minecraft
	 *  game ticks */
	public MusicDuration (String duration)
	{
		String [] tokens = duration.split(":");
		
		// This class strictly requires the duration string to be in the format of MM:SS.
		// All else will fail silently
		if (tokens.length == 2)
		{
			this.minutes = Integer.parseInt(tokens[0]);
			this.seconds = Integer.parseInt(tokens[1]);
		}
		else
		{
			this.minutes = 0;
			this.seconds = 0;
		}
		this.ticks = this.convertTimeToTicks();
		this.durationString = duration;
	}
	
	/** Converts the amount of minutes and seconds stored in this class to
	 *  game ticks. */
	protected int convertTimeToTicks ()
	{
		return ((minutes * 60) * 20) + (seconds * 20);
	}
	
	/** Gets the total time in Minecraft game ticks */
	public int getTicks ()
	{
		return this.ticks;
	}
	
	/** Gets the amount of minutes specified in the duration string */
	public int getMinutes ()
	{
		return this.minutes;
	}
	
	/** Gets the amount of seconds specified in the duration string */
	public int getSeconds ()
	{
		return this.seconds;
	}
	
	@Override
	public String toString ()
	{
		return this.durationString;
	}
}
