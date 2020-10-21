package com.joojet.plugins.mobs.commands.tabcompleter;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

import com.joojet.plugins.agcraft.enums.CommandType;
import com.joojet.plugins.agcraft.interfaces.AGTabCompleter;
import com.joojet.plugins.mobs.commands.SummonEntity;
import com.joojet.plugins.mobs.enums.MonsterType;

public class SummonEntityTabCompleter extends AGTabCompleter 
{	
	public SummonEntityTabCompleter ()
	{
		super (CommandType.SUMMON_ENTITY);
	}
	
	@Override
	public List<String> onTabComplete(CommandSender sender, Command arg1, String arg2, String[] args) {
		int n = args.length;
		
		SummonEntity summonEntityCommand = (SummonEntity) this.commandExecutor;
		switch (n)
		{
			case 1:
				return this.filterArrayByInput(MonsterType.values(), args[0]);
			case 2:
				return this.filterArrayByInput(this.generateBetweenRange(1, summonEntityCommand.getMaxSummonedEntities()).toArray(), args[1]);
			default:
				break;
		}
		return new ArrayList <String> ();
	}

}
