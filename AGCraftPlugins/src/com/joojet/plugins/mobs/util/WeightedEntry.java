package com.joojet.plugins.mobs.util;

import java.util.ArrayList;

public abstract class WeightedEntry <E> 
{
	/** The stored entry */
	protected E entry;
	/** Min range */
	protected int minWeight;
	/** Max range */
	protected int maxWeight;
	
	public WeightedEntry (E entry, int minWeight, int maxWeight)
	{
		this.entry = entry;
		this.minWeight = minWeight;
		this.maxWeight = maxWeight;
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
	
	/** Conducts binary search upon the weighted entry list searching for the entry whose weight range
	 *  satisfies the random number
	 *  	@param roll - Random number
	 *  	@param entryList - A list of weighted entries */
	public static <T> T searchWeightedList (int roll, ArrayList <? extends WeightedEntry<T>> entryList)
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
				return curr.getEntry();
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
