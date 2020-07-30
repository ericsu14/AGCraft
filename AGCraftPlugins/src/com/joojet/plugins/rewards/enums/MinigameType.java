package com.joojet.plugins.rewards.enums;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public enum MinigameType 
{
	/** A list of minigame events that has happened in this server */
	UHC_I ("UHC I", "07-31-2020"),
	UHC_I_WINNER ("UHC I", "07-31-2020", "Awarded for dominating in our very first UHC event! Congrats team <team>!"),
	GIFT ("Gift", "now", "A small gift from the administrator!");
	
	/** Full name of the event */
	private String fullName;
	/** The date (in string form) in which this event happened */
	private Calendar date;
	/** Used to display custom lore information */
	private String customLore;
	
	private MinigameType (String fullName, String date)
	{
		this.fullName = fullName;
		this.date = new Calendar.Builder().build();
		this.date.setTime(this.parseDate(date));
		this.customLore = null;
	}
	
	private MinigameType (String fullName, String date, String customLore)
	{
		this.fullName = fullName;
		this.date = new Calendar.Builder().build();
		this.customLore = customLore;
	}
	
	/** Returns a user-friendly name for this event */
	public String getFullName ()
	{
		return this.fullName;
	}
	
	/** Returns the date attached to this object */
	public Calendar getDate ()
	{
		return this.date;
	}
	
	/** Returns lore information that should be included in the lore of
	 * 	reward items */
	public String getFormattedLore ()
	{
		if (this.customLore == null)
		{
			StringBuilder lore = new StringBuilder ();
			lore.append("Rewarded from partaking in ");
			lore.append(fullName);
			lore.append(" on ");
			lore.append((date.get(Calendar.MONTH) + 1));
			lore.append("/");
			lore.append(date.get(Calendar.DAY_OF_MONTH));
			lore.append("/");
			lore.append(date.get(Calendar.YEAR));
			return lore.toString();
		}
		else
		{
			return this.customLore;
		}
	}
	
	/** Returns the ENUM's name for the search trie */
	@Override
	public String toString ()
	{
		return this.name();
	}
	
	/** Converts a datestring formatted in MM-dd-yyyy to
	 *  a date object.
	 *  	@param dateString - Date formatted in MM-dd-yyyy */
	public Date parseDate (String dateString)
	{
		SimpleDateFormat formatter = new SimpleDateFormat("MM-dd-yyyy");
		Date d = null;
		try
		{
			d = formatter.parse(dateString);
		}
		catch (ParseException e)
		{
			e.printStackTrace();
		}
		
		return d;
	}
}
