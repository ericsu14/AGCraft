package com.joojet.plugins.mobs.equipment.chest;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.EquipmentSlot;

import com.joojet.plugins.mobs.enums.EquipmentTypes;
import com.joojet.plugins.mobs.equipment.Equipment;

public class DarkNetheriteChestplate extends Equipment
{
	public DarkNetheriteChestplate (ChatColor color)
	{
		super (EquipmentTypes.DARK_NETHERITE_CHEST, Material.NETHERITE_CHESTPLATE, EquipmentSlot.CHEST, color);
		this.addUnsafeEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 4);
		this.addUnsafeEnchantment(Enchantment.THORNS, 2);
		this.addUnsafeEnchantment(Enchantment.DURABILITY, 3);
		this.setDisplayName("Dark Netherite Chestpate");
	}
}
