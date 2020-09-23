package com.joojet.plugins.mobs.equipment.head;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.EquipmentSlot;

import com.joojet.plugins.mobs.enums.EquipmentType;
import com.joojet.plugins.mobs.equipment.Equipment;

public class DarkNetheriteHelmet extends Equipment
{
	public DarkNetheriteHelmet (ChatColor color)
	{
		super (EquipmentType.DARK_NETHERITE_HELMET, Material.NETHERITE_HELMET, EquipmentSlot.HEAD, color);
		this.addUnsafeEnchantment(Enchantment.PROTECTION_PROJECTILE, 3);
		this.addUnsafeEnchantment(Enchantment.DURABILITY, 2);
		this.setDisplayName("Dark Netherite Helmet");
	}
}
