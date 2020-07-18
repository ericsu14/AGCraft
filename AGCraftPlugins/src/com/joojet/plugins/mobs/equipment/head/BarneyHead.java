package com.joojet.plugins.mobs.equipment.head;

import org.bukkit.ChatColor;
import org.bukkit.enchantments.Enchantment;

import com.joojet.plugins.mobs.enums.PlayerHead;
import com.joojet.plugins.mobs.interfaces.Equipment;

public class BarneyHead extends Equipment
{
	public BarneyHead (ChatColor color)
	{
		super (PlayerHead.BARNEY, color);
		this.setDisplayName("Barney the Dinosaur");
		this.addLoreToItemMeta("now cha cha real smooth...");
		this.addHealthAttributes(8.0);
		this.addUnsafeEnchantment(Enchantment.PROTECTION_EXPLOSIONS, 4);
		this.addUnsafeEnchantment(Enchantment.BINDING_CURSE, 1);
		this.addDefenseAttributes(2.0, 2.0, 0.10);
	}
}
