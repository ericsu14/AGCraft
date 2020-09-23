package com.joojet.plugins.mobs.equipment.chest;

import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.inventory.EquipmentSlot;

import com.joojet.plugins.mobs.enums.EquipmentType;
import com.joojet.plugins.mobs.equipment.LeatherEquipment;
import com.joojet.plugins.mobs.monsters.factions.USCFaction;

public class USCBandUniformTop extends LeatherEquipment
{
	public USCBandUniformTop (ChatColor color)
	{
		super (EquipmentType.USC_BAND_UNIFORM, Material.LEATHER_CHESTPLATE, EquipmentSlot.CHEST, color);
		this.setDisplayName(USCFaction.generateUSCDisplayName("Band Uniform"));
		this.setColor(Color.fromRGB(153, 27, 30));
		this.addDefenseAttributes(6.0, 3.0, 0.15);
		this.addAttackAttributes(2.0, 0.10);
		this.wordsPerLine = 6;
		this.loreColor = ChatColor.GOLD;
		this.addLoreToItemMeta("March your way to victory!");
		this.makeUnbreakable();
	}
}
