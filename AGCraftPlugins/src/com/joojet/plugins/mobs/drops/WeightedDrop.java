package com.joojet.plugins.mobs.drops;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import com.joojet.plugins.mobs.util.WeightedEntry;

public class WeightedDrop extends WeightedEntry<ItemStack> 
{
	/** The material of the item being dropped */
	protected Material material;
	
	public WeightedDrop (ItemStack item, int weight)
	{
		super (item, weight);
		this.material = item.getType();
	}
	
	public WeightedDrop(Material material, int weight) 
	{
		super(new ItemStack (material, 1), weight);
		this.material = material;
	}
	
	public Material getMaterial ()
	{
		return this.material;
	}

}
