package com.joojet.plugins.mobs.equipment.chest;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.EquipmentSlot;

import com.joojet.plugins.mobs.enums.EquipmentTypes;
import com.joojet.plugins.mobs.equipment.Equipment;

public class ReinforcedChainmailChestplate extends Equipment
{
	public ReinforcedChainmailChestplate (ChatColor color)
	{	
		super (EquipmentTypes.REINFORCED_CHAINMAIL_CHEST, Material.CHAINMAIL_CHESTPLATE, EquipmentSlot.CHEST, color);
		this.setDisplayName("Reinforced Chainmail Helmet");
		this.addUnsafeEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 2);
		this.addUnsafeEnchantment(Enchantment.DURABILITY, 1);
		this.addDefenseAttributes(5.0, 1.0, 0.0);
		this.addLoreToItemMeta("Reinforced with steel to have better resistance towards high damaging attacks.");
	}
}
