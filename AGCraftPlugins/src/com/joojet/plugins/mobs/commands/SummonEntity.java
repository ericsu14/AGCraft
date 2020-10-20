package com.joojet.plugins.mobs.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.joojet.plugins.agcraft.config.ServerConfigFile;
import com.joojet.plugins.agcraft.enums.CommandType;
import com.joojet.plugins.agcraft.interfaces.AGCommandExecutor;
import com.joojet.plugins.mobs.SummoningScrollListener;
import com.joojet.plugins.mobs.bossbar.BossBarController;
import com.joojet.plugins.mobs.interpreter.SummoningScrollInterpreter;
import com.joojet.plugins.mobs.scrolls.SummoningScroll;

import net.md_5.bungee.api.ChatColor;

public class SummonEntity extends AGCommandExecutor {
	
	/** Stores interpreter used to reference all custom entity summons */
	protected SummoningScrollInterpreter summonTypes;
	
	/** Stores a reference to the plugin's boss bar controller */
	protected BossBarController bossBarController;
	
	public SummonEntity(SummoningScrollInterpreter summonTypes, BossBarController bossBarController) {
		super(CommandType.SUMMON_ENTITY);
		this.summonTypes = summonTypes;
		this.bossBarController = bossBarController;
	}
	
	
	/** Usage: /summonEntity <name of custom entity> */
	@Override
	public boolean onCommand(CommandSender sender, Command arg1, String arg2, String[] args) {
		int n = args.length;
		if (sender instanceof Player && n >= 1)
		{
			Player player = (Player) sender;
			String summonID = args[0];
			SummoningScroll summon = this.summonTypes.searchTrie(summonID);
			
			if (summon != null)
			{
				SummoningScrollListener.summonMonster(player, summon.getMob(), summon.getMobType(),this.bossBarController);
				return true;
			}
			else
			{
				player.sendMessage(ChatColor.RED + "Error: Summon not recognized.");
			}
		}
		return false;
	}

	@Override
	public void loadConfigVariables(ServerConfigFile config) 
	{
		// TODO Auto-generated method stub

	}

}
