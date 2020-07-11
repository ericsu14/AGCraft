package com.joojet.plugins.mobs.interfaces;

import java.util.ArrayList;
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
		for (int i = 0; i < weight; ++i)
		{
			equipmentList.add(equipment);
		}
	}
	
	/** Returns any random mob contained within this equipment list */
	public MobEquipment getRandomEquipment ()
	{
		int n = equipmentList.size();
		Random rand = new Random ();
		return equipmentList.get(rand.nextInt(n));
	}
	
	/** Returns a random mob that spawns within a specific biome
	 * 		@param biome - The biome the monster spawns in */
	public MobEquipment getRandomEquipment (Biome biome)
	{
		ArrayList <WeightedMob> mobList = new ArrayList <WeightedMob> ();
		
		int minWeight = 0;
		int maxWeight = 0;
		for (MobEquipment mob : equipmentList)
		{
			// Iterate through each mob's spawn biomes
			ArrayList <Biome> spawnBiomes = mob.getSpawnBiomes();
			for (Biome b : spawnBiomes)
			{
				/* Found biome, add to the list and break out of the loop */
				if (b == Biome.THE_VOID || biome == b)
				{
					maxWeight = (minWeight + mob.getSpawnWeight()) - 1;
					mobList.add(new WeightedMob (mob, minWeight, maxWeight));
					minWeight = maxWeight + 1;
					break;
				}
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
