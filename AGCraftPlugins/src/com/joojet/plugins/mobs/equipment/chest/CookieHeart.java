package com.joojet.plugins.mobs.equipment.chest;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.EquipmentSlot;

import com.joojet.plugins.mobs.interfaces.Equipment;

public class CookieHeart extends Equipment
{
	public CookieHeart (ChatColor color)
	{
		super (Material.COOKIE, EquipmentSlot.CHEST, color);
		this.addDefenseAttributes(14.0, 8.0, 0.5);
		this.addAttackAttributes(8.0, 0.0);
		this.addSpeedAttribute(0.25);
		this.addHealthAttributes(10.0);
		this.setDisplayName("Cookie's Heart");
		this.addLoreToItemMeta("Contains the heart of a beautiful soul.");
		this.addUnsafeEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 3);
		this.addUnsafeEnchantment(Enchantment.PROTECTION_EXPLOSIONS, 5);
		this.addUnsafeEnchantment(Enchantment.PROTECTION_FALL, 4);
		this.addUnsafeEnchantment(Enchantment.THORNS, 3);
		this.addUnsafeEnchantment(Enchantment.DEPTH_STRIDER, 5);
	}
}
