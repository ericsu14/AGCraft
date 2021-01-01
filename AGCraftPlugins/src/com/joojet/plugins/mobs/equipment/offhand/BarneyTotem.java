package com.joojet.plugins.mobs.equipment.offhand;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.EquipmentSlot;

import com.joojet.plugins.mobs.enums.EquipmentType;
import com.joojet.plugins.mobs.equipment.Equipment;

public class BarneyTotem extends Equipment
{
	public BarneyTotem (ChatColor color)
	{
		super (EquipmentType.BARNEY_TOTEM, Material.TOTEM_OF_UNDYING, EquipmentSlot.OFF_HAND, color);
		this.addAttackAttributes(7.0, 0.0);
		this.addSpeedAttribute(0.15);
		this.setDisplayName("Barney's Pal");
		this.loreColor = ChatColor.GOLD;
		this.addLoreToItemMeta("Barney weeps in joy for your tortured soul.");
	}
}
