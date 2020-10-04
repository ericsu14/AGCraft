package com.joojet.plugins.mobs.equipment.leggings;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.EquipmentSlot;

import com.joojet.plugins.mobs.enums.EquipmentType;
import com.joojet.plugins.mobs.equipment.Equipment;

public class ReinforcedChainmailLeggings extends Equipment
{
	public ReinforcedChainmailLeggings (ChatColor color)
	{
		super (EquipmentType.REINFORCED_CHAINMAIL_LEGGINGS, Material.CHAINMAIL_LEGGINGS, EquipmentSlot.LEGS, color);
		this.setDisplayName("Reinforced Chainmail Helmet");
		this.addUnsafeEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 2);
		this.addUnsafeEnchantment(Enchantment.DURABILITY, 1);
		this.addDefenseAttributes(4.0, 1.5, 0.0);
		this.addLoreToItemMeta("Reinforced with steel to have better resistance towards high damaging attacks.");
	}
}
