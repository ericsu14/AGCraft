package com.joojet.plugins.rewards.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.joojet.plugins.agcraft.config.ServerConfigFile;
import com.joojet.plugins.agcraft.enums.CommandType;
import com.joojet.plugins.agcraft.interfaces.AGCommandExecutor;
import com.joojet.plugins.rewards.gui.RewardGUI;

public class OpenRewards extends AGCommandExecutor
{

	public OpenRewards() 
	{
		super(CommandType.REWARDS);
	}

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String [] args) 
	{
		if (sender instanceof Player)
		{
			Player player = (Player) sender;
			RewardGUI gui = new RewardGUI (player);
			gui.onEnable();
			gui.openInventory();
			return true;
		}
		return false;
	}

	@Override
	public void loadConfigVariables(ServerConfigFile config) 
	{
		
	}

}
