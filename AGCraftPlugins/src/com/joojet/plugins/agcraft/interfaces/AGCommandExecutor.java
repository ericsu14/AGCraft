package com.joojet.plugins.agcraft.interfaces;

import org.bukkit.command.CommandExecutor;

import com.joojet.plugins.agcraft.enums.CommandType;
import com.joojet.plugins.agcraft.main.AGCraftPlugin;

public abstract class AGCommandExecutor implements CommandExecutor
{
	/** Command's command type */
	protected CommandType commandType;
	
	public AGCommandExecutor (CommandType commandType)
	{
		this.commandType = commandType;
		// Inserts itself into the plugin's command list
		AGCraftPlugin.addPlayerCommand(this.commandType, this);
	}
}
