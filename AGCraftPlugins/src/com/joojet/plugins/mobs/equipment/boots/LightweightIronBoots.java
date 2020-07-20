package com.joojet.plugins.mobs.equipment.boots;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.EquipmentSlot;

import com.joojet.plugins.mobs.interfaces.Equipment;

public class LightweightIronBoots extends Equipment
{
	public LightweightIronBoots (ChatColor color)
	{
		super (Material.IRON_BOOTS, EquipmentSlot.FEET, color);
		this.addUnsafeEnchantment(Enchantment.PROTECTION_PROJECTILE, 2);
		this.addUnsafeEnchantment(Enchantment.PROTECTION_FALL, 4);
		this.addLoreToItemMeta("Lightweight iron allows for improved mobility.");
		this.setDisplayName("Lightweight Iron Boots");
		this.addSpeedAttribute(0.15);
		this.addDefenseAttributes(2.0, 1.0, 0.0);
	}
}
