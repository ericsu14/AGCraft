package com.joojet.plugins.mobs.equipment.weapons;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.EquipmentSlot;

import com.joojet.plugins.mobs.enums.EquipmentType;
import com.joojet.plugins.mobs.equipment.Equipment;

public class RoyalPiglinCrossbow extends Equipment
{
	public RoyalPiglinCrossbow (ChatColor color)
	{
		super (EquipmentType.ROYAL_PIGLIN_CROSSBOW, Material.CROSSBOW, EquipmentSlot.HAND, color);
		this.setDisplayName("Royal Piglin Crossbow");
		this.addUnsafeEnchantment(Enchantment.PIERCING, 3);
		this.addUnsafeEnchantment(Enchantment.MULTISHOT, 1);
		this.addUnsafeEnchantment(Enchantment.DURABILITY, 5);
		this.addUnsafeEnchantment(Enchantment.QUICK_CHARGE, 3);
		
		this.addLoreToItemMeta("A crossbow with highly illegal enchantment pairings. Use with care.");
	}
}
