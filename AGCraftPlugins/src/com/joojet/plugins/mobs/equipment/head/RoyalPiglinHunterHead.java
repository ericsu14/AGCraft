package com.joojet.plugins.mobs.equipment.head;

import org.bukkit.ChatColor;
import org.bukkit.enchantments.Enchantment;

import com.joojet.plugins.mobs.enums.EquipmentType;
import com.joojet.plugins.mobs.enums.PlayerHead;
import com.joojet.plugins.mobs.equipment.Equipment;

public class RoyalPiglinHunterHead extends Equipment 
{
	public RoyalPiglinHunterHead (ChatColor color)
	{
		super (EquipmentType.ROYAL_PIGLIN_HUNTER_HEAD, PlayerHead.PIGLIN_CAPTAIN, color);
		this.setDisplayName("Royal Piglin Hunter Head");
		this.addDefenseAttributes(3.0, 3.0, 0.30);
		this.addUnsafeEnchantment(Enchantment.PROTECTION_PROJECTILE, 4);
		this.addUnsafeEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 3);
		this.addSpeedAttribute(0.05);
		this.addHealthAttributes(10.0);
	}
}
