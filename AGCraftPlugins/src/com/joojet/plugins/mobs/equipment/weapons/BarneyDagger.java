package com.joojet.plugins.mobs.equipment.weapons;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.EquipmentSlot;

import com.joojet.plugins.mobs.enums.EquipmentType;
import com.joojet.plugins.mobs.equipment.Equipment;

public class BarneyDagger extends Equipment
{
	public BarneyDagger (ChatColor color)
	{
		super (EquipmentType.BARNEY_DAGGER, Material.IRON_SWORD, EquipmentSlot.HAND, color);
		this.setDisplayName("Barney's Stupendously Sharp Dagger");
		this.addLoreToItemMeta("Barney knows some pledges are gonna be clapped tonite...");
		this.addAttackAttributes(9.0, 1.6);
		this.addUnsafeEnchantment(Enchantment.KNOCKBACK, 3);
		this.addUnsafeEnchantment(Enchantment.DURABILITY, 2);
		this.addUnsafeEnchantment(Enchantment.FIRE_ASPECT, 1);
	}
}
