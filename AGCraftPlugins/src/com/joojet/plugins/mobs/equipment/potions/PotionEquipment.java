package com.joojet.plugins.mobs.equipment.potions;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.EquipmentSlot;

import com.joojet.plugins.mobs.interfaces.AbstractPotionEquipment;

public abstract class PotionEquipment extends AbstractPotionEquipment 
{
	/** Constructs a Potion with an item count of 1
	 * 		@param color - ChatColor applied to this equipment's name and lore */
	public PotionEquipment (ChatColor color)
	{
		super (Material.POTION, EquipmentSlot.HAND, color);
	}
	
}
