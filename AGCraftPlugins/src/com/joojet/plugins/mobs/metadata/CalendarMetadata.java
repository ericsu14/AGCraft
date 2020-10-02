package com.joojet.plugins.mobs.metadata;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.bukkit.persistence.PersistentDataHolder;

public abstract class CalendarMetadata extends AbstractMetadata<Date> 
{
	/** Creates a new Calendar metadata instance storing the current time
	 *  @param tag - Tag identifier for the entity's metadata */
	public CalendarMetadata(String tag) 
	{
		super(tag, Calendar.getInstance().getTime());
	}
	
	/** Creates a new Calendar metadata instance with a set timestamp referenced by Calendar
	 *  @param tag - Tag identifier for the entity's metadata
	 *  @param calendar - Calendar object containing a timestamp to be stored */
	public CalendarMetadata (String tag, Calendar calendar)
	{
		super (tag, calendar.getTime());
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
