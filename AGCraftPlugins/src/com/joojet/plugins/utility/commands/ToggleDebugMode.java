package com.joojet.plugins.utility.commands;

import org.bukkit.ChatColor;
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
	public boolean onCommand(CommandSender sender, Command arg1, String arg2, String[] arg3) 
	{
		if (sender instanceof ConsoleCommandSender || sender instanceof Player)
		{
			AGCraftPlugin.plugin.toggleDebugMode();
			sender.sendMessage("Debug mode toggled to " + ChatColor.GOLD + AGCraftPlugin.plugin.enableDebugMode);
			return true;
		}
		return false;
	}

}
