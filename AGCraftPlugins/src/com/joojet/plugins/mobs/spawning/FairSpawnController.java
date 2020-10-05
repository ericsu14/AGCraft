package com.joojet.plugins.mobs.spawning;

import java.util.Arrays;
import java.util.List;

import org.bukkit.Location;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.util.BoundingBox;

import com.joojet.plugins.agcraft.main.AGCraftPlugin;

public class FairSpawnController 
{	
	/** Max scan radius used to calculate a player's threat score when spawning in monsters */
	protected Integer maxScanRadius;
	/** Used to calculate a threat score for the player */
	protected FairSpawnWeightContainer fairSpawnWeightContainer;
	/** Bias used to amplify game difficulity if there are multiple players around nearby enemy spawns */
	protected Double bias;
	
	public FairSpawnController (Integer maxScanRadius)
	{
		this.maxScanRadius = maxScanRadius;
		this.bias = 0.0625;
		this.fairSpawnWeightContainer = new FairSpawnWeightContainer (
			new EPFWeight(16.0, 4),
			new FairSpawnWeight (Attribute.GENERIC_ARMOR_TOUGHNESS, 12.0, 2),
			new FairSpawnWeight (Attribute.GENERIC_ARMOR, 20.0, 1),
			new FairSpawnWeight (Attribute.GENERIC_MAX_HEALTH, 20.0, 1)
		);
	}
	
	public Double getAverageThreatScore (LivingEntity monster)
	{
		Integer numPlayers = 0;
		Double sumOfScores = 0.0;
		
		List<Player> onlinePlayers = Arrays.asList(AGCraftPlugin.plugin.getServer().getOnlinePlayers().toArray(new Player[0]));
		for (Player player : onlinePlayers)
		{
			if (this.checkIfMobIsWithinRangeOfPlayer(player, monster))
			{
				++numPlayers;
				sumOfScores += this.fairSpawnWeightContainer.calculateThreatScore(player);
			}
		}
		return numPlayers > 0 ? (sumOfScores / numPlayers) + (bias * (numPlayers - 1)) : 0.0;
	}
	
	protected boolean checkIfMobIsWithinRangeOfPlayer (Player player, LivingEntity monster)
	{
		// Both the monster and the player needs to be in the same world
		if (player.getLocation().getWorld() != monster.getLocation().getWorld())
		{
			return false;
		}
		
		Location monsterSpawnLocation = monster.getLocation().clone();
		BoundingBox scanRange = player.getBoundingBox().clone();
		
		scanRange.expand(maxScanRadius.doubleValue());
		return scanRange.contains(monsterSpawnLocation.toVector());
	}
}
