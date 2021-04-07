package com.joojet.plugins.warp.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.ChatColor;

import com.joojet.plugins.agcraft.config.ServerConfigFile;
import com.joojet.plugins.agcraft.enums.CommandType;
import com.joojet.plugins.agcraft.interfaces.AGCommandExecutor;
import com.joojet.plugins.mobs.interpreter.MonsterTypeInterpreter;
import com.joojet.plugins.warp.commands.tasks.AsyncEWarpCheckerTask;
import com.joojet.plugins.warp.commands.tasks.AsyncWarpPlayerTask;
import com.joojet.plugins.warp.scantools.ScanEntities;

public class Warp extends AGCommandExecutor
{
	/** A hardcoded warp location used to define a warp back to the player's bed spawn */
	public final static String home = "home";
	/** A tag used to identify the scanning radius variable in the config file */
	public final static String WARP_RADIUS_TAG = "warp-scan-radius";
	/** A tag used to identify the health threshold variable in the config file */
	public final static String WARP_HEALTH_THRESHOLD_TAG = "warp-min-health-threshold";
	/** Max. search radius of nearby enemies check */
	private int maxMobRadius;
	/** Min. player health needs to exceed before warping */
	private double healthThreshold;
	/** Search trie used to lookup custom monsters by name */
	protected MonsterTypeInterpreter monsterTypeInterpreter;
	
	public Warp (MonsterTypeInterpreter monsterTypeInterpreter)
	{
		super (CommandType.WARP);
		this.monsterTypeInterpreter = monsterTypeInterpreter;
		this.healthThreshold = 6.0;
		this.maxMobRadius = 6;
	}
	
	/** Warps a player to either a designated location or their bed spawn.
	 * 	Usage:
	 * 		/warp <location name> */
	@Override
	public boolean onCommand (CommandSender sender, Command command, String label, String [] args)
	{
		if (sender instanceof Player)
		{
			Player p = (Player) sender;
			
			int n = args.length;
			
			if (n < 1)
			{
				p.sendMessage(ChatColor.RED + "Insufficient parameters.");
				return false;
			}
			
			// Check for player conditions
			if (ScanEntities.ScanNearbyEnemies(p, maxMobRadius, this.monsterTypeInterpreter))
			{
				p.sendMessage(ChatColor.RED + "You cannot warp now! There are enemies nearby.");
				return false;
			}
			
			String locName = args[0].toLowerCase();
			
			// Also deny warp if the player is either on fire (without fire resistance) or health drops below threshold
			if ((p.getFireTicks() > 0 && !p.hasPotionEffect(PotionEffectType.FIRE_RESISTANCE))
					|| p.getHealth() < healthThreshold)
			{
				new AsyncEWarpCheckerTask (p, locName).runDatabaseTask();;
			}
			else
			{
				new AsyncWarpPlayerTask (p, locName).runDatabaseTask();;
			}
			return true;
		}
		return false;
	}

	@Override
	public void loadConfigVariables(ServerConfigFile config) 
	{
		this.maxMobRadius = config.getValueAsInteger(WARP_RADIUS_TAG);
		this.healthThreshold = config.getValueAsDouble(WARP_HEALTH_THRESHOLD_TAG);
	}
}
