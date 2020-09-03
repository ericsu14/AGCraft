package com.joojet.plugins.mobs.equipment.boots;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.EquipmentSlot;

import com.joojet.plugins.mobs.enums.EquipmentTypes;
import com.joojet.plugins.mobs.equipment.Equipment;

public class RoyalGoldBoots extends Equipment
{
	public RoyalGoldBoots (ChatColor color)
	{
		super (EquipmentTypes.ROYAL_GOLD_BOOTS, Material.GOLDEN_BOOTS, EquipmentSlot.FEET, color);
		this.addUnsafeEnchantment(Enchantment.PROTECTION_PROJECTILE, 3);
		this.addUnsafeEnchantment(Enchantment.PROTECTION_FALL, 4);
		this.addUnsafeEnchantment(Enchantment.DURABILITY, 2);
		this.setDisplayName("Royal Gold Boots");
		this.addLoreToItemMeta("This isn't actually made from 24k gold which offers vastly improved mobility.");
		this.addDefenseAttributes(2.0, 1.0, 0.05);
		this.addSpeedAttribute(0.20);
	}
}
