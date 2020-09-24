package com.joojet.plugins.mobs.equipment.head;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.EquipmentSlot;

import com.joojet.plugins.mobs.enums.EquipmentType;
import com.joojet.plugins.mobs.equipment.Equipment;

public class ReinforcedDiamondHelmet extends Equipment 
{
	public ReinforcedDiamondHelmet (ChatColor color)
	{
		super (EquipmentType.REINFORCED_DIAMOND_HELMET, Material.DIAMOND_HELMET, EquipmentSlot.HEAD, color);
		this.loreColor = ChatColor.GOLD;
		this.wordsPerLine = 6;
		this.setDisplayName("Reinforced Diamond Helmet");
		this.addLoreToItemMeta("Forged from a higher-grade cut of Diamond, this Helmet offers improved resistance towards high damaging attacks.");
		this.addDefenseAttributes(3.0, 2.5, 0.10);
		this.addUnsafeEnchantment(Enchantment.PROTECTION_PROJECTILE, 4);
		this.addUnsafeEnchantment(Enchantment.DURABILITY, 2);
	}
}
