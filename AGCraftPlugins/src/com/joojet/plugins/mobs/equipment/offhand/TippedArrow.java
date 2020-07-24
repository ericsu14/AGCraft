package com.joojet.plugins.mobs.equipment.offhand;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.EquipmentSlot;

import com.joojet.plugins.mobs.interfaces.AbstractPotionEquipment;

public abstract class TippedArrow extends AbstractPotionEquipment 
{
	public TippedArrow (ChatColor color)
	{
		super (Material.TIPPED_ARROW, EquipmentSlot.OFF_HAND, color);
	}
	
}
