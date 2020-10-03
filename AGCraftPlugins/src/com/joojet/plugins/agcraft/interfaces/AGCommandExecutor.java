package com.joojet.plugins.agcraft.interfaces;

import org.bukkit.command.CommandExecutor;

import com.joojet.plugins.agcraft.config.ServerConfigFile;
import com.joojet.plugins.agcraft.enums.CommandType;

public abstract class AGCommandExecutor implements CommandExecutor
{
	/** Command's command type */
	protected CommandType commandType;
	
	public AGCommandExecutor (CommandType commandType)
	{
		this.commandType = commandType;
	}
	
	public CommandType getCommandType ()
	{
		return this.commandType;
	}
	
	/** Allows the command to load in its configurable variables specified in the main
	 *  server's configuration file.
	 *  @param config - A reference to the main server configuration file */
	public abstract void loadConfigVariables (ServerConfigFile config);
}
