package com.joojet.plugins.agcraft.main;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import com.destroystokyo.paper.event.server.AsyncTabCompleteEvent;
import com.joojet.plugins.agcraft.commands.PlayerCommand;
import com.joojet.plugins.agcraft.enums.CommandType;
import com.joojet.plugins.agcraft.interfaces.AGTabCompleter;
import com.joojet.plugins.agcraft.interfaces.AsyncTabCompleteOperation;

public class TabCompleteListener implements Listener 
{
	protected ConcurrentHashMap <CommandType, PlayerCommand> playerCommands;
	
	public TabCompleteListener (ConcurrentHashMap <CommandType, PlayerCommand> playerCommands)
	{
		this.playerCommands = playerCommands;
	}
	
	/** Listens to PaperMC's AsyncTabCompleteEvents and run the AGCommand's async command handler
	 *  if one exists for that command */
	@EventHandler
	public void onAsyncTabComplete (AsyncTabCompleteEvent event)
	{
		CommandType command = this.getCommandTypeFromBuffer(event.getBuffer());
		
		if (command != null && this.playerCommands.containsKey(command))
		{
			AGTabCompleter tabCompleter = this.playerCommands.get(command).getTabCompleter();
			if (tabCompleter != null && 
					tabCompleter instanceof AsyncTabCompleteOperation)
			{
				try 
				{
					List <String> completions = ((AsyncTabCompleteOperation) tabCompleter).
							getDataFromDatabase(event.getSender(), command.toString(), this.getParameters(event.getBuffer()));
					
					if (completions != null)
					{
						event.setCompletions(completions);
					}
					event.setHandled(true);
				} 
				catch (SQLException e) 
				{
					e.printStackTrace();
				}
			}
		}
	}
	
	/** Retrieves a command type from a TabCompleteEvent's buffer */
	public CommandType getCommandTypeFromBuffer (String buffer)
	{
		if (buffer != null && !buffer.isEmpty())
		{
			// Parse out the slash character from the buffer and retrieve the first tokenized string, which is the command itself
			String command = buffer.substring(1).split(" ")[0];
			for (CommandType commandType : CommandType.values())
			{
				if (command.equalsIgnoreCase(commandType.toString()))
				{
					return commandType;
				}
			}
		}
		return null;
	}
	
	/** Retrieves the parameters of a TabCompleteEvent's buffer */
	public List <String> getParameters (String buffer)
	{
		if (buffer != null && !buffer.isEmpty())
		{
			String [] tokens = buffer.split ("\\s+");
			List <String> result = new ArrayList <String> ();
			
			boolean seenFirst = false;
			for (String token : tokens)
			{
				// Do not add the first input into the parameters list
				if (!seenFirst)
				{
					seenFirst = true;
				}
				else
				{
					result.add(token);
				}
			}
			
			// If the result is empty, add an empty string so tabs can show the entire list of options
			if (result.isEmpty())
			{
				result.add("");
			}
			
			return result;
		}
		return new ArrayList <String> ();
	}
	
}
