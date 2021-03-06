package com.joojet.plugins.mobs.equipment.boots;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.EquipmentSlot;

import com.joojet.plugins.mobs.enums.EquipmentType;
import com.joojet.plugins.mobs.equipment.Equipment;

public class LightweightChainmailBoots extends Equipment
{
	public LightweightChainmailBoots (ChatColor color)
	{
		super (EquipmentType.LIGHTWEIGHT_CHAINMAIL_BOOTS, Material.CHAINMAIL_BOOTS, EquipmentSlot.FEET, color);
		this.setDisplayName("Lightweight Chainmail Boots");
		this.addUnsafeEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 1);
		this.addUnsafeEnchantment(Enchantment.PROTECTION_FALL, 2);
		this.addLoreToItemMeta("Lightweight chains allows for improved mobility.");
		this.addSpeedAttribute(0.10);
		this.addDefenseAttributes(2.0, 0.0, 0.0);
	}
}
