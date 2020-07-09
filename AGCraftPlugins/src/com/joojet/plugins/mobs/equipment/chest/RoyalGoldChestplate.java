package com.joojet.plugins.mobs.equipment.chest;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.EquipmentSlot;

import com.joojet.plugins.mobs.interfaces.Equipment;

public class RoyalGoldChestplate extends Equipment
{
	public RoyalGoldChestplate (ChatColor color)
	{
		super (Material.GOLDEN_CHESTPLATE, EquipmentSlot.CHEST, color);
		this.addUnsafeEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 3);
		this.addUnsafeEnchantment(Enchantment.DURABILITY, 2);
		this.setDisplayName("Royal Gold Chestplate");
		this.addLoreToItemMeta("Passed down by generations.");
		this.addDefenseAttributes(6.0, 2.0, 0.10);
	}
}
