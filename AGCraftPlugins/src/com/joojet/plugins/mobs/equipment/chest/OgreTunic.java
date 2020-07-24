package com.joojet.plugins.mobs.equipment.chest;

import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.inventory.EquipmentSlot;

import com.joojet.plugins.mobs.equipment.LeatherEquipment;

public class OgreTunic extends LeatherEquipment
{
	public OgreTunic (ChatColor color)
	{
		super (Material.LEATHER_CHESTPLATE, EquipmentSlot.CHEST, color);
		this.addDefenseAttributes(6.0, 3.0, 0.10);
		this.setColor(Color.fromRGB(237, 222, 172));
		this.setDisplayName("Shrek's Leather Tunic");
	}
}
