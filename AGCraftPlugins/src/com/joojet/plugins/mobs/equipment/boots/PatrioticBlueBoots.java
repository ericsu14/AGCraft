package com.joojet.plugins.mobs.equipment.boots;

import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.inventory.EquipmentSlot;

import com.joojet.plugins.agcraft.enums.TextPattern;
import com.joojet.plugins.agcraft.util.StringUtil;
import com.joojet.plugins.mobs.enums.EquipmentType;
import com.joojet.plugins.mobs.equipment.LeatherEquipment;


public class PatrioticBlueBoots extends LeatherEquipment
{
	public PatrioticBlueBoots ()
	{
		super (EquipmentType.PATRIOTIC_BLUE_BOOTS, Material.LEATHER_BOOTS, EquipmentSlot.FEET, ChatColor.BLUE);
		this.setColor(Color.BLUE);
		this.setDisplayName(ChatColor.BLUE + "Patriotic Blue Boots");
		this.addLoreToItemMeta(StringUtil.alternateTextColors("God bless America!", TextPattern.CHARACTER, ChatColor.RED,
				ChatColor.WHITE, ChatColor.BLUE));
		this.addDefenseAttributes(3.0, 1.5, 0.05);
	}
}
