package com.joojet.plugins.utility.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

import com.joojet.plugins.agcraft.enums.CommandType;
import com.joojet.plugins.agcraft.interfaces.AGCommandExecutor;
import com.joojet.plugins.agcraft.main.AGCraftPlugin;

public class ReloadConfigFile extends AGCommandExecutor {
	public ReloadConfigFile ()
	{
		super (CommandType.RELOAD_CONFIG_FILE);
	}
	
	/** Dynamically reloads the server's config file */
	@Override
	public boolean onCommand(CommandSender sender, Command arg1, String arg2, String[] args) 
	{
		if (sender instanceof ConsoleCommandSender || sender instanceof Player)
		{
			AGCraftPlugin.plugin.loadServerConfigFile();
			sender.sendMessage("Reload complete!");
			return true;
		}
		return false;
	}

}
