package com.joojet.plugins.mobs.equipment.chest;

import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.inventory.EquipmentSlot;

import com.joojet.plugins.mobs.equipment.LeatherEquipment;


public class BruinTunic extends LeatherEquipment
{
	public BruinTunic (ChatColor color)
	{
		super (Material.LEATHER_HELMET, EquipmentSlot.CHEST, color);
		this.wordsPerLine = 6;
		this.loreColor = ChatColor.GOLD;
		this.setDisplayName("UCLA Football Chestplate");
		this.addLoreToItemMeta("Show off your school spirit in the most wrong way possible!");
		this.setColor(Color.fromRGB(39, 116, 174));
		this.addDefenseAttributes(5.0, 2.0, 0.10);
		this.addAttackAttributes(2.0, 0.5);
	}
}
