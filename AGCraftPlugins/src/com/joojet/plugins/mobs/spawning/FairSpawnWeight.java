package com.joojet.plugins.mobs.spawning;

import org.bukkit.attribute.Attribute;

public class FairSpawnWeight 
{
	/** The attribute this spawn weight is tied to */
	protected Attribute attribute;
	/** The maximum value of this attribute that can be reached in vanilla Minecraft */
	protected Double maxValue;
	/** Determines how much this weighs in when determining a player's threat score */
	protected Integer weight;
	
	public FairSpawnWeight (Attribute attribute, Double maxValue, Integer weight)
	{
		this.attribute = attribute;
		this.maxValue = maxValue;
		this.weight = weight;
	}
	
	/** Calculates a raw threat value for the passed value */
	public Double calculateRawThreatScore (Double value)
	{
		return this.weight * (value / this.maxValue);
	}
	
	/** Returns the attribute tied to this fair spawn weight */
	public Attribute getAttribute ()
	{
		return this.attribute;
	}
	
	/** Returns the weight */
	public Integer getWeight ()
	{
		return this.weight;
	}
}
