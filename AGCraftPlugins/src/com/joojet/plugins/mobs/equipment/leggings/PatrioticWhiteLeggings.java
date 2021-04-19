package com.joojet.plugins.mobs.equipment.leggings;

import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.inventory.EquipmentSlot;

import com.joojet.plugins.mobs.enums.EquipmentType;
import com.joojet.plugins.mobs.equipment.LeatherEquipment;

public class PatrioticWhiteLeggings extends LeatherEquipment
{
	public PatrioticWhiteLeggings ()
	{
		super (EquipmentType.PATRIOTIC_WHITE_LEGGINGS, Material.LEATHER_LEGGINGS, EquipmentSlot.LEGS, ChatColor.WHITE);
		this.setColor(Color.WHITE);
		this.setDisplayName("Patriotic White Pants");
		this.addLoreToItemMeta("White as snow!");
		this.addDefenseAttributes(6.0, 2.0, 0.10);
	}
}
