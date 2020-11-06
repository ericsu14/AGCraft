package com.joojet.plugins.mobs.spawning.weights;

import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Player;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;

public class MaxHealthWeight extends FairSpawnWeight
{
	public MaxHealthWeight (Double maxValue, Integer weight)
	{
		super (Attribute.GENERIC_MAX_HEALTH, maxValue, weight);
	}
	
	
	/** Iterates through each armor-slot in the player's current inventory
	 *  and returns the sum of all max health bonuses found on his/her equipment.
	 *  @param player - Player whose inventory is to be checked. */
	@Override
	protected double getAttributeValue (Player player)
	{
		double value = 0.0;
		ItemStack[] equipmentContent = this.getEquipmentContents(player);
		
		for (int i = 0; i < equipmentContent.length; ++i)
		{
			ItemStack equipment = equipmentContent[i];
			if (equipment != null && equipment.getType() != Material.AIR)
			{
				value += equipment.getItemMeta().
						getAttributeModifiers(EquipmentSlot.values()[i]).get(this.getAttribute()).stream().
						reduce(0.0, (partialSum, modifier) -> partialSum + modifier.getAmount(), Double::sum);
			}
		}
		return value;
	}
}
