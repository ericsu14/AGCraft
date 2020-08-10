package com.joojet.plugins.utility.commands.tabcompleter;

import java.util.List;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.joojet.plugins.agcraft.enums.CommandType;
import com.joojet.plugins.agcraft.interfaces.AGTabCompleter;
import com.joojet.plugins.utility.enums.JunkClassifier;

public class ClearJunkTabCompleter extends AGTabCompleter 
{
	public ClearJunkTabCompleter ()
	{
		super (CommandType.CLEAR_JUNK);
	}
	
	@Override
	public List <String> onTabComplete (CommandSender sender, Command command, String alias, String[] args)
	{
		int n = args.length;
		if (sender instanceof Player && n >= 1)
		{
			String input = args[n-1].toLowerCase();
			return this.filterArrayByInput(JunkClassifier.values(), input);
		}
		return null;
	}
}
