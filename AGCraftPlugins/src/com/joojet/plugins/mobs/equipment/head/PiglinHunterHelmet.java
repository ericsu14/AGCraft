package com.joojet.plugins.mobs.equipment.head;

import org.bukkit.ChatColor;
import org.bukkit.enchantments.Enchantment;

import com.joojet.plugins.mobs.enums.EquipmentTypes;
import com.joojet.plugins.mobs.enums.PlayerHead;
import com.joojet.plugins.mobs.equipment.Equipment;

public class PiglinHunterHelmet extends Equipment
{
	public PiglinHunterHelmet (ChatColor color)
	{
		super (EquipmentTypes.PIGLIN_HUNTER_HEAD, PlayerHead.PIGLIN_HUNTER, color);
		this.setDisplayName("Piglin Hunter's Head");
		this.addUnsafeEnchantment(Enchantment.PROTECTION_PROJECTILE, 3);
		this.addDefenseAttributes(2.0, 2.0, 0.15);
	}
}
