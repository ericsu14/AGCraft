package com.joojet.plugins.mobs.interpreter;

import com.joojet.plugins.agcraft.interfaces.AbstractInterpreter;
import com.joojet.plugins.mobs.enums.ThemedServerEvent;

public class ServerEventInterpreter extends AbstractInterpreter<ThemedServerEvent>
{
	public ServerEventInterpreter ()
	{
		super (ThemedServerEvent.values());
	}
}
