package com.joojet.plugins.mobs.equipment.head;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.EquipmentSlot;

import com.joojet.plugins.mobs.equipment.Equipment;

public class FrostyFace extends Equipment 
{
	public FrostyFace (ChatColor color)
	{
		super (Material.CARVED_PUMPKIN, EquipmentSlot.HEAD, color);
		this.addUnsafeEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 4);
		this.addUnsafeEnchantment(Enchantment.THORNS, 5);
		this.addUnsafeEnchantment(Enchantment.VANISHING_CURSE, 1);
		this.setDisplayName("Frosty's Face");
		this.addDefenseAttributes(10.0, 4.0, 0.2);
	}
}
