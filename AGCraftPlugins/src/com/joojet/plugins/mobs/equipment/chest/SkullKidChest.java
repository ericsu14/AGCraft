package com.joojet.plugins.mobs.equipment.chest;

import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.inventory.EquipmentSlot;

import com.joojet.plugins.mobs.enums.EquipmentType;
import com.joojet.plugins.mobs.equipment.LeatherEquipment;

public class SkullKidChest extends LeatherEquipment
{
	public SkullKidChest (ChatColor color)
	{
		super (EquipmentType.SKULL_KID_CHEST, Material.LEATHER_CHESTPLATE, EquipmentSlot.CHEST, color);
		this.setDisplayName("Skull Kid's Chestplate");
		this.addLoreToItemMeta("You’ve met with a terrible fate, haven’t you?");
		this.setColor(Color.fromRGB(177, 163, 38));
		this.addDefenseAttributes(8.0, 6.0, 0.20);
		this.makeUnbreakable();
		this.makeSoulbound();
	}
}
