package com.joojet.plugins.warp.commands.tabcompleter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import com.joojet.plugins.warp.constants.WarpAccessLevel;

public class SetLocationTabCompleter implements TabCompleter
{
	@Override
	public List <String> onTabComplete (CommandSender sender, Command command, String alias, String[] args)
	{
		int n = args.length;
		
		if (sender instanceof Player && n >= 1)
		{
			String input = args[n-1].toLowerCase();
			Object[] values = null;
			
			switch (n)
			{
				case 2:
					values = Arrays.asList(WarpAccessLevel.values()).stream().
						map (entry -> entry.toString().toLowerCase()).
						filter (entry -> entry.contains(input))
						.toArray();
					break;
				default:
					return new ArrayList <String> ();
			}
			
			return (List<String>) Arrays.asList(Arrays.copyOf(values, values.length, String[].class));  
		}
		return null;
	}
}
