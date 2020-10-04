package com.joojet.plugins.mobs.equipment.head;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.EquipmentSlot;

import com.joojet.plugins.mobs.enums.EquipmentType;
import com.joojet.plugins.mobs.equipment.Equipment;

public class ReinforcedIronHelmet extends Equipment
{
	public ReinforcedIronHelmet (ChatColor color)
	{
		super (EquipmentType.REINFORCED_IRON_HELMET, Material.IRON_HELMET, EquipmentSlot.HEAD, color);
		this.setDisplayName("Reinforced Iron Helmet");
		this.addLoreToItemMeta("Reinforced with titanium to have better resistance towards high damaging attacks.");
		this.addUnsafeEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 1);
		this.addUnsafeEnchantment(Enchantment.DURABILITY, 2);
		this.addDefenseAttributes(2.0, 2.0, 0.0);
	}
}
