package com.joojet.plugins.mobs.equipment.offhand;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.EquipmentSlot;

import com.joojet.plugins.mobs.enums.EquipmentTypes;
import com.joojet.plugins.mobs.equipment.AbstractPotionEquipment;

public abstract class TippedArrow extends AbstractPotionEquipment 
{
	/** Constructs a tipped arrow with a count of 1
	 * 		@param equipmentType - Type of equipment this is
	 * 		@param color - ChatColor applied to the lore and name of the tipped arrow */
	public TippedArrow (EquipmentTypes equipmentType, ChatColor color)
	{
		super (equipmentType, Material.TIPPED_ARROW, EquipmentSlot.OFF_HAND, color);
	}
	
	/** Constructs a tipped arrow with a custom item count
	 * 		@param equipmentType - Type of equipment this is
	 * 		@param color - ChatColor applied to the lore and name of the tipped arrow
	 * 		@param count - Number of arrows in this stack */
	public TippedArrow (EquipmentTypes equipmentType, ChatColor color, int count)
	{
		super (equipmentType, Material.TIPPED_ARROW, EquipmentSlot.OFF_HAND, color, count);
	}
}
