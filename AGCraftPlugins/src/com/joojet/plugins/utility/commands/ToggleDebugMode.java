package com.joojet.plugins.utility.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

import com.joojet.plugins.agcraft.enums.CommandType;
import com.joojet.plugins.agcraft.interfaces.AGCommandExecutor;
import com.joojet.plugins.mobs.AmplifiedMobSpawner;

import net.md_5.bungee.api.ChatColor;

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
		if (arg0 instanceof Player)
		{
			Player p = (Player) arg0;
			p.sendMessage(ChatColor.RED + "I am sorry, but this command can only be executed by the server administrator.");
		}
		else if (arg0 instanceof ConsoleCommandSender)
		{
			AmplifiedMobSpawner.toggleDebug();
			return true;
		}
		return false;
	}
}
