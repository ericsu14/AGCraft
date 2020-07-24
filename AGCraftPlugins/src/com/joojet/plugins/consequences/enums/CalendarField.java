package com.joojet.plugins.consequences.enums;

import java.util.Calendar;

public enum CalendarField 
{
	SECONDS, MINUTES, HOURS, DAYS;
	
	/** Returns the Calendar field as an integer associated with the Calendar Field's type */
	public int getField ()
	{
		switch (this)
		{
			case SECONDS:
				return Calendar.SECOND;
			case MINUTES:
				return Calendar.MINUTE;
			case HOURS:
				return Calendar.HOUR_OF_DAY;
			case DAYS:
				return Calendar.DAY_OF_MONTH;
		}
		return Calendar.SECOND;
	}
}
