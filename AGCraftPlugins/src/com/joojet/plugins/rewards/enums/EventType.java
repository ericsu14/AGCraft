package com.joojet.plugins.rewards.enums;

public enum EventType 
{
	/** A list of minigame events that has happened in this server */
	UHC_I ("UHC I", "7/9/2020");
	
	private String fullName;
	private String date;
	
	private EventType (String fullName, String date)
	{
		this.fullName = fullName;
		this.date = date;
	}
	
	public String getFullName ()
	{
		return this.fullName;
	}
	
	public String getDate ()
	{
		return this.date;
	}
	
	public String getFormattedLore ()
	{
		StringBuilder lore = new StringBuilder ();
		lore.append("Rewarded from partaking in ");
		lore.append(fullName);
		lore.append(" on ");
		lore.append(date);
		return lore.toString();
	}
	
	@Override
	public String toString ()
	{
		return this.fullName;
	}
}
