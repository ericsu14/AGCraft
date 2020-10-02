package com.joojet.plugins.mobs.metadata;

import java.text.ParseException;
import java.util.Calendar;

import org.bukkit.entity.Player;

/** Players with this metadata enabled will have all hostile mobs ignore it
 *  until the time exceeds its set timestamp */
public class IgnorePlayerMetadata extends CalendarMetadata 
{
	public static final String IGNORE_PLAYER_TAG = "ignore-player";
	
	public IgnorePlayerMetadata ()
	{
		super (IGNORE_PLAYER_TAG);
	}
	
	public IgnorePlayerMetadata (int ignoreTime)
	{
		super (IGNORE_PLAYER_TAG);
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.SECOND, ignoreTime);
		this.value = calendar.getTime();
	}
	
	public boolean ignorePlayer (Player player)
	{
		try
		{
			Calendar lastUse = this.getCalendarFromHolder(player);
			Calendar now = Calendar.getInstance();
			return (now.compareTo(lastUse) < 0);
		}
		catch (ParseException pe)
		{
			pe.printStackTrace();
		}
		return false;
	}
}
