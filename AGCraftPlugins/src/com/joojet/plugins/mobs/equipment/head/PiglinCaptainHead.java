package com.joojet.plugins.mobs.equipment.head;

import org.bukkit.ChatColor;
import org.bukkit.enchantments.Enchantment;

import com.joojet.plugins.mobs.enums.EquipmentType;
import com.joojet.plugins.mobs.enums.PlayerHead;
import com.joojet.plugins.mobs.equipment.Equipment;

public class PiglinCaptainHead extends Equipment
{
	public PiglinCaptainHead (ChatColor color)
	{
		super (EquipmentType.PIGLIN_CAPTAIN_HEAD, PlayerHead.PIGLIN_CAPTAIN, color);
		this.setDisplayName("Head of the Piglin Captain");
		this.addLoreToItemMeta("The head of a true leader in this godforsaken underworld.");
		this.addUnsafeEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 4);
		this.addUnsafeEnchantment(Enchantment.PROTECTION_PROJECTILE, 2);
		this.addDefenseAttributes(3.0, 3.5, 0.10);
	}
}
