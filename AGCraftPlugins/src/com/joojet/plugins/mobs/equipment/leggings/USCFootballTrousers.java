package com.joojet.plugins.mobs.equipment.leggings;

import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.inventory.EquipmentSlot;

import com.joojet.plugins.mobs.enums.EquipmentTypes;
import com.joojet.plugins.mobs.equipment.LeatherEquipment;

public class USCFootballTrousers extends LeatherEquipment
{
	public USCFootballTrousers (ChatColor color)
	{
		super (EquipmentTypes.USC_FOOTBALL_TROUSERS, Material.LEATHER_LEGGINGS, EquipmentSlot.LEGS, color);
		this.setColor(Color.fromRGB(255, 198, 81));
		this.setDisplayName(ChatColor.RED + "U" + ChatColor.GOLD + "S" + ChatColor.RED + "C" 
		+ ChatColor.GOLD + " Bulky" + ChatColor.RED + " Pants");
		this.addDefenseAttributes(5.0, 1.5, 0.07);
		this.wordsPerLine = 6;
		this.loreColor = ChatColor.GOLD;
		this.addLoreToItemMeta("Show off your school spirit with these flashy yet effective pants!");
		this.makeUnbreakable();
	}
}
