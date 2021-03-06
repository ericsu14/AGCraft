package com.joojet.plugins.mobs.equipment.boots;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.EquipmentSlot;

import com.joojet.plugins.mobs.enums.EquipmentType;
import com.joojet.plugins.mobs.equipment.Equipment;

public class LetItGo extends Equipment
{
	public LetItGo (ChatColor color)
	{
		super (EquipmentType.LET_IT_GO, Material.GOLDEN_BOOTS, EquipmentSlot.FEET, color);
		this.addUnsafeEnchantment(Enchantment.FROST_WALKER, 3);
		this.addUnsafeEnchantment(Enchantment.DEPTH_STRIDER, 3);
		this.addUnsafeEnchantment(Enchantment.SOUL_SPEED, 1);
		this.addUnsafeEnchantment(Enchantment.VANISHING_CURSE, 1);
		this.setDisplayName("Let it go!");
	}
}
