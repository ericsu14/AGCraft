package com.joojet.plugins.mobs.equipment.leggings;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.EquipmentSlot;

import com.joojet.plugins.mobs.enums.EquipmentTypes;
import com.joojet.plugins.mobs.equipment.Equipment;

public class ReinforcedIronLeggings extends Equipment 
{
	public ReinforcedIronLeggings(ChatColor color)
	{
		super (EquipmentTypes.REINFORCED_IRON_LEGGINGS, Material.IRON_LEGGINGS, EquipmentSlot.LEGS, color);
		this.addUnsafeEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 1);
		this.addUnsafeEnchantment(Enchantment.DURABILITY, 2);
		this.setDisplayName("Reinforced Iron Leggings");
		this.addLoreToItemMeta("Reinforced with titanium to have better resistance towards high damaging attacks.");
		this.addDefenseAttributes(5.0, 2.0, 0.0);
	}
}
