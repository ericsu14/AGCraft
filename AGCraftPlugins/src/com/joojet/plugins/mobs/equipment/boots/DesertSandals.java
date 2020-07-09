package com.joojet.plugins.mobs.equipment.boots;

import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.inventory.EquipmentSlot;

import com.joojet.plugins.mobs.interfaces.LeatherEquipment;

public class DesertSandals extends LeatherEquipment
{
	public DesertSandals (ChatColor color)
	{
		super (Material.LEATHER_BOOTS, EquipmentSlot.FEET, color);
		this.addSpeedAttribute(0.10);
		this.addDefenseAttributes(6.0, 1.0, 0.1);
		this.setDisplayName("Desert Sandals");
		this.addLoreToItemMeta("Passed down by generations, "
				+ "these sandals were used to walk across this scorched earth safely.");
		this.setColor(Color.fromRGB(237, 201, 175));
	}
}
