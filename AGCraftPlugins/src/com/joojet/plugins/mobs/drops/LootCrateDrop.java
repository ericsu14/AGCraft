package com.joojet.plugins.mobs.drops;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import com.joojet.plugins.mobs.equipment.EquipmentLoader;

/** A special monster drop that drops a random item
 *  from a set of given materials. 
 *  All drops have an equally random chance of dropping. */
public class LootCrateDrop extends MonsterDrop 
{
	protected List <Material> possibleDrops;
	
	public LootCrateDrop(double dropRate, int minAmount, int maxAmount, Material ...materials) 
	{
		super(Material.STICK, dropRate, minAmount, maxAmount);
		this.possibleDrops = new ArrayList <Material> ();
		for (Material material :  materials)
		{
			this.possibleDrops.add(material);
		}
	}
	
	@Override
	public ItemStack generateDrop (double looting, EquipmentLoader equipmentLoader)
	{
		if (rand.nextDouble() <= (this.dropRate + looting))
		{
			return new ItemStack (this.possibleDrops.get(this.rand.nextInt(this.possibleDrops.size())), 
					this.generateRandomInt());
		}
		return null;
	}

}
