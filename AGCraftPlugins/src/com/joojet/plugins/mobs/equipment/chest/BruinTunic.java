package com.joojet.plugins.mobs.equipment.chest;

import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.inventory.EquipmentSlot;

import com.joojet.plugins.mobs.enums.EquipmentType;
import com.joojet.plugins.mobs.equipment.LeatherEquipment;
import com.joojet.plugins.mobs.monsters.factions.UCLAFaction;


public class BruinTunic extends LeatherEquipment
{
	public BruinTunic (ChatColor color)
	{
		super (EquipmentType.BRUIN_TUNIC, Material.LEATHER_CHESTPLATE, EquipmentSlot.CHEST, color);
		this.wordsPerLine = 6;
		this.loreColor = ChatColor.GOLD;
		this.setDisplayName(UCLAFaction.generateUCLADisplayName("Football Jersey"));
		this.addLoreToItemMeta("Show off your school spirit in the most wrong way possible!");
		this.setColor(Color.fromRGB(39, 116, 174));
		this.addDefenseAttributes(5.0, 2.0, 0.10);
	}
}
