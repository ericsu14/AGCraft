package com.joojet.plugins.mobs.spawning;

import java.util.ArrayList;

import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

import com.joojet.plugins.mobs.spawning.weights.EPFWeight;
import com.joojet.plugins.mobs.spawning.weights.FairSpawnWeight;

public class FairSpawnWeightContainer 
{
	/** Stores a list of fair spawn weights */
	protected ArrayList <FairSpawnWeight> weights;
	/** Stores the sum of all stored weights */
	protected Integer sum;
	
	public FairSpawnWeightContainer (FairSpawnWeight... weights)
	{
		this.weights = new ArrayList <FairSpawnWeight> ();
		
		for (FairSpawnWeight weight : weights)
		{
			this.weights.add(weight);
		}
		this.sum = this.generateSumOfWeights();
	}
	
	/** Analyzes a player's inventory and calculates a threatness score based on a combination
	 *  of specified attributes and enchantments found in the player's inventory */
	public Double calculateThreatScore (Player player)
	{
		Double score = 0.0;
		Double rawModifierSum = 0.0;
		ItemStack[] equipmentContent = this.getEquipmentContents(player);
		for (FairSpawnWeight weight : this.weights)
		{
			rawModifierSum = 0.0;
			for (int i = 0; i < equipmentContent.length; ++i)
			{
				ItemStack equipment = equipmentContent[i];
				if (equipment != null && equipment.getType() != Material.AIR)
				{
					if (weight instanceof EPFWeight && 
							equipment.containsEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL))
					{
						rawModifierSum += equipment.getEnchantmentLevel(Enchantment.PROTECTION_ENVIRONMENTAL);
					}
					// Generic max health can only be increased by equipment
					else if (!(weight instanceof EPFWeight) && weight.getAttribute() == Attribute.GENERIC_MAX_HEALTH)
					{
						rawModifierSum += equipment.getItemMeta().
							getAttributeModifiers(EquipmentSlot.values()[i]).get(weight.getAttribute()).stream().
							reduce(0.0, (partialSum, modifier) -> partialSum + modifier.getAmount(), Double::sum);
					}
				}
			}
			
			if (weight.getAttribute() != Attribute.GENERIC_MAX_HEALTH
					&& player.getAttribute(weight.getAttribute()) != null)
			{
				rawModifierSum += player.getAttribute(weight.getAttribute()).getValue();
			}
			
			score += weight.calculateRawThreatScore(rawModifierSum);
		}
		
		return score / this.getSumOfWeights();
	}
	
	/** Fetches the player's inventory contents as an array, excluding the held weapon */
	protected ItemStack[] getEquipmentContents (Player player)
	{
		PlayerInventory playerInventory = player.getInventory();
		ItemStack[] equipment = new ItemStack [EquipmentSlot.values().length];
		equipment [EquipmentSlot.HEAD.ordinal()] = playerInventory.getHelmet();
		equipment [EquipmentSlot.CHEST.ordinal()] = playerInventory.getChestplate();
		equipment [EquipmentSlot.LEGS.ordinal()] = playerInventory.getLeggings();
		equipment [EquipmentSlot.FEET.ordinal()] = playerInventory.getBoots();
		equipment [EquipmentSlot.OFF_HAND.ordinal()] = playerInventory.getItemInOffHand();
		return equipment;
	}
	
	/** Returns the sum of all weights stored in this container */
	public Integer generateSumOfWeights ()
	{
		return weights.stream().reduce(0, (partialSum, weight) -> partialSum + weight.getWeight(), Integer::sum);
	}
	
	/** Returns a pre-generated sum of weights */
	public Integer getSumOfWeights ()
	{
		return this.sum;
	}
}
