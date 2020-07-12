package com.joojet.plugins.mobs.interfaces;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;

import org.bukkit.block.Biome;

public abstract class MonsterTypes 
{
	private ArrayList <MobEquipment> equipmentList;
	private Random random;
	
	public MonsterTypes ()
	{
		this.equipmentList = new ArrayList <MobEquipment> ();
		this.random = new Random ();
	}
	
	public void addEquipment (MobEquipment equipment, int weight)
	{
		equipment.setSpawnWeight(weight);
		equipmentList.add(equipment);
	}
	
	/** Returns a random mob that spawns within a specific biome
	 * 		@param biome - The biome the monster spawns in */
	public MobEquipment getRandomEquipment (Biome biome)
	{
		int minWeight = 0;
		int maxWeight = 0;
		HashSet <Biome> spawnBiomes;
		ArrayList <WeightedMob> mobList = new ArrayList <WeightedMob> ();
		
		for (MobEquipment mob : equipmentList)
		{
			spawnBiomes = mob.getSpawnBiomes();
			/* Add this mob to the monster spawn list if their spawn biomes either contain THE_VOID
			 * or the passed biome. */
			if (spawnBiomes.contains(Biome.THE_VOID) || spawnBiomes.contains(biome))
			{
				maxWeight = (minWeight + mob.getSpawnWeight()) - 1;
				mobList.add(new WeightedMob (mob, minWeight, maxWeight));
				minWeight = maxWeight + 1;
			}
		}
		
		// Binary searches the list with a random number and returns a random mob
		int roll = this.random.nextInt (minWeight);
		
		if (!mobList.isEmpty())
		{
			return this.searchMobList(roll, mobList);
		}
		return null;
	}
	
	/** Conducts binary search upon the weighted mob list searching for the monster whose weight range
	 *  satisfies the random number
	 *  	@param roll - Random number
	 *  	@param mobList - A list of weighted mobs */
	private MobEquipment searchMobList (int roll, ArrayList <WeightedMob> mobList)
	{
		int n = mobList.size();
		int left = 0;
		int right = n - 1;
		int pivot;
		WeightedMob curr;
		
		while (left <= right)
		{
			pivot = (int) Math.floor ((left + right) / 2);
			curr = mobList.get(pivot);
			
			// If the target value is in range between the pivot's weights, return the mob equipment
			if (curr.inRange(roll))
			{
				return curr.getEquipment();
			}
			// Search right if the roll exceeds the current mob's max weight
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
