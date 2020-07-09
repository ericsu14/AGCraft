package com.joojet.plugins.mobs.equipment.chest;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.EquipmentSlot;

import com.joojet.plugins.mobs.interfaces.Equipment;

public class DarkNetheriteChestplate extends Equipment
{
	public DarkNetheriteChestplate (ChatColor color)
	{
		super (Material.NETHERITE_CHESTPLATE, EquipmentSlot.CHEST, color);
		this.addUnsafeEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 4);
		this.addUnsafeEnchantment(Enchantment.VANISHING_CURSE, 1);
		this.addUnsafeEnchantment(Enchantment.THORNS, 2);
		this.setDisplayName("Dark Netherite Chestplate");
	}
}
