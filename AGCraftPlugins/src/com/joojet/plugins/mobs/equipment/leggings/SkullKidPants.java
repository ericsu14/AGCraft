package com.joojet.plugins.mobs.equipment.leggings;

import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.inventory.EquipmentSlot;

import com.joojet.plugins.mobs.enums.EquipmentTypes;
import com.joojet.plugins.mobs.equipment.LeatherEquipment;

public class SkullKidPants extends LeatherEquipment
{
	public SkullKidPants (ChatColor color)
	{
		super (EquipmentTypes.SKULL_KID_PANTS, Material.LEATHER_LEGGINGS, EquipmentSlot.LEGS, color);
		this.setDisplayName("Skull Kid's Pants");
		this.addLoreToItemMeta("You’ve met with a terrible fate, haven’t you?");
		this.addDefenseAttributes(6.0, 5.0, 0.10);
		this.setColor(Color.fromRGB(173, 61, 24));
		this.makeUnbreakable();
		this.makeSoulbound();
	}
}
