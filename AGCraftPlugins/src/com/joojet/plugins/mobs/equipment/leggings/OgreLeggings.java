package com.joojet.plugins.mobs.equipment.leggings;

import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.inventory.EquipmentSlot;

import com.joojet.plugins.mobs.interfaces.LeatherEquipment;

public class OgreLeggings extends LeatherEquipment
{
	public OgreLeggings (ChatColor color)
	{
		super (Material.LEATHER_LEGGINGS, EquipmentSlot.LEGS, color);
		this.setColor(Color.fromRGB(94, 78, 65));
		this.setDisplayName("Shrek's Leather Pants");
		this.addDefenseAttributes(5.0, 2.0, 0.10);
	}
}
