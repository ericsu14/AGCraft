package com.joojet.plugins.mobs.spawning;

import java.util.Arrays;
import java.util.List;

import org.bukkit.attribute.Attribute;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.util.BoundingBox;

import com.joojet.plugins.agcraft.main.AGCraftPlugin;
import com.joojet.plugins.mobs.spawning.containers.FairSpawnWeightContainer;
import com.joojet.plugins.mobs.spawning.weights.EPFWeight;
import com.joojet.plugins.mobs.spawning.weights.FairSpawnWeight;
import com.joojet.plugins.mobs.spawning.weights.MaxHealthWeight;

public class FairSpawnController 
{	
	/** Max scan radius used to calculate a player's threat score when spawning in monsters */
	protected int maxScanRadius;
	/** Used to calculate a threat score for the player */
	protected FairSpawnWeightContainer fairSpawnWeightContainer;
	/** Bias used to amplify game difficulty if there are multiple players around nearby enemy spawns */
	protected double bias;
	
	public FairSpawnController (Integer maxScanRadius)
	{
		this.maxScanRadius = maxScanRadius;
		this.bias = 0.025;
		this.fairSpawnWeightContainer = new FairSpawnWeightContainer (
			new EPFWeight(16.0, 4),
			new FairSpawnWeight (Attribute.GENERIC_ARMOR_TOUGHNESS, 20.0, 2),
			new FairSpawnWeight (Attribute.GENERIC_ARMOR, 20.0, 1),
			new MaxHealthWeight (20.0, 1)
		);
	}
	
	public double getAverageThreatScore (LivingEntity monster)
	{
		int numPlayers = 0;
		double sumOfScores = 0.0;
		
		List<Player> onlinePlayers = Arrays.asList(AGCraftPlugin.plugin.getServer().getOnlinePlayers().toArray(new Player[0]));
		
		// Creates a scan radius using the monster's expanded bounding box
		BoundingBox monsterScanRadius = monster.getBoundingBox().clone();
		monsterScanRadius.expand((double) this.maxScanRadius);
		
		for (Player player : onlinePlayers)
		{
			
			if (monster.getWorld().getEnvironment() == player.getWorld().getEnvironment() 
					&& monsterScanRadius.contains(player.getLocation().toVector()))
			{
				++numPlayers;
				sumOfScores += this.fairSpawnWeightContainer.calculateThreatScore(player);
			}
		}
		return numPlayers > 0 ? (sumOfScores / numPlayers) + (bias * (numPlayers - 1)) : 0.0;
	}
	
	/** Sets the max scan radius used by the thread score calculation algorithm to a different value
	 * 	@param maxRadius - New max scanning radius */
	public void setMaxRadius (int maxRadius)
	{
		this.maxScanRadius = maxRadius;
	}
}
