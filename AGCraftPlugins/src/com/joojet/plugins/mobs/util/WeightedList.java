package com.joojet.plugins.mobs.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class WeightedList <T extends WeightedEntry<?>>
{ 
	/** An internal list of weighted entries */
	protected List <T> entries;
	/** Current min weight range */
	protected int minWeight;
	/** Current max weight range */
	protected int maxWeight;
	/** RNG used to retrieve random values */
	protected Random rand;
	
	/** Constructs a new weighted list */
	public WeightedList ()
	{
		this.entries = new ArrayList <T> ();
		this.minWeight = 0;
		this.maxWeight = 0;
		this.rand = new Random ();
	}
	
	/** Adds a new weighted entry into the list */
	public void addEntry (T weightedEntry)
	{
		int weight = weightedEntry.getWeight();
		this.maxWeight = (this.minWeight + weight) - 1;
		weightedEntry.setWeightRanges(this.minWeight, this.maxWeight);
		this.entries.add(weightedEntry);
		this.minWeight = this.maxWeight + 1;
	}
	
	/** Removes all entries from the weighted list */
	public void clear ()
	{
		this.entries.clear();
		this.minWeight = 0;
		this.maxWeight = 0;
	}
	
	/** Returns the total amount of weight added into this list, which can be used to generate
	 *  a random roll */
	public int getTotalWeight ()
	{
		return this.minWeight;
	}
	
	/** Returns true if the weighted list is empty */
	public boolean isEmpty ()
	{
		return this.entries.isEmpty();
	}
	
	/** Selects a random weighted entry in the weighted list using binary search */
	public T getRandomWeightedEntry ()
	{
		return this.getWeightedEntry (this.rand.nextInt(this.minWeight));
	}
	
	/** Selects a random weighted entry in the weighted list based on a random roll using binary search */
	public T getWeightedEntry (int roll)
	{
		int n = this.entries.size();
		int left = 0;
		int right = n - 1;
		int pivot;
		T curr;
		
		while (left <= right)
		{
			pivot = (int) Math.floor ((left + right) / 2);
			curr = this.entries.get(pivot);
			
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
