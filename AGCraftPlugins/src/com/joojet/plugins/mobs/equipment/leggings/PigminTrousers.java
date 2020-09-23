package com.joojet.plugins.mobs.equipment.leggings;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.EquipmentSlot;

import com.joojet.plugins.mobs.enums.EquipmentType;
import com.joojet.plugins.mobs.equipment.Equipment;


public class PigminTrousers extends Equipment
{
	public PigminTrousers (ChatColor color)
	{
		super (EquipmentType.PIGMAN_TROUSERS, Material.GOLDEN_LEGGINGS, EquipmentSlot.LEGS, color);
		this.setDisplayName("Pigmen Trousers");
		this.loreColor = ChatColor.GOLD;
		this.wordsPerLine = 6;
		this.addLoreToItemMeta("These fancy trousers were handed over through many generations. It is said that wearing these trousers may make you feel lucky today!");
		this.addDefenseAttributes(4.0, 6.0, 0.10);
		this.addUnsafeEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 3);
		this.addUnsafeEnchantment(Enchantment.DURABILITY, 2);
		this.addUnsafeEnchantment(Enchantment.LOOT_BONUS_MOBS, 1);
	}
}
