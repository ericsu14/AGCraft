package com.joojet.plugins.mobs.equipment.weapons;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.EquipmentSlot;

import com.joojet.plugins.mobs.equipment.Equipment;

public class SharpenedIronSword extends Equipment
{
	public SharpenedIronSword (ChatColor color)
	{
		super (Material.IRON_SWORD, EquipmentSlot.HAND, color);
		this.addAttackAttributes(7.0, 2.0);
		this.addUnsafeEnchantment(Enchantment.DAMAGE_ALL, 1);
		this.addUnsafeEnchantment(Enchantment.DURABILITY, 1);
		this.addLoreToItemMeta("Sharpened with a 10000 grit waterstone, these swords deal just as much damage as a Diamond Sword.");
		this.setDisplayName("Sharpened Iron Sword");
	}
}
