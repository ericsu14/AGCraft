package com.joojet.plugins.mobs.equipment.leggings;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.EquipmentSlot;

import com.joojet.plugins.mobs.enums.EquipmentTypes;
import com.joojet.plugins.mobs.equipment.Equipment;

public class RoyalGoldLeggings extends Equipment
{
	public RoyalGoldLeggings (ChatColor color)
	{
		super (EquipmentTypes.ROYAL_GOLD_LEGGINGS, Material.GOLDEN_LEGGINGS, EquipmentSlot.LEGS, color);
		this.addUnsafeEnchantment(Enchantment.PROTECTION_FIRE, 3);
		this.addUnsafeEnchantment(Enchantment.DURABILITY, 2);
		this.setDisplayName("Royal Gold Leggings");
		this.addLoreToItemMeta("Passed down by generations.");
		this.addDefenseAttributes(5.0, 2.0, 0.05);
	}
}
