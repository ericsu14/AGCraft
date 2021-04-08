package com.joojet.plugins.agcraft.commands;

import com.joojet.plugins.agcraft.enums.CommandType;
import com.joojet.plugins.agcraft.enums.PermissionType;
import com.joojet.plugins.agcraft.interfaces.AGCommandExecutor;
import com.joojet.plugins.agcraft.interfaces.AGTabCompleter;

/** An object that stores a player command */
public class PlayerCommand 
{
	protected CommandType commandType;
	protected AGCommandExecutor command;
	protected AGTabCompleter commandTabCompleter;
	protected PermissionType permissionType;
	
	/** Represents a player command without a tab completer
	 * 		@param commandName - Name of the command
	 * 		@param command - A reference to the command's executor instance
	 * 		@param commandType - Permissions for the command  */
	public PlayerCommand (CommandType commandType, AGCommandExecutor command)
	{
		this.commandTabCompleter = null;
		this.commandType = commandType;
		this.command = command;
		this.permissionType = commandType.getPermissionType();
	}
	
	/** Represents a player command with a tab completer
	 * 		@param commandName - Name of the command
	 * 		@param command - A reference to the command's executor instance
	 * 		@param tabCompleter - A reference to the command's tab completer instance
	 * 		@param commandType - Permissions for the command  */
	public PlayerCommand (CommandType commandType, AGCommandExecutor command, AGTabCompleter tabCompleter)
	{
		this.commandTabCompleter = tabCompleter;
		this.commandType = commandType;
		this.command = command;
		this.permissionType = commandType.getPermissionType();
	}
	
	/** Returns the command's name */
	public CommandType getCommandType ()
	{
		return this.commandType;
	}
	
	/** Returns the command's executor instance */
	public AGCommandExecutor getExecutor ()
	{
		return this.command;
	}
	
	/** Returns the command's tab completer instance */
	public AGTabCompleter getTabCompleter ()
	{
		return this.commandTabCompleter;
	}
	
	/** Returns the command's permission type */
	public PermissionType getPermissionType ()
	{
		return this.permissionType;
	}
	
	/** Sets this command's tabcompleter to a new instance */
	public void setTabCompleter (AGTabCompleter tabCompleter)
	{
		this.commandTabCompleter = tabCompleter;
		tabCompleter.setCommandExecutor(this.command);
	}
}
