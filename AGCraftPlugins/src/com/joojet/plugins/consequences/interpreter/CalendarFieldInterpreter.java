package com.joojet.plugins.consequences.interpreter;

import com.joojet.plugins.agcraft.interfaces.AbstractInterpreter;
import com.joojet.plugins.consequences.enums.CalendarField;

public class CalendarFieldInterpreter extends AbstractInterpreter <CalendarField>
{
	public CalendarFieldInterpreter ()
	{
		super (CalendarField.values());
	}
}
