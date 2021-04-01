package com.joojet.plugins.mobs.util;

import java.util.List;

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
	
	/** Constructs a weighted entry between the set min and max weights.
	 *  @deprecated Use the new weightedlist to automatically perform min-max weight management. */
	public WeightedEntry (E entry, int minWeight, int maxWeight)
	{
		this.entry = entry;
		this.minWeight = minWeight;
		this.maxWeight = maxWeight;
		this.weight = this.maxWeight - this.minWeight;
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
	
	/** Conducts binary search upon the weighted entry list searching for the entry whose weight range
	 *  satisfies the random number
	 *  	@param roll - Random number
	 *  	@param entryList - A list of weighted entries */
	public static <T> T searchWeightedList (int roll, List <? extends WeightedEntry<T>> entryList)
	{
		WeightedEntry<T> entry = searchWeightedListForWeightedEntry (roll, entryList);
		return entry != null ? entry.getEntry() : null;
	}
	
	/** Conducts binary search upon the weighted entry list searching for a weighted entry whose weight range
	 *  satisfies the random number
	 *  	@param roll - Random number
	 *  	@param entryList - A list of weighted entries */
	public static <T> WeightedEntry<T> searchWeightedListForWeightedEntry (int roll, List <? extends WeightedEntry<T>> entryList)
	{
		int n = entryList.size();
		int left = 0;
		int right = n - 1;
		int pivot;
		WeightedEntry<T> curr;
		
		while (left <= right)
		{
			pivot = (int) Math.floor ((left + right) / 2);
			curr = entryList.get(pivot);
			
			// If the target value is in range between the pivot's weights, return the weighted entry
			if (curr.inRange(roll))
			{
				return curr;
			}
			// Search right if the roll exceeds the current entry's max weight
			else if (roll > curr.getMaxWeight())
			{
				left = pivot + 1;
			}
			// Otherwise, search left
			else
			{
				right = pivot - 1;
			}
		}
		
		// Return null if the mob is not found
		return null;
	}
}
