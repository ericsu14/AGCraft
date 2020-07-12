package com.joojet.plugins.mobs.equipment.leggings;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.EquipmentSlot;

import com.joojet.plugins.mobs.interfaces.Equipment;

public class DarkNetheriteLeggings extends Equipment
{
	public DarkNetheriteLeggings (ChatColor color)
	{
		super (Material.NETHERITE_LEGGINGS, EquipmentSlot.LEGS, color);
		this.addUnsafeEnchantment(Enchantment.PROTECTION_PROJECTILE, 3);
	}
}
