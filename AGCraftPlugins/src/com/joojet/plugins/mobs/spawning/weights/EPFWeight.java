package com.joojet.plugins.mobs.spawning.weights;

import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

/** A special weight that tells the threat score calculation to search through the equipment's protection
 *  enchantments instead of attribute modifiers */
public class EPFWeight extends FairSpawnWeight {

	public EPFWeight(Double maxValue, Integer weight) 
	{
		super(Attribute.ZOMBIE_SPAWN_REINFORCEMENTS, maxValue, weight);
	}
	
	/** Scans through the player's currently equipped inventory and returns the sum of
	 *  all protection levels found on the player's equipment
	 *  @param player - The player's inventory to be checked. */
	@Override
	protected double getAttributeValue (Player player)
	{
		double value = 0.0;
		ItemStack[] equipmentContent = this.getEquipmentContents(player);
		
		for (ItemStack equipment : equipmentContent)
		{
			if (equipment != null && equipment.getType() != Material.AIR
					&& equipment.containsEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL))
			{
				value += equipment.getEnchantmentLevel(Enchantment.PROTECTION_ENVIRONMENTAL);
			}
		}
		
		return value;
	}

}
