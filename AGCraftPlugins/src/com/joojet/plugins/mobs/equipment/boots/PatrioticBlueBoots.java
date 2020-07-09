package com.joojet.plugins.mobs.equipment.boots;

import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.inventory.EquipmentSlot;

import com.joojet.plugins.mobs.interfaces.LeatherEquipment;


public class PatrioticBlueBoots extends LeatherEquipment
{
	public PatrioticBlueBoots ()
	{
		super (Material.LEATHER_BOOTS, EquipmentSlot.FEET, ChatColor.BLUE);
		this.setColor(Color.BLUE);
		this.setDisplayName(ChatColor.BLUE + "Patriotic Blue Boots");
		this.addLoreToItemMeta(this.americanizeText("God bless America!"));
		this.addDefenseAttributes(3.0, 1.5, 0.05);
	}
}
