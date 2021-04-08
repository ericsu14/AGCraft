package com.joojet.plugins.agcraft.commands;

import com.joojet.plugins.agcraft.enums.CommandType;
import com.joojet.plugins.agcraft.interfaces.AbstractInterpreter;

public class CommandTypeInterpreter extends AbstractInterpreter<CommandType> 
{
	public CommandTypeInterpreter ()
	{
		super (CommandType.values());
	}
}
