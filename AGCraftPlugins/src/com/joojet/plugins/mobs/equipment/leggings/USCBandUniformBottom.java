package com.joojet.plugins.mobs.equipment.leggings;

import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.inventory.EquipmentSlot;

import com.joojet.plugins.mobs.enums.EquipmentType;
import com.joojet.plugins.mobs.equipment.LeatherEquipment;
import com.joojet.plugins.mobs.monsters.factions.USCFaction;

public class USCBandUniformBottom extends LeatherEquipment 
{
	public USCBandUniformBottom (ChatColor color)
	{
		super (EquipmentType.USC_BAND_UNIFORM_BOTTOM, Material.LEATHER_LEGGINGS, EquipmentSlot.LEGS, color);
		this.setDisplayName(USCFaction.generateUSCDisplayName("Band Uniform"));
		this.setColor(Color.fromRGB(153, 27, 30));
		this.addDefenseAttributes(5.0, 1.5, 0.07);
		this.wordsPerLine = 6;
		this.loreColor = ChatColor.GOLD;
		this.addLoreToItemMeta("March your way to victory with these sharp-looking pants!");
		this.makeUnbreakable();
	}
}
