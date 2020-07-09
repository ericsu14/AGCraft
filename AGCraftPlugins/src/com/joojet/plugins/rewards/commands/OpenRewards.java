package com.joojet.plugins.rewards.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.joojet.plugins.rewards.gui.RewardGUI;

public class OpenRewards implements CommandExecutor  
{

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

}
