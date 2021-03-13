package com.joojet.plugins.rewards.enums;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public enum MinigameRewardType 
{
	/** A list of minigame events that has happened in this server */
	UHC_I ("UHC I", "08-01-2020"),
	UHC_I_WINNER ("UHC I - Round 1", "08-01-2020", "Awarded for dominating in the first round of our very first UHC event! Congrats team men!"),
	UHC_I_WINNNER_I ("UHC I - Round 2", "08-01-2020", "Awarded for dominating in the second round of our very first UHC event! Congrats team Team 19!"),
	UHC_RUSH ("UHC Rush", "08-26-2020"),
	UHC_RUSH_REWARDS ("UHC Rush", "08-26-2020"),
	UHC_RUSH_WINNER ("UHC Rush - Winner", "08-26-2020", "Awarded for dominating a round of UHC during AGO's very own rush week! Fight on!"),
	GIFT ("Gift", "now", "A small gift from the administrator!"),
	JASON_BIRTHDAY ("Birthday Gift - Jason", "09-23-2020", "Happy birthday sprinkles! Thanks for being an everlasting light in this community!"
			+ " Hope you have an amazing day today and best of wishes in your job and small group leading! ~ jooj"),
	JOOJ_BIRTHDAY ("Birthday Gift - Eric", "11-20-2020", "Happy birthday to myself, hehe! ~ jooj"),
	CHRISTMAS ("Belated Christmas", "12-26-2020", "Merry (belated) Christmas everyone! ~jooj"),
	HUNGER_GAMES_ENTERTAINMENT ("Hunger Games by Alpha Omicron", "3-15-2020");
	
	/** Full name of the event */
	private String fullName;
	/** The date (in string form) in which this event happened */
	private Calendar date;
	/** Used to display custom lore information */
	private String customLore;
	
	private MinigameRewardType (String fullName, String date)
	{
		this.fullName = fullName;
		this.date = new Calendar.Builder().build();
		this.date.setTime(this.parseDate(date));
		this.customLore = null;
	}
	
	private MinigameRewardType (String fullName, String date, String customLore)
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
	
	/** Returns the key used to identify where this entry is in the YAML config file */
	public static String getKey ()
	{
		return "participation-reward-type";
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
