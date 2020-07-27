package com.joojet.plugins.agcraft.interfaces;

import org.bukkit.command.CommandExecutor;
import org.bukkit.command.TabCompleter;

import com.joojet.plugins.agcraft.enums.CommandType;
import com.joojet.plugins.agcraft.enums.PermissionType;

/** An object that stores a player command */
public class PlayerCommand 
{
	protected CommandType commandType;
	protected CommandExecutor command;
	protected TabCompleter commandTabCompleter;
	protected PermissionType permissionType;
	
	/** Represents a player command without a tab completer
	 * 		@param commandName - Name of the command
	 * 		@param command - A reference to the command's executor instance
	 * 		@param commandType - Permissions for the command  */
	public PlayerCommand (CommandType commandType, CommandExecutor command)
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
	public PlayerCommand (CommandType commandType, CommandExecutor command, TabCompleter tabCompleter)
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
	public CommandExecutor getExecutor ()
	{
		return this.command;
	}
	
	/** Returns the command's tab completer instance */
	public TabCompleter getTabCompleter ()
	{
		return this.commandTabCompleter;
	}
	
	/** Returns the command's permission type */
	public PermissionType getPermissionType ()
	{
		return this.permissionType;
	}
	
	/** Sets this command's tabcompleter to a new instance */
	public void setTabCompleter (TabCompleter tabCompleter)
	{
		this.commandTabCompleter = tabCompleter;
	}
}
