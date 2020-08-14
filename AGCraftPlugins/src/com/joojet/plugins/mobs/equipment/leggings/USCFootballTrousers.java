package com.joojet.plugins.mobs.equipment.leggings;

import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.inventory.EquipmentSlot;

import com.joojet.plugins.mobs.equipment.LeatherEquipment;

public class USCFootballTrousers extends LeatherEquipment
{
	public USCFootballTrousers (ChatColor color)
	{
		super (Material.LEATHER_LEGGINGS, EquipmentSlot.LEGS, color);
		this.setColor(Color.fromRGB(255, 198, 81));
		this.setDisplayName(ChatColor.RED + "U" + ChatColor.GOLD + "S" + ChatColor.RED + "C" 
		+ ChatColor.GOLD + " Football" + ChatColor.RED + " Pants");
		this.addDefenseAttributes(5.0, 1.5, 0.07);
		this.wordsPerLine = 6;
		this.loreColor = ChatColor.GOLD;
		this.addLoreToItemMeta("Show off your school spirit with these flashy yet effective pants!");
		this.makeUnbreakable();
	}
}
