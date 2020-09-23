package com.joojet.plugins.mobs.equipment.offhand;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.EquipmentSlot;

import com.joojet.plugins.mobs.enums.EquipmentType;
import com.joojet.plugins.mobs.equipment.Equipment;


public class PropFirework extends Equipment 
{
	public PropFirework ()
	{
		super (EquipmentType.PROP_FIREWORK, Material.FIREWORK_ROCKET, EquipmentSlot.OFF_HAND, ChatColor.GRAY);
		this.setDisplayName("Prop Firework");
		this.addUnsafeEnchantment(Enchantment.VANISHING_CURSE, 1);
	}
}
