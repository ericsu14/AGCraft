package com.joojet.plugins.mobs.equipment.head;

import org.bukkit.ChatColor;
import org.bukkit.enchantments.Enchantment;

import com.joojet.plugins.mobs.enums.EquipmentType;
import com.joojet.plugins.mobs.enums.PlayerHead;
import com.joojet.plugins.mobs.equipment.Equipment;

public class PiglinSoldierHat extends Equipment
{
	public PiglinSoldierHat (ChatColor color)
	{
		super (EquipmentType.PIGLIN_SOLDIER_HAT, PlayerHead.PIGLIN_SOILDER, color);
		this.setDisplayName("Piglin Soldier's Head");
		this.addUnsafeEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 2);
		this.addDefenseAttributes(2.0, 2.0, 0.10);
	}

}
