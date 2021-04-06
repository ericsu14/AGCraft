package com.joojet.plugins.mobs.equipment.potions;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.EquipmentSlot;

import com.joojet.plugins.mobs.enums.EquipmentType;
import com.joojet.plugins.mobs.equipment.AbstractPotionEquipment;

public abstract class SplashPotionEquipment extends AbstractPotionEquipment 
{
	public SplashPotionEquipment(EquipmentType equipmentType, ChatColor color) 
	{
		super(equipmentType, Material.SPLASH_POTION, EquipmentSlot.HAND, color);
	}

}
