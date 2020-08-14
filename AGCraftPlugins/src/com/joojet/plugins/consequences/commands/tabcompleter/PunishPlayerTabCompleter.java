package com.joojet.plugins.consequences.commands.tabcompleter;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

import com.joojet.plugins.agcraft.enums.CommandType;
import com.joojet.plugins.agcraft.interfaces.AGTabCompleter;
import com.joojet.plugins.consequences.enums.CalendarField;

public class PunishPlayerTabCompleter extends AGTabCompleter
{
	public PunishPlayerTabCompleter ()
	{
		super (CommandType.PUNISH_PLAYER);
	}

	@Override
	public List<String> onTabComplete(CommandSender sender, Command arg1, String arg2, String[] args) 
	{
		int n = args.length;
		
		if (n > 0)
		{
			// Return an empty list on odd values of n, since that parameter accepts only integers
			if (n % 2 != 0)
			{
				return new ArrayList <String> ();
			}
			// Otherwise, return all CalendarField values as a list
			else
			{
				return this.filterArrayByInput(CalendarField.values(), args[n]);
			}
		}
		return null;
	}
	
	
}
