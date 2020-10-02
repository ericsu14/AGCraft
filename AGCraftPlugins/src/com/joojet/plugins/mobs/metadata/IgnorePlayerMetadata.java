package com.joojet.plugins.mobs.metadata;

import java.text.ParseException;
import java.util.Calendar;

import org.bukkit.entity.Player;

/** Players with this metadata enabled will have all hostile mobs ignore it
 *  until the time exceeds its set timestamp */
public class IgnorePlayerMetadata extends CalendarMetadata 
{
	public static final String IGNORE_PLAYER_TAG = "ignore-player";
	
	/** Creates an empty ignore player metadata instance which can be
	 *  used to quickly extract values from a player's metadata container
	 *  without specifying any data. */
	public IgnorePlayerMetadata ()
	{
		super (IGNORE_PLAYER_TAG);
	}
	
	/** Registers a grace period for a set amount of seconds where most non-boss mobs
	 *  cannot target the player for a short period of time.
	 *  This is to allow logged in players to safely load resource packs
	 *  without getting attacked mid-loading.
	 *  @param ignoreTime - Total amount of time where monsters ignore the player
	 *  in seconds. */
	public IgnorePlayerMetadata (int ignoreTime)
	{
		super (IGNORE_PLAYER_TAG);
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.SECOND, ignoreTime);
		this.value = calendar.getTime();
	}
	
	/** Returns true if the player is still under an active grace period,
	 *  telling the mob to cancel their target selecting events againt him/her.
	 *  @param player - Player object being checked.*/
	public boolean canIgnorePlayer (Player player)
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
