package com.joojet.plugins.mobs.equipment.offhand;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemFlag;

import com.joojet.plugins.mobs.enums.EquipmentTypes;
import com.joojet.plugins.mobs.equipment.Equipment;

public class TotemOfTheEternalRushee extends Equipment {
	public TotemOfTheEternalRushee ()
	{
		super (EquipmentTypes.TOTEM_OF_THY_ETERNAL_RUSHEE, Material.TOTEM_OF_UNDYING,
				EquipmentSlot.OFF_HAND, ChatColor.GOLD);
		
		this.setDisplayName("The Totem of Thy Eternal Rushee");
		this.loreColor = ChatColor.YELLOW;
		this.wordsPerLine = 8;
		this.addLoreToItemMeta("Legend says that this contains the spirit of a legend who rushed AΓΩ multiple times"
				+ " for the sake of rushing AΓΩ. Holding onto this totem will grant you his powers.");
		this.addSpeedAttribute(0.15);
		this.addAttackAttributes(2.0, 0.0);
		this.addUnsafeEnchantment(Enchantment.DURABILITY, 1);
		this.addItemFlags(ItemFlag.HIDE_ENCHANTS);
	}
}
