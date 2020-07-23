package com.joojet.plugins.mobs.equipment.boots;

import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.EquipmentSlot;

import com.joojet.plugins.mobs.interfaces.LeatherEquipment;

public class DoomGuyFeet extends LeatherEquipment
{
	public DoomGuyFeet (ChatColor color)
	{
		super (Material.LEATHER_BOOTS, EquipmentSlot.FEET, color);
		this.setDisplayName("Boots of the Doom Slayer");
		this.addLoreToItemMeta("In his crusade, the seraphim bestowed upon him terrible power and speed, and with his might he crushed the obsidian pillars of the Blood Temples...");
		this.addUnsafeEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 4);
		this.addUnsafeEnchantment(Enchantment.DEPTH_STRIDER, 4);
		this.addUnsafeEnchantment(Enchantment.DURABILITY, 7);
		this.addUnsafeEnchantment(Enchantment.PROTECTION_FALL, 5);
		this.addUnsafeEnchantment(Enchantment.SOUL_SPEED, 3);
		this.addUnsafeEnchantment(Enchantment.MENDING, 1);
		this.addDefenseAttributes(3.0, 5.0, 0.20);
		this.addSpeedAttribute(0.30);
		this.setColor(Color.fromRGB(91, 70, 63));
	}
}
