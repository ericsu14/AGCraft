package com.joojet.plugins.mobs.util;

public abstract class WeightedEntry <E> 
{
	/** The stored entry */
	protected E entry;
	/** Min range */
	protected int minWeight;
	/** Max range */
	protected int maxWeight;
	/** Weight assigned to the entry */
	protected int weight;
	
	public WeightedEntry (E entry, int weight)
	{
		this.entry = entry;
		this.minWeight = 0;
		this.maxWeight = 0;
		this.weight = weight;
	}
	
	/** Returns true if the random roll is within the min and max ranges for this entry */
	public boolean inRange (int roll)
	{
		return (roll >= this.minWeight && roll <= this.maxWeight);
	}
	
	/** Returns the entry attached to this weight */
	public E getEntry ()
	{
		return this.entry;
	}
	
	/** Returns the weight's min range */
	public int getMinWeight ()
	{
		return this.minWeight;
	}
	
	/** Returns the weight's max range */
	public int getMaxWeight ()
	{
		return this.maxWeight;
	}
	
	/** Returns the weight assigned to this entry */
	public int getWeight ()
	{
		return this.weight;
	}
	
	/** Sets the min and max weights for this entry to its new values
	 *  @param minWeight Weight's new min range
	 *  @param maxWeight Weight's new max range */
	public void setWeightRanges (int minWeight, int maxWeight)
	{
		this.minWeight = minWeight;
		this.maxWeight = maxWeight;
	}
}
