package com.joojet.plugins.deathcounter;

import org.bukkit.OfflinePlayer;
import org.bukkit.Statistic;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;

import com.joojet.plugins.agcraft.enums.ServerMode;
import com.joojet.plugins.agcraft.main.AGCraftPlugin;

public class DeathCounter 
{
	private String scoreboardName = "deathcounter";
	private String deathCriteriaName = "deathCount";
	private String scoreboardDisplayName = "Deaths";
	private Scoreboard scoreboard;
	private Objective deathCounterObj;
	
	public DeathCounter ()
	{		
		this.scoreboard = AGCraftPlugin.plugin.getServer().getScoreboardManager().getMainScoreboard();
		if (AGCraftPlugin.plugin.serverMode == ServerMode.NORMAL)
		{
			// Create a new deathcounter scoreboard if one doesn't already exist
			deathCounterObj = this.scoreboard.getObjective(scoreboardName);
			if (deathCounterObj == null)
			{
				deathCounterObj = this.scoreboard.registerNewObjective(scoreboardName, deathCriteriaName, scoreboardDisplayName);
				deathCounterObj.setDisplaySlot(DisplaySlot.BELOW_NAME);
			}
			this.syncDeathCounts();
		}
	}
	
	/** Synchronizes the death counter scoreboard to reflect values found in each player's statistics */
	@SuppressWarnings("deprecation")
	public void syncDeathCounts ()
	{
		OfflinePlayer players [] = AGCraftPlugin.plugin.getServer().getOfflinePlayers();
		
		for (OfflinePlayer p : players)
		{
			if (p != null)
			{
				System.out.println ("Found offline player with UUID " + p.getUniqueId());
				// Gets the player's current deaths
				int deaths = p.getStatistic(Statistic.DEATHS);
				System.out.println ("Deaths: " + deaths);
				deathCounterObj.getScore(p).setScore(deaths);
			}
		}
	}
	
}
