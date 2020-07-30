package com.joojet.plugins.mobs.equipment.boots;

import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.EquipmentSlot;

import com.joojet.plugins.mobs.equipment.LeatherEquipment;

public class SkullKidBoots extends LeatherEquipment 
{
	public SkullKidBoots (ChatColor color)
	{
		super (Material.LEATHER_BOOTS, EquipmentSlot.FEET, color);
		this.setColor(Color.fromRGB(177, 163, 38));
		this.setDisplayName("Skull Kid's Boots");
		this.addLoreToItemMeta("You’ve met with a terrible fate, haven’t you?");
		this.addDefenseAttributes(3.0, 4.0, 0.10);
		this.addSpeedAttribute(0.25);
		this.addUnsafeEnchantment(Enchantment.DEPTH_STRIDER, 3);
		this.addUnsafeEnchantment(Enchantment.PROTECTION_FALL, 4);
		this.makeUnbreakable();
	}
}
