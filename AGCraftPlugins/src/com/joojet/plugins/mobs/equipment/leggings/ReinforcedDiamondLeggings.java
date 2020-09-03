package com.joojet.plugins.mobs.equipment.leggings;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.EquipmentSlot;

import com.joojet.plugins.mobs.enums.EquipmentTypes;
import com.joojet.plugins.mobs.equipment.Equipment;

public class ReinforcedDiamondLeggings extends Equipment 
{
	public ReinforcedDiamondLeggings (ChatColor color)
	{
		super (EquipmentTypes.REINFORCED_DIAMOND_LEGGINGS, Material.DIAMOND_LEGGINGS, EquipmentSlot.LEGS, color);
		this.setDisplayName("Reinforced Diamond Leggings");
		this.addLoreToItemMeta("Forged from a higher-grade cut of Diamond, these pants offers improved resistance towards high damaging attacks.");
		this.addUnsafeEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 2);
		this.addUnsafeEnchantment(Enchantment.DURABILITY, 1);
		this.addDefenseAttributes(6.0, 2.5, 0.0);
	}
}
