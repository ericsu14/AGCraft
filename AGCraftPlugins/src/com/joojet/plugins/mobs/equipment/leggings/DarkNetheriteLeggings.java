package com.joojet.plugins.mobs.equipment.leggings;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.EquipmentSlot;

import com.joojet.plugins.mobs.enums.EquipmentType;
import com.joojet.plugins.mobs.equipment.Equipment;

public class DarkNetheriteLeggings extends Equipment
{
	public DarkNetheriteLeggings (ChatColor color)
	{
		super (EquipmentType.DARK_NETHERITE_LEGGINGS, Material.NETHERITE_LEGGINGS, EquipmentSlot.LEGS, color);
		this.addUnsafeEnchantment(Enchantment.PROTECTION_PROJECTILE, 3);
		this.addUnsafeEnchantment(Enchantment.DURABILITY, 2);
		this.setDisplayName("Dark Netherite Leggings");
	}
}
