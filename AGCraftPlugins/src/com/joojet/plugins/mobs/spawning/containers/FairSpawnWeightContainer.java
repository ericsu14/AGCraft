package com.joojet.plugins.mobs.spawning.containers;

import java.util.ArrayList;

import org.bukkit.entity.Player;

import com.joojet.plugins.mobs.spawning.weights.FairSpawnWeight;

public class FairSpawnWeightContainer 
{
	/** Stores a list of fair spawn weights */
	protected ArrayList <FairSpawnWeight> weights;
	/** Stores the sum of all stored weights */
	protected int sum;
	
	public FairSpawnWeightContainer (FairSpawnWeight... weights)
	{
		this.weights = new ArrayList <FairSpawnWeight> ();
		
		for (FairSpawnWeight weight : weights)
		{
			this.weights.add(weight);
		}
		this.sum = this.generateSumOfWeights();
	}
	
	/** Analyzes a player's inventory and calculates a threatness score based on a combination
	 *  of specified attributes and enchantments found in the player's inventory */
	public double calculateThreatScore (Player player)
	{
		double score = 0.0;

		for (FairSpawnWeight weight : this.weights)
		{
			score += weight.calculateThreatScore(player);
		}
		
		return score / this.getSumOfWeights();
	}
	
	
	/** Returns the sum of all weights stored in this container */
	public int generateSumOfWeights ()
	{
		return weights.stream().reduce(0, (partialSum, weight) -> partialSum + weight.getWeight(), Integer::sum);
	}
	
	/** Returns a pre-generated sum of weights */
	public int getSumOfWeights ()
	{
		return this.sum;
	}
}
