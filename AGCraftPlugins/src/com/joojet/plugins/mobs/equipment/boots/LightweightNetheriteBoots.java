package com.joojet.plugins.mobs.equipment.boots;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.EquipmentSlot;

import com.joojet.plugins.mobs.enums.EquipmentTypes;
import com.joojet.plugins.mobs.equipment.Equipment;

public class LightweightNetheriteBoots extends Equipment
{
	public LightweightNetheriteBoots (ChatColor color)
	{
		super (EquipmentTypes.LIGHTWEIGHT_NETHERITE_BOOTS, Material.NETHERITE_BOOTS, EquipmentSlot.FEET, color);
		this.addUnsafeEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 3);
		this.addUnsafeEnchantment(Enchantment.PROTECTION_FALL, 4);
		this.setDisplayName("Lightweight Netherite Boots");
		this.addLoreToItemMeta("Forged from a mixture of carbon-fiber and netherite, these boots offers vastly improved mobility.");
		this.addSpeedAttribute(0.20);
		this.addDefenseAttributes(3.0, 3.0, 0.10);
	}
}
