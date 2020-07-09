package com.joojet.plugins.mobs.equipment.chest;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.EquipmentSlot;

import com.joojet.plugins.mobs.interfaces.Equipment;

public class ReinforcedDiamondChestplate extends Equipment
{
	public ReinforcedDiamondChestplate (ChatColor color)
	{
		super (Material.DIAMOND_CHESTPLATE, EquipmentSlot.CHEST, color);
		this.addUnsafeEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 2);
		this.addUnsafeEnchantment(Enchantment.THORNS, 2);
		this.addUnsafeEnchantment(Enchantment.DURABILITY, 1);
		this.setDisplayName("Enhanced Diamond Chestplate");
		this.addLoreToItemMeta("Forged from a higher-grade cut of Diamond, this chestplate offers improved resistance towards high damaging attacks.");
		this.addDefenseAttributes(8.0, 4.0, 0.0);
	}
}
