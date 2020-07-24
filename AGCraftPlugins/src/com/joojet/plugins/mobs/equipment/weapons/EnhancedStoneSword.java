package com.joojet.plugins.mobs.equipment.weapons;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.EquipmentSlot;

import com.joojet.plugins.mobs.equipment.Equipment;

public class EnhancedStoneSword extends Equipment 
{
	public EnhancedStoneSword (ChatColor color)
	{
		super (Material.STONE_SWORD, EquipmentSlot.HAND, color);
		this.setDisplayName("Enhanced Stone Sword");
		this.addUnsafeEnchantment(Enchantment.DAMAGE_ALL, 1);
		this.addUnsafeEnchantment(Enchantment.DURABILITY, 1);
		this.addLoreToItemMeta("Okay, maybe not that enhanced.");
	}
}
