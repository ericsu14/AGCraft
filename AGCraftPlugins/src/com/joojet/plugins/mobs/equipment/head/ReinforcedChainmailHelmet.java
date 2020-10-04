package com.joojet.plugins.mobs.equipment.head;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.EquipmentSlot;

import com.joojet.plugins.mobs.enums.EquipmentType;
import com.joojet.plugins.mobs.equipment.Equipment;

public class ReinforcedChainmailHelmet extends Equipment
{
	public ReinforcedChainmailHelmet (ChatColor color)
	{
		super (EquipmentType.REINFOCED_CHAINMAIL_HELMET, Material.CHAINMAIL_HELMET, EquipmentSlot.HEAD, color);
		this.setDisplayName("Reinforced Chainmail Helmet");
		this.addUnsafeEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 1);
		this.addUnsafeEnchantment(Enchantment.DURABILITY, 1);
		this.addDefenseAttributes(2.0, 1.5, 0.0);
		this.addLoreToItemMeta("Reinforced with steel to have better resistance towards high damaging attacks.");
	}
}
