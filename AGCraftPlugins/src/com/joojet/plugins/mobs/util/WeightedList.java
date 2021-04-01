package com.joojet.plugins.mobs.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class WeightedList <T extends WeightedEntry <E>, E>
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
	public WeightedEntry <E> getRandomWeightedEntry ()
	{
		return this.getWeightedEntry (this.rand.nextInt(this.minWeight));
	}
	
	/** Selects a random weighted entry in the weighted list based on a random roll using binary search */
	public WeightedEntry <E> getWeightedEntry (int roll)
	{
		return WeightedEntry.searchWeightedListForWeightedEntry (roll, this.entries);
	}
	
	/** Selects a random entry in the weighted list using binary search */
	public E getRandomEntry ()
	{
		return this.getEntry(this.rand.nextInt(this.minWeight));
	}
	
	/** Selects a random weighted entry in the weighted list based on a random roll using binary search */
	public E getEntry (int roll)
	{
		return WeightedEntry.searchWeightedList(roll, this.entries);
	}
}
