package com.joojet.plugins.mobs.equipment.head;

import org.bukkit.ChatColor;
import org.bukkit.enchantments.Enchantment;

import com.joojet.plugins.mobs.enums.PlayerHead;
import com.joojet.plugins.mobs.interfaces.Equipment;

public class ShrekHat extends Equipment
{
	public ShrekHat (ChatColor color)
	{
		super (PlayerHead.SHREK, color);
		this.setDisplayName("Shrek");
		this.addDefenseAttributes(2.0, 2.0, 0.10);
		this.addUnsafeEnchantment(Enchantment.PROTECTION_PROJECTILE, 3);
	}
}
