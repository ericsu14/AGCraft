package com.joojet.plugins.utility.commands.tabcompleter;

import java.util.Arrays;
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
			Object[] values = Arrays.stream(JunkClassifier.values()).
					map(entry -> (String) entry.toString().toLowerCase()).
					filter(val -> val.contains(input)).
					sorted().
					toArray();
			return (List<String>) Arrays.asList(Arrays.copyOf(values, values.length, String[].class));
		}
		return null;
	}
}
