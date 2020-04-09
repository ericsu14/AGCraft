package com.joojet.plugins.biblefetcher.commands;

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
	public List <String> onTabComplete (CommandSender sender, Command command, String alias, String[] args)
	{
		int n = args.length;
		if (command.getName().equalsIgnoreCase("bible"))
		{
			String values[] = null;
			switch (n)
			{
				// Get bible translations
				case 1:
					values = (String[]) Arrays.asList((BibleID[])BibleID.values())
						.stream().map(entry -> entry.getBibleID()).toArray();
					break;
				// Get bible book names
				case 2:
					values = (String[]) Arrays.asList((BookID[])BookID.values())
						.stream().map(entry -> entry.getFormattedTitleWithoutSpace()).toArray();
					break;
				// Otherwise, list all the chapters in the book.
				default:
					BookID book = AGCraftPlugin.interpreter.searchBookTrie(args[1]);
					
					ArrayList <String> chapters = new ArrayList <String> ();
					for (int i = 1; i <= book.getNumChapters(); ++i)
					{
						chapters.add(i + "");
					}
					return chapters;
			}
			
			return (List<String>) Arrays.asList(values);
		}
		
		return null;
	}
}
