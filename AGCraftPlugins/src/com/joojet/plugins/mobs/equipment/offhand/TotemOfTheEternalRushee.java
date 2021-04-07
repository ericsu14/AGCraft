package com.joojet.plugins.mobs.equipment.offhand;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemFlag;

import com.joojet.plugins.mobs.enums.EquipmentType;
import com.joojet.plugins.mobs.equipment.Equipment;

public class TotemOfTheEternalRushee extends Equipment {
	public TotemOfTheEternalRushee ()
	{
		super (EquipmentType.TOTEM_OF_THY_ETERNAL_RUSHEE, Material.TOTEM_OF_UNDYING,
				EquipmentSlot.OFF_HAND, ChatColor.GOLD);
		
		this.setDisplayName("The Totem of Thy Eternal Rushee");
		this.loreColor = ChatColor.YELLOW;
		this.wordsPerLine = 7;
		this.addLoreToItemMeta("Legend says that this contains the spirit of a legend who rushed AΓΩ multiple times"
				+ " for the sake of rushing AΓΩ. Holding onto this totem will grant you his powers.");
		this.addSpeedAttribute(0.15);
		this.addAttackAttributes(3.0, -0.15);
		this.addUnsafeEnchantment(Enchantment.SOUL_SPEED, 1);
		this.addUnsafeEnchantment(Enchantment.LOOT_BONUS_MOBS, 1);
		this.addUnsafeEnchantment(Enchantment.DURABILITY, 1);
		this.addItemFlagstoMeta(ItemFlag.HIDE_ENCHANTS);
		this.makeSoulbound();
	}
}
