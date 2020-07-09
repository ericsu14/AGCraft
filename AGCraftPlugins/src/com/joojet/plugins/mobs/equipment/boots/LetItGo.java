package com.joojet.plugins.mobs.equipment.boots;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.EquipmentSlot;

import com.joojet.plugins.mobs.interfaces.Equipment;

public class LetItGo extends Equipment
{
	public LetItGo (ChatColor color)
	{
		super (Material.GOLDEN_BOOTS, EquipmentSlot.FEET, color);
		this.addUnsafeEnchantment(Enchantment.FROST_WALKER, 1);
		this.addUnsafeEnchantment(Enchantment.VANISHING_CURSE, 1);
		this.setDisplayName("Let it go!");
	}
}
