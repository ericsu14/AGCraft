package com.joojet.plugins.mobs.equipment.boots;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.EquipmentSlot;

import com.joojet.plugins.mobs.enums.EquipmentTypes;
import com.joojet.plugins.mobs.equipment.Equipment;

public class LightweightDiamondBoots extends Equipment 
{
	public LightweightDiamondBoots (ChatColor color)
	{
		super (EquipmentTypes.LIGHTWEIGHT_DIAMOND_BOOTS, Material.DIAMOND_BOOTS, EquipmentSlot.FEET, color);
		this.setDisplayName("Reinforced Diamond Boots");
		this.addLoreToItemMeta("A lighter cut of diamond allows for improved mobility.");
		this.addUnsafeEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 2);
		this.addUnsafeEnchantment(Enchantment.PROTECTION_FALL, 4);
		this.addUnsafeEnchantment(Enchantment.DURABILITY, 3);
		this.addSpeedAttribute(0.20);
		this.addDefenseAttributes(3.0, 2.0, 0.0);
	}
}
