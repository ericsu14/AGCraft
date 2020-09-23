package com.joojet.plugins.mobs.equipment.boots;

import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.EquipmentSlot;

import com.joojet.plugins.mobs.enums.EquipmentType;
import com.joojet.plugins.mobs.equipment.LeatherEquipment;

public class OgreBoots extends LeatherEquipment
{
	public OgreBoots (ChatColor color)
	{
		super (EquipmentType.OGRE_BOOTS, Material.LEATHER_BOOTS, EquipmentSlot.FEET, color);
		this.setColor(Color.fromRGB(94, 78, 65));
		this.setDisplayName("Shrek's Leather Boots");
		this.addDefenseAttributes(3.0, 1.0, 0.05);
		this.addSpeedAttribute(0.10);
		this.addUnsafeEnchantment(Enchantment.DEPTH_STRIDER, 3);
	}
}
