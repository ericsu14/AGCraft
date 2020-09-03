package com.joojet.plugins.mobs.equipment.boots;

import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.inventory.EquipmentSlot;

import com.joojet.plugins.mobs.enums.EquipmentTypes;
import com.joojet.plugins.mobs.equipment.LeatherEquipment;

public class BruinFootballBoots extends LeatherEquipment
{
	public BruinFootballBoots (ChatColor color)
	{
		super (EquipmentTypes.BRUIN_FOOTBALL_BOOTS, Material.LEATHER_BOOTS, EquipmentSlot.FEET, color);
		this.setDisplayName("UCLA Football Boots");
		this.setColor(Color.fromRGB(39, 116, 174));
		this.loreColor = ChatColor.GOLD;
		this.wordsPerLine = 6;
		this.addLoreToItemMeta("Gotta hit the ground runnin'");
		this.addDefenseAttributes(2.0, 1.0, 0.5);
		this.addSpeedAttribute(0.15);
		this.addAttackAttributes(1.0, 0.0);
	}
}
