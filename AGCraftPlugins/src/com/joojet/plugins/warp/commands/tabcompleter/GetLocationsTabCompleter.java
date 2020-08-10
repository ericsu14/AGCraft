package com.joojet.plugins.warp.commands.tabcompleter;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.joojet.plugins.agcraft.enums.CommandType;
import com.joojet.plugins.agcraft.interfaces.AGTabCompleter;
import com.joojet.plugins.warp.constants.WarpAccessLevel;

public class GetLocationsTabCompleter extends AGTabCompleter 
{
	public GetLocationsTabCompleter ()
	{
		super (CommandType.GET_LOCATIONS);
	}
	
	@Override
	public List <String> onTabComplete (CommandSender sender, Command command, String alias, String[] args)
	{
		int n = args.length;
		if (sender instanceof Player && n >= 1)
		{
			String input = args[n-1].toLowerCase();
			
			switch (n)
			{
				case 1:
					return this.filterArrayByInput(WarpAccessLevel.values(), input);
				default:
					return new ArrayList <String> ();
			}
		}
		
		return null;
	}
}
