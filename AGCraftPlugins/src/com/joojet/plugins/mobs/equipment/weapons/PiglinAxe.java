package com.joojet.plugins.mobs.equipment.weapons;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.EquipmentSlot;

import com.joojet.plugins.mobs.enums.EquipmentType;
import com.joojet.plugins.mobs.equipment.Equipment;

public class PiglinAxe extends Equipment
{
	public PiglinAxe (ChatColor color)
	{
		super (EquipmentType.PIGLIN_AXE, Material.GOLDEN_AXE, EquipmentSlot.HAND, color);
		this.setDisplayName("Piglin Axe");
		this.addLoreToItemMeta("Weilded only by the strongest of all Piglins...");
		this.addAttackAttributes(10.0, 1.9);
		this.addUnsafeEnchantment(Enchantment.DAMAGE_ALL, 4);
		this.addUnsafeEnchantment(Enchantment.DURABILITY, 3);
		this.addUnsafeEnchantment(Enchantment.KNOCKBACK, 3);
		this.addUnsafeEnchantment(Enchantment.MENDING, 1);
	}
}
