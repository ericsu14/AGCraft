package com.joojet.plugins.mobs.equipment.head;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.EquipmentSlot;

import com.joojet.plugins.mobs.enums.EquipmentTypes;
import com.joojet.plugins.mobs.equipment.Equipment;

public class ScruffyFace extends Equipment
{
	public ScruffyFace (ChatColor color)
	{
		super (EquipmentTypes.SCRUFFY_FACE, Material.CARVED_PUMPKIN, EquipmentSlot.HEAD, color);
		this.addUnsafeEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 4);
		this.addUnsafeEnchantment(Enchantment.THORNS, 7);
		this.addUnsafeEnchantment(Enchantment.VANISHING_CURSE, 1);
		this.setDisplayName("Scruffy's Face");
		this.addDefenseAttributes(16.0, 8.0, 0.5);
	}
}
