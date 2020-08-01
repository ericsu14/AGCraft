package com.joojet.plugins.mobs.interpreter;

import com.joojet.plugins.agcraft.interfaces.AbstractInterpreter;
import com.joojet.plugins.mobs.enums.ThemedServerEvent;

public class ThemedServerEventInterpreter extends AbstractInterpreter<ThemedServerEvent>
{
	public ThemedServerEventInterpreter ()
	{
		super (ThemedServerEvent.values());
	}
}
