package com.joojet.plugins.utility.interpreter;

import com.joojet.plugins.agcraft.enums.ServerMode;
import com.joojet.plugins.agcraft.interfaces.AbstractInterpreter;

public class ServerModeInterpreter extends AbstractInterpreter <ServerMode>
{
	public ServerModeInterpreter ()
	{
		super (ServerMode.values());
	}
}
