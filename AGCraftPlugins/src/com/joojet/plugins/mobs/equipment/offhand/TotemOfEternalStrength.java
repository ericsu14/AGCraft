package com.joojet.plugins.mobs.equipment.offhand;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemFlag;

import com.joojet.plugins.mobs.enums.EquipmentTypes;
import com.joojet.plugins.mobs.equipment.Equipment;

public class TotemOfEternalStrength extends Equipment 
{
	public TotemOfEternalStrength ()
	{
		super (EquipmentTypes.TOTEM_OF_ETERNAL_STRENGTH, Material.TOTEM_OF_UNDYING, EquipmentSlot.OFF_HAND, 
				ChatColor.RED);
		this.setDisplayName("Totem of Eternal Strength");
		this.addAttackAttributes(8.0, 0.1);
		this.addDefenseAttributes(-4.0, -6.0, 0.0);
		this.wordsPerLine = 7;
		this.loreColor = ChatColor.DARK_RED;
		this.addLoreToItemMeta("Hold onto this totem to have the blessings of eternal strength."
				+ " However, holding such great power also makes you less resilient towards attacks.");
		this.addUnsafeEnchantment(Enchantment.DURABILITY, 1);
		this.addItemFlags(ItemFlag.HIDE_ENCHANTS);
	}
}
