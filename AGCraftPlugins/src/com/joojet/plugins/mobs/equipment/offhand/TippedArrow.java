package com.joojet.plugins.mobs.equipment.offhand;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.EquipmentSlot;

import com.joojet.plugins.mobs.equipment.AbstractPotionEquipment;

public abstract class TippedArrow extends AbstractPotionEquipment 
{
	/** Constructs a tipped arrow with a count of 1
	 * 		@param color - ChatColor applied to the lore and name of the tipped arrow */
	public TippedArrow (ChatColor color)
	{
		super (Material.TIPPED_ARROW, EquipmentSlot.OFF_HAND, color);
	}
	
	/** Constructs a tipped arrow with a custom item count
	 * 		@param color - ChatColor applied to the lore and name of the tipped arrow
	 * 		@param count - Number of arrows in this stack */
	public TippedArrow (ChatColor color, int count)
	{
		super (Material.TIPPED_ARROW, EquipmentSlot.OFF_HAND, color, count);
	}
}
