package com.joojet.plugins.mobs.equipment.head;

import org.bukkit.ChatColor;
import org.bukkit.enchantments.Enchantment;

import com.joojet.plugins.mobs.enums.PlayerHead;
import com.joojet.plugins.mobs.equipment.Equipment;

public class USCTrojan extends Equipment
{
	public USCTrojan (ChatColor color)
	{
		super (PlayerHead.USC_TROJAN, color);
		this.setDisplayName(ChatColor.RED + "U" + ChatColor.GOLD + "S" + ChatColor.RED + "C" 
		+ ChatColor.GOLD + " Football" + ChatColor.RED + " Helmet");
		this.addDefenseAttributes(3.0, 2.5, 0.15);
		this.addUnsafeEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 4);
		this.addUnsafeEnchantment(Enchantment.OXYGEN, 3);
		this.addUnsafeEnchantment(Enchantment.WATER_WORKER, 1);
		this.loreColor = ChatColor.GOLD;
		this.wordsPerLine = 6;
		this.addLoreToItemMeta("Show off your school spirit and fight on!!");
	}
}
