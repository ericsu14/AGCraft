package com.joojet.plugins.mobs.spawning.weights;

import org.bukkit.attribute.Attribute;

public class MaxHealthWeight extends FairSpawnWeight {

	public MaxHealthWeight(Double maxValue, Integer weight) 
	{
		super(Attribute.GENERIC_MAX_HEALTH, maxValue, weight);
	}
	
	
	/** Calculates the health's fairness score in a specialized way */
	public Double calculateRawThreatScore (Double value)
	{
		return this.weight * (((value + 20.0) / this.maxValue) - 1.0);
	}
}
