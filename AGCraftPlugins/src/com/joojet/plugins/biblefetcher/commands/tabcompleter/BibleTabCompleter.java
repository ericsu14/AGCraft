package com.joojet.plugins.biblefetcher.commands.tabcompleter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

import com.joojet.biblefetcher.constants.BibleID;
import com.joojet.biblefetcher.constants.BookID;
import com.joojet.plugins.agcraft.main.AGCraftPlugin;

public class BibleTabCompleter implements TabCompleter 
{
	@Override
	public List <String> onTabComplete (CommandSender sender, Command command, String alias, String[] args)
	{
		int n = args.length;
		if (command.getName().equalsIgnoreCase("bible") && n >= 1)
		{
			// Captures the current input
			String input = args [n - 1].toLowerCase();
			Object values[] = null;
			switch (n)
			{
				// Get bible translations
				case 1:
					values = Arrays.asList((BibleID[])BibleID.values()).
						stream().map(entry -> (String) entry.getBibleID()).
						filter(param -> param.toLowerCase().contains(input)).
						toArray();
					break;
					
				// Get bible book names
				case 2:
					values = Arrays.asList((BookID[])BookID.values()).
						stream().map(entry -> (String) entry.getFormattedTitleWithoutSpace()).
						filter(param -> param.toLowerCase().contains (input)).
						toArray();
					break;
					
				// List all the chapters in the book.
				case 3:
					BookID book = AGCraftPlugin.interpreter.searchBookTrie(args[1]);
					
					ArrayList <String> chapters = new ArrayList <String> ();
					for (int i = 1; i <= book.getNumChapters(); ++i)
					{
						chapters.add(i + "");
					}
					values = chapters.stream().
							filter(param -> param.contains(input)).
							toArray();
					
				// Otherwise, return an empty array
				default:
					return new ArrayList <String> ();
			}
			
			return (List<String>) Arrays.asList(Arrays.copyOf(values, values.length, String[].class));
		}
		
		return null;
	}
}
