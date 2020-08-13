package com.joojet.plugins.mobs.equipment.leggings;

import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.inventory.EquipmentSlot;

import com.joojet.plugins.mobs.equipment.LeatherEquipment;

public class BruinLeggings extends LeatherEquipment
{
	public BruinLeggings (ChatColor color)
	{
		super (Material.LEATHER_LEGGINGS, EquipmentSlot.LEGS, color);
		this.wordsPerLine = 6;
		this.loreColor = ChatColor.GOLD;
		this.setDisplayName("UCLA Football Leggings");
		this.setColor(Color.fromRGB(255, 209, 0));
		this.addLoreToItemMeta("These people never skip leg day...");
		this.addDefenseAttributes(5.0, 1.0, 0.10);
		this.addSpeedAttribute(0.05);
	}
}
