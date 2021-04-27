package com.joojet.plugins.mobs.equipment.weapons;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.EquipmentSlot;

import com.joojet.plugins.mobs.enums.EquipmentType;
import com.joojet.plugins.mobs.equipment.Equipment;

public class PiglinCrossbow extends Equipment
{

	public PiglinCrossbow (ChatColor chatColor) 
	{
		super(EquipmentType.PIGLIN_CROSSBOW, Material.CROSSBOW, EquipmentSlot.HAND, chatColor);
		this.setDisplayName("Piglin Crossbow");
		this.loreColor = ChatColor.GOLD;
		this.addUnsafeEnchantment(Enchantment.QUICK_CHARGE, 2);
		this.addUnsafeEnchantment(Enchantment.DURABILITY, 3);
		this.addLoreToItemMeta("Good hunting.");
	}

}
