package com.joojet.plugins.mobs.equipment.weapons;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.EquipmentSlot;

import com.joojet.plugins.mobs.enums.EquipmentTypes;
import com.joojet.plugins.mobs.equipment.Equipment;

public class SpiderTooth extends Equipment 
{
	public SpiderTooth (ChatColor color)
	{
		super (EquipmentTypes.SPIDER_TOOTH, Material.STONE_SWORD, EquipmentSlot.HAND, color);
		this.addUnsafeEnchantment(Enchantment.DAMAGE_ALL, 2);
		this.addUnsafeEnchantment(Enchantment.KNOCKBACK, 1);
		this.addUnsafeEnchantment(Enchantment.DURABILITY, 1);
		this.setDisplayName("Spider Tooth");
		this.addLoreToItemMeta("You are lucky this spider ain't poisonous...");
	}
}
