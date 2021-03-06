package com.joojet.plugins.mobs.equipment.chest;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.EquipmentSlot;

import com.joojet.plugins.mobs.enums.EquipmentType;
import com.joojet.plugins.mobs.equipment.Equipment;

public class SnowballHeart extends Equipment
{
	public SnowballHeart (ChatColor color)
	{
		super (EquipmentType.SNOWBALL_HEART, Material.SNOWBALL, EquipmentSlot.CHEST, color);
		this.setDisplayName("Snowball's Heart");
		this.addLoreToItemMeta("His fur is as white as snow.");
		this.addDefenseAttributes(8.0, 6.0, 0.75);
		this.addAttackAttributes(6.0, 0.0);
		this.addSpeedAttribute(0.20);
		this.addUnsafeEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 3);
		this.addUnsafeEnchantment(Enchantment.PROTECTION_EXPLOSIONS, 3);
		this.addUnsafeEnchantment(Enchantment.PROTECTION_FALL, 3);
		this.addUnsafeEnchantment(Enchantment.THORNS, 2);
		this.addUnsafeEnchantment(Enchantment.DEPTH_STRIDER, 2);
	}
}
