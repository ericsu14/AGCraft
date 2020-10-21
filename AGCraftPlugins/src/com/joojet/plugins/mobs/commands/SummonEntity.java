package com.joojet.plugins.mobs.commands;

import org.bukkit.ChatColor;
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

public class SummonEntity extends AGCommandExecutor 
{
	/** Tag used to identify the max entites that could be summoned in bulk using this summon command */
	public static final String MAX_SUMMONED_ENTITIES_TAG = "max-summoned-entities";
	/** Stores interpreter used to reference all custom entity summons */
	protected SummoningScrollInterpreter summonTypes;
	
	/** Stores a reference to the plugin's boss bar controller */
	protected BossBarController bossBarController;
	
	/** Max entities that can be summoned at a time */
	protected int maxSummonedEntities;
	
	public SummonEntity(SummoningScrollInterpreter summonTypes, BossBarController bossBarController) 
	{
		super(CommandType.SUMMON_ENTITY);
		this.summonTypes = summonTypes;
		this.bossBarController = bossBarController;
		this.maxSummonedEntities = 100;
	}
	
	
	/** Usage: /summonEntity <name of custom entity> */
	@Override
	public boolean onCommand(CommandSender sender, Command arg1, String arg2, String[] args) {
		int n = args.length;
		if (sender instanceof Player && n >= 1)
		{
			Player player = (Player) sender;
			try
			{
				String summonID = args[0];
				int amount = 1;
				if (n >= 2)
				{
					amount = Integer.parseInt(args[1]);
				}
				
				SummoningScroll summon = this.summonTypes.searchTrie(summonID);
				
				if (summon != null)
				{
					for (int i = 0; i < amount; ++i)
					{
						SummoningScrollListener.summonMonster(player, summon.getMob(), summon.getMobType(),this.bossBarController);
					}
					return true;
				}
				else
				{
					player.sendMessage(ChatColor.RED + "Error: Summon not recognized.");
				}
			}
			
			catch (NumberFormatException ne)
			{
				player.sendMessage(ChatColor.RED + "Error: Could not parse input.");
			}
		}
		return false;
	}
	
	/** Returns the max amount of entities that could be summoned in bulk with this command */
	public int getMaxSummonedEntities ()
	{
		return this.maxSummonedEntities;
	}

	@Override
	public void loadConfigVariables(ServerConfigFile config) 
	{
		this.maxSummonedEntities = config.getValueAsInteger(MAX_SUMMONED_ENTITIES_TAG);
	}

}
