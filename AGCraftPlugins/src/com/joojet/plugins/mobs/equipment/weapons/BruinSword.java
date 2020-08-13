package com.joojet.plugins.mobs.equipment.weapons;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.EquipmentSlot;

import com.joojet.plugins.mobs.equipment.Equipment;

public class BruinSword extends Equipment
{
	public BruinSword (ChatColor color)
	{
		super (Material.IRON_SWORD, EquipmentSlot.HAND, color);
		this.setDisplayName("Bruin Sword");
		this.addUnsafeEnchantment(Enchantment.DAMAGE_ALL, 3);
	}
}
