package com.joojet.plugins.mobs.equipment.offhand;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemFlag;

import com.joojet.plugins.mobs.enums.EquipmentType;
import com.joojet.plugins.mobs.equipment.Equipment;

public class TotemOfEternalFaith extends Equipment 
{
	public TotemOfEternalFaith ()
	{
		super (EquipmentType.TOTEM_OF_ETERNAL_FAITH, Material.TOTEM_OF_UNDYING,
				EquipmentSlot.OFF_HAND, ChatColor.GOLD);
		this.setDisplayName("The Totem of Eternal Faith");
		this.loreColor = ChatColor.YELLOW;
		this.wordsPerLine = 7;
		this.addLoreToItemMeta("Holding onto this totem will grant you eternal hope and faith, which will"
				+ " shield you from the everlasting trials that will be thrown against you."
				+ " However, you will be weaker in strength and power as a result.");
		this.addHealthAttributes(20.0);
		this.addDefenseAttributes(6.0, 8.0, 0.15);
		this.addAttackAttributes(-3.0, 0.0);
		this.addUnsafeEnchantment(Enchantment.DURABILITY, 1);
		this.addUnsafeEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 1);
		this.addItemFlagstoMeta(ItemFlag.HIDE_ENCHANTS);
		this.makeSoulbound();
	}
}
