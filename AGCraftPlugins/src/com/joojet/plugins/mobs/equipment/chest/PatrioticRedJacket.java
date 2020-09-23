package com.joojet.plugins.mobs.equipment.chest;

import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.inventory.EquipmentSlot;

import com.joojet.plugins.mobs.enums.EquipmentType;
import com.joojet.plugins.mobs.equipment.LeatherEquipment;

public class PatrioticRedJacket extends LeatherEquipment
{
	public PatrioticRedJacket ()
	{
		super (EquipmentType.PATRIOTIC_RED_JACKET, Material.LEATHER_CHESTPLATE, EquipmentSlot.CHEST, ChatColor.RED);
		this.setColor(Color.RED);
		this.setDisplayName("Patriotic Red Jacket");
		this.addLoreToItemMeta("jooj loves to wear this!");
		this.addDefenseAttributes(8.0, 3.0, 0.15);
	}
}
