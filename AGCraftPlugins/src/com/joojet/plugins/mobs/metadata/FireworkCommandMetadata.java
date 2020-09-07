package com.joojet.plugins.mobs.metadata;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.bukkit.persistence.PersistentDataHolder;

public class FireworkCommandMetadata extends AbstractMetadata<Date> 
{
	public static String FIREWORK_COMMAND_TAG = "firework-command-tag";
	
	public FireworkCommandMetadata ()
	{
		super (FIREWORK_COMMAND_TAG, Calendar.getInstance().getTime());
	}
	
	/** Creates a new instance of a metadata value used for the firework command,
	 *  keeping track of the next available usage for the next firework show.
	 *  This is to make sure a player cannot run multiple firework shows at once, 
	 *  otherwise it might induce lag.
	 *  @param calendar - Passed calendar instance */
	public FireworkCommandMetadata (Calendar calendar)
	{
		super (FIREWORK_COMMAND_TAG, calendar.getTime());
	}
	
	/** Gets calendar metadata stored within the holder if it exists.
	 * 	@param holder - The Persistent Data Holder instance containing the stored timestamp data.
	 *  @throws ParseException if the timestamp is incorrectly stored */
	public Calendar getCalendarFromHolder (PersistentDataHolder holder) throws ParseException
	{
		String data = this.getStringMetadata(holder);
		if (data != null)
		{
			Date date = new SimpleDateFormat ("EEE MMM d HH:mm:ss z yyyy").parse(data);
			
			if (date != null)
			{
				Calendar calendar = Calendar.getInstance();
				calendar.setTime(date);
				return calendar;
			}
		}
		
		return null;
	}
}
