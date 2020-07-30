package com.joojet.plugins.utility.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

import com.joojet.plugins.agcraft.enums.CommandType;
import com.joojet.plugins.agcraft.interfaces.AGCommandExecutor;
import com.joojet.plugins.agcraft.main.AGCraftPlugin;

public class ToggleDebugMode extends AGCommandExecutor
{
	public ToggleDebugMode ()
	{
		super (CommandType.TOGGLE_DEBUG_MODE);
	}
	
	/** A command that toggles debug mode on or off.
	 *  Can only be used by the command sender in the terminal */
	@Override
	public boolean onCommand(CommandSender arg0, Command arg1, String arg2, String[] arg3) 
	{
		if (arg0 instanceof ConsoleCommandSender || arg0 instanceof Player)
		{
			AGCraftPlugin.plugin.toggleDebugMode();
			return true;
		}
		return false;
	}
}
