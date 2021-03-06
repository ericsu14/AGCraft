package com.joojet.plugins.agcraft.interfaces;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.bukkit.command.TabCompleter;

import com.joojet.plugins.agcraft.enums.CommandType;

public abstract class AGTabCompleter implements TabCompleter
{
	/** Stores the type of command this tab completer is part of */
	protected CommandType commandType;
	/** Stores a reference to the tab completer's command executor */
	protected AGCommandExecutor commandExecutor;
	
	public AGTabCompleter (CommandType commandType)
	{
		this.commandType = commandType;
		this.commandExecutor = null;
	}
	
	/** Sets the tab completer's parenting command executor to a new instance
	 *  @param - commandExecutor - The new command executor instance */
	public void setCommandExecutor (AGCommandExecutor commandExecutor)
	{
		this.commandExecutor = commandExecutor;
	}
	
	/** Returns the command type associated with this tab completer instance */
	public CommandType getCommandType ()
	{
		return this.commandType;
	}
	
	/** Filters a list of objects based on the passed input and
	 *  returns the transformed list as a List
	 *  	@param values - A list of strings
	 * 		@param input - The input used to filter the list of strings*/
	protected List <String> filterListByInput (List <Object> values, String input)
	{
		return values.stream().
		map (entry -> entry.toString().toLowerCase()).
		filter (entry -> entry.contains(input)).
		sorted().collect(Collectors.toList());
	}
	
	/** Filters a primitive array of objects based on the passed input and returns
	 *  the transformed list as a List.
	 * 		@param values - An array of object
	 * 		@param input - The input used to filter the list of strings */
	protected List <String> filterArrayByInput (Object values[], String input)
	{
		return Arrays.asList(values).stream().
		map (entry -> entry.toString().toLowerCase()).
		filter (entry -> entry.contains(input)).
		sorted().collect(Collectors.toList());
	}
	
	/** Returns an array of integers between a certain range
	 * 	@param min - Min value
	 *  @param max - Max value */
	protected List <String> generateBetweenRange (int min, int max)
	{
		List <String> result = new ArrayList <String> ();
		for (int i = min; i <= max; ++i)
		{
			result.add(i + "");
		}
		return result;
	}
	
}
