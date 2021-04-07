package com.joojet.plugins.biblefetcher.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.joojet.biblefetcher.interpreter.BibleCommandInterpreter;
import com.joojet.plugins.agcraft.config.ServerConfigFile;
import com.joojet.plugins.agcraft.enums.CommandType;
import com.joojet.plugins.agcraft.interfaces.AGCommandExecutor;
import com.joojet.plugins.agcraft.main.AGCraftPlugin;
import com.joojet.plugins.biblefetcher.task.FetchBibleTask;

public class Bible extends AGCommandExecutor
{
	/** A reference to the bible command interpreter defined in the main driver */
	protected BibleCommandInterpreter bibleCommandInterpreter;
	
	public Bible (BibleCommandInterpreter bibleCommandInterpreter)
	{
		super (CommandType.BIBLE);
		this.bibleCommandInterpreter = bibleCommandInterpreter;
	}
	
	/** Represents the /bible command.
	 * 	 Usage: /bible <translation> <book> <chapter> <start> <end>*/
	@Override
	public boolean onCommand (CommandSender sender, Command command, String label, String [] args)
	{
		/* Parses the command */
		if (sender instanceof Player)
		{
			Player player = (Player) sender;
			new FetchBibleTask (player, args, this.bibleCommandInterpreter).runTask(AGCraftPlugin.plugin);
			return true;
		}
		return false;
	}

	@Override
	public void loadConfigVariables(ServerConfigFile config) 
	{
		// TODO Auto-generated method stub
		
	}

}
