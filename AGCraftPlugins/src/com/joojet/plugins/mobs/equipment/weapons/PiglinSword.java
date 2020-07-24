package com.joojet.plugins.mobs.equipment.weapons;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.EquipmentSlot;

import com.joojet.plugins.mobs.interfaces.Equipment;

public class PiglinSword extends Equipment 
{
	public PiglinSword (ChatColor color)
	{
		super (Material.GOLDEN_SWORD, EquipmentSlot.HAND, color);
		this.setDisplayName("Piglin Sword");
		this.addUnsafeEnchantment(Enchantment.DAMAGE_ALL, 4);
		this.addUnsafeEnchantment(Enchantment.DURABILITY, 3);
		this.addUnsafeEnchantment(Enchantment.KNOCKBACK, 2);
		this.addUnsafeEnchantment(Enchantment.MENDING, 1);
		this.addLoreToItemMeta("Standard issue.");
	}
}
