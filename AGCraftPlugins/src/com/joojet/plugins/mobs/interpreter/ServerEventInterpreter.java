package com.joojet.plugins.mobs.interpreter;

import com.joojet.plugins.agcraft.interfaces.AbstractInterpreter;
import com.joojet.plugins.mobs.enums.ServerEvent;

public class ServerEventInterpreter extends AbstractInterpreter<ServerEvent>
{
	public ServerEventInterpreter ()
	{
		super (ServerEvent.values());
	}
}
