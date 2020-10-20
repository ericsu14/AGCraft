package com.joojet.plugins.mobs.commands.tabcompleter;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

import com.joojet.plugins.agcraft.enums.CommandType;
import com.joojet.plugins.agcraft.interfaces.AGTabCompleter;
import com.joojet.plugins.mobs.enums.MonsterType;

public class SummonEntityTabCompleter extends AGTabCompleter {
	
	public SummonEntityTabCompleter ()
	{
		super (CommandType.SUMMON_ENTITY);
	}
	
	@Override
	public List<String> onTabComplete(CommandSender sender, Command arg1, String arg2, String[] args) {
		int n = args.length;
		
		if (n == 1)
		{
			return this.filterArrayByInput(MonsterType.values(), args[0]);
		}
		return new ArrayList <String> ();
	}

}
