package com.joojet.plugins.utility.commands.tabcompleter;

import java.util.List;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.joojet.plugins.agcraft.enums.CommandType;
import com.joojet.plugins.agcraft.enums.ServerMode;
import com.joojet.plugins.agcraft.interfaces.AGTabCompleter;

public class ChangeServerModeTabCompleter extends AGTabCompleter 
{
	public ChangeServerModeTabCompleter ()
	{
		super (CommandType.CHANGE_SERVER_MODE);
	}

	@Override
	public List<String> onTabComplete(CommandSender sender, Command arg1, String arg2, String[] args) {
		int n = args.length;
		if (sender instanceof Player && n == 1)
		{
			String input = args[n-1].toLowerCase();
			return this.filterArrayByInput(ServerMode.values(), input);
		}
		return null;
	}
	
	

}
