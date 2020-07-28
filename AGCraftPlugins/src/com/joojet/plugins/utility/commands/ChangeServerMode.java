package com.joojet.plugins.utility.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

import com.joojet.plugins.agcraft.enums.CommandType;
import com.joojet.plugins.agcraft.enums.ServerMode;
import com.joojet.plugins.agcraft.interfaces.AGCommandExecutor;
import com.joojet.plugins.agcraft.main.AGCraftPlugin;
import com.joojet.plugins.utility.interpreter.ServerModeInterpreter;

public class ChangeServerMode extends AGCommandExecutor 
{
	
	protected ServerModeInterpreter interpreter;
	public ChangeServerMode ()
	{
		super (CommandType.CHANGE_SERVER_MODE);
		this.interpreter = new ServerModeInterpreter ();
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command arg1, String arg2, String[] arg3) 
	{
		int n = arg3.length;
		
		if (sender instanceof Player || sender instanceof ConsoleCommandSender)
		{
			if (n < 1)
			{
				return false;
			}
			
			ServerMode mode = this.interpreter.searchTrie(arg3[0]);
			if (mode == null)
			{
				return false;
			}
			
			AGCraftPlugin.plugin.switchServerMode(mode);
			System.out.println ("Changed server mode to " + mode.toString());
			sender.sendMessage ("Changed server mode to " + mode.toString());
			return true;
		}
		return false;
	}

}
