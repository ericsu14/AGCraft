package com.joojet.plugins.mobs.equipment.leggings;

import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.inventory.EquipmentSlot;

import com.joojet.plugins.mobs.equipment.LeatherEquipment;

public class USCBandUniformBottom extends LeatherEquipment 
{
	public USCBandUniformBottom (ChatColor color)
	{
		super (Material.LEATHER_LEGGINGS, EquipmentSlot.LEGS, color);
		this.setDisplayName(ChatColor.RED + "U" + ChatColor.GOLD + "S" + ChatColor.RED + "C" 
		+ ChatColor.GOLD + " Band" + ChatColor.RED + " Uniform");
		this.setColor(Color.fromRGB(153, 27, 30));
		this.addDefenseAttributes(5.0, 1.5, 0.07);
		this.wordsPerLine = 6;
		this.loreColor = ChatColor.GOLD;
		this.addLoreToItemMeta("Show off your school spirit with these flashy yet effective pants!");
		this.makeUnbreakable();
	}
}
