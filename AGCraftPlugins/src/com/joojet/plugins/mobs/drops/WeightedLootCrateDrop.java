package com.joojet.plugins.mobs.drops;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import com.joojet.plugins.mobs.util.WeightedList;

public class WeightedLootCrateDrop extends MonsterDrop
{
	/** A list of weighted drops */
	protected WeightedList <WeightedDrop> dropList;
	
	/** Creates a new weighted loot crate drop, which allows the monster to drop a mystery item from a list of possible drops with weights. */
	public WeightedLootCrateDrop(double dropRate, int minAmount, int maxAmount, WeightedDrop ...drops) 
	{
		super(Material.STICK, dropRate, minAmount, maxAmount);
		this.dropList = new WeightedList <WeightedDrop> ();
		
		for (WeightedDrop drop : drops)
		{
			this.addWeightedDrop(drop);
		}
	}
	
	/** Adds a new weighted drop into the list of items that can be dropped */
	public void addWeightedDrop (WeightedDrop drop)
	{
		this.dropList.addEntry(drop);
	}
	
	/** Drops a randomly chosen item from the given list of drops */
	@Override
	public ItemStack generateDrop (double looting)
	{
		if (rand.nextDouble() <= (this.dropRate + looting))
		{
			ItemStack item = this.dropList.getRandomWeightedEntry().getEntry();
			
			if (item != null && this.maxAmount <= item.getMaxStackSize())
			{	
				item.setAmount(this.generateRandomInt());
			}
			return item;
		}
		return null;
	}

}
