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
	/** A reference to the Server Mode Interpreter defined in main */
	protected ServerModeInterpreter serverModeInterpreter;
	
	public ChangeServerMode (ServerModeInterpreter serverModeInterpreter)
	{
		super (CommandType.CHANGE_SERVER_MODE);
		this.serverModeInterpreter = serverModeInterpreter;
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
			
			ServerMode mode = this.serverModeInterpreter.searchTrie(arg3[0]);
			if (mode == null)
			{
				return false;
			}
			
			AGCraftPlugin.plugin.switchServerMode(mode);
			sender.sendMessage ("Changed server mode to " + mode.toString());
			return true;
		}
		return false;
	}

}
