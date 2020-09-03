package com.joojet.plugins.mobs.equipment.head;

import org.bukkit.ChatColor;
import org.bukkit.enchantments.Enchantment;

import com.joojet.plugins.mobs.enums.EquipmentTypes;
import com.joojet.plugins.mobs.enums.PlayerHead;
import com.joojet.plugins.mobs.equipment.Equipment;

public class USCBandHead extends Equipment
{
	public USCBandHead (ChatColor color)
	{
		super (EquipmentTypes.USC_BAND_HEAD, PlayerHead.USC_BAND, color);
		this.setDisplayName(ChatColor.RED + "U" + ChatColor.GOLD + "S" + ChatColor.RED + "C" 
		+ ChatColor.GOLD + " Marching" + ChatColor.RED + " Band");
		this.addDefenseAttributes(3.0, 2.5, 0.15);
		this.addUnsafeEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 5);
		this.addUnsafeEnchantment(Enchantment.OXYGEN, 3);
		this.addUnsafeEnchantment(Enchantment.WATER_WORKER, 1);
		this.loreColor = ChatColor.GOLD;
		this.wordsPerLine = 6;
		this.addLoreToItemMeta("March your way to victory and fight on!");
	}
}
