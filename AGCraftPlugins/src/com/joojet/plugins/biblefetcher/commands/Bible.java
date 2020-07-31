package com.joojet.plugins.biblefetcher.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.joojet.plugins.agcraft.enums.CommandType;
import com.joojet.plugins.agcraft.interfaces.AGCommandExecutor;
import com.joojet.plugins.biblefetcher.task.FetchBibleTask;

public class Bible extends AGCommandExecutor
{
	public Bible ()
	{
		super (CommandType.BIBLE);
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
			FetchBibleTask bibleTask = new FetchBibleTask (player, args);
			bibleTask.start();
			return true;
		}
		return false;
	}

}
