package com.joojet.plugins.agcraft.interfaces;

import org.bukkit.command.TabCompleter;

import com.joojet.plugins.agcraft.enums.CommandType;

public abstract class AGTabCompleter implements TabCompleter
{
	protected CommandType commandType;
	
	public AGTabCompleter (CommandType commandType)
	{
		this.commandType = commandType;
	}
	
	public CommandType getCommandType ()
	{
		return this.commandType;
	}
	
}
