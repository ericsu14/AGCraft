package com.joojet.plugins.mobs.spawning.weights;

import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Player;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

public class FairSpawnWeight 
{
	/** The attribute this spawn weight is tied to */
	protected Attribute attribute;
	/** The maximum value of this attribute that can be reached in vanilla Minecraft */
	protected double maxValue;
	/** Determines how much this weighs in when determining a player's threat score */
	protected int weight;
	
	public FairSpawnWeight (Attribute attribute, Double maxValue, Integer weight)
	{
		this.attribute = attribute;
		this.maxValue = maxValue;
		this.weight = weight;
	}
	
	/** Calculates a threat score for a given player */
	public double calculateThreatScore (Player player)
	{
		return this.weight * (this.getAttributeValue(player) / this.maxValue);
	}
	
	/** Returns the attribute tied to this fair spawn weight */
	public Attribute getAttribute ()
	{
		return this.attribute;
	}
	
	/** Returns the weight */
	public Integer getWeight ()
	{
		return this.weight;
	}
	
	/** Fetches the player's attribute value for the attribute assigned to this weight.
	 *  Returns 0.0 if the player does not have the attribute assigned to this weight. */
	protected double getAttributeValue (Player player)
	{
		if (player.getAttribute(this.getAttribute()) != null)
		{
			return player.getAttribute(this.getAttribute()).getValue();
		}
		return 0.0;
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
}
