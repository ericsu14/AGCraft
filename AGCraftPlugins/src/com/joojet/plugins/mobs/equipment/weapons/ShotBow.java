package com.joojet.plugins.mobs.equipment.weapons;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.EquipmentSlot;

import com.joojet.plugins.mobs.equipment.Equipment;

public class ShotBow extends Equipment
{
	public ShotBow (ChatColor color)
	{
		super (Material.CROSSBOW, EquipmentSlot.HAND, color);
		this.setDisplayName("ShotBow");
		this.addLoreToItemMeta("A quick-firing crossbow that shoots multiple high-penetrating arrows at quick succession. Mending cannot be applied to this weapon.");
		this.addUnsafeEnchantment(Enchantment.QUICK_CHARGE, 5);
		this.addUnsafeEnchantment(Enchantment.PIERCING, 3);
		this.addUnsafeEnchantment(Enchantment.MULTISHOT, 1);
		this.addUnsafeEnchantment(Enchantment.ARROW_INFINITE, 1);
	}
}
