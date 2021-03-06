package com.joojet.plugins.mobs.equipment.weapons;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.EquipmentSlot;

import com.joojet.plugins.mobs.enums.EquipmentType;
import com.joojet.plugins.mobs.equipment.Equipment;

public class AngelOfDeath extends Equipment
{
	public AngelOfDeath (ChatColor color)
	{
		super (EquipmentType.ANGEL_OF_DEATH, Material.BOW, EquipmentSlot.HAND, color);
		this.addUnsafeEnchantment(Enchantment.ARROW_DAMAGE, 5);
		this.addUnsafeEnchantment(Enchantment.ARROW_FIRE, 1);
		this.addUnsafeEnchantment(Enchantment.DURABILITY, 2);
		this.addUnsafeEnchantment(Enchantment.ARROW_KNOCKBACK, 1);
		this.setDisplayName("Angel of Death");
		this.addLoreToItemMeta("You can have it when you pry it from my cold, dead hands.");
	}
}
