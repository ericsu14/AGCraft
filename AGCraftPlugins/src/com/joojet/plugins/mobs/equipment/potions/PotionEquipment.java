package com.joojet.plugins.mobs.equipment.potions;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.EquipmentSlot;

import com.joojet.plugins.mobs.interfaces.AbstractPotionEquipment;

public abstract class PotionEquipment extends AbstractPotionEquipment 
{
	public PotionEquipment (ChatColor color)
	{
		super (Material.POTION, EquipmentSlot.HAND, color);
	}
}
