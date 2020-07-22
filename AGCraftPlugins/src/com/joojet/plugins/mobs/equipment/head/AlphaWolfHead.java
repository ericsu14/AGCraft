package com.joojet.plugins.mobs.equipment.head;

import org.bukkit.ChatColor;
import org.bukkit.enchantments.Enchantment;

import com.joojet.plugins.mobs.enums.PlayerHead;
import com.joojet.plugins.mobs.interfaces.Equipment;

public class AlphaWolfHead extends Equipment
{
	public AlphaWolfHead (ChatColor color)
	{
		super (PlayerHead.WOLF, color);
		this.addDefenseAttributes(16.0, 10.0, 0.5);
		this.addAttackAttributes(8.0, 0.0);
		this.addSpeedAttribute(0.25);
		this.addHealthAttributes(10.0);
		this.setDisplayName("Alpha Wolf's Head");
		this.addUnsafeEnchantment(Enchantment.VANISHING_CURSE, 1);
		this.addUnsafeEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 3);
		this.addUnsafeEnchantment(Enchantment.PROTECTION_FALL, 4);
		this.addUnsafeEnchantment(Enchantment.THORNS, 4);
		this.addUnsafeEnchantment(Enchantment.DEPTH_STRIDER, 5);
	}
}
