package com.joojet.plugins.mobs.equipment.potions;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.EquipmentSlot;

import com.joojet.plugins.mobs.enums.EquipmentType;
import com.joojet.plugins.mobs.equipment.AbstractPotionEquipment;

public abstract class PotionEquipment extends AbstractPotionEquipment 
{
	/** Constructs a Potion with an item count of 1
	 * 		@param equipmentType - Type of equipment this is 
	 * 		@param color - ChatColor applied to this equipment's name and lore */
	public PotionEquipment (EquipmentType equipmentType, ChatColor color)
	{
		super (equipmentType, Material.POTION, EquipmentSlot.HAND, color);
	}
	
}
