package com.joojet.plugins.agcraft.interfaces;

import java.util.Arrays;
import java.util.List;

import org.bukkit.command.TabCompleter;

import com.joojet.plugins.agcraft.enums.CommandType;

public abstract class AGTabCompleter implements TabCompleter
{
	protected CommandType commandType;
	
	public AGTabCompleter (CommandType commandType)
	{
		this.commandType = commandType;
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
		Object[] result = values.stream().
		map (entry -> entry.toString().toLowerCase()).
		filter (entry -> entry.contains(input)).
		sorted().
		toArray();
		return (List<String>) Arrays.asList(Arrays.copyOf(result, result.length, String[].class)); 
	}
	
	/** Filters a primitive array of objects based on the passed input and returns
	 *  the transformed list as a List.
	 * 		@param values - An array of object
	 * 		@param input - The input used to filter the list of strings */
	protected List <String> filterArrayByInput (Object values[], String input)
	{
		Object[] result = Arrays.asList(values).stream().
		map (entry -> entry.toString().toLowerCase()).
		filter (entry -> entry.contains(input)).
		sorted().
		toArray();
		return (List<String>) Arrays.asList(Arrays.copyOf(result, result.length, String[].class)); 
	}
	
}
