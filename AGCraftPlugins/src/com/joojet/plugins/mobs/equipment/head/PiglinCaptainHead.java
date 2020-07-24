package com.joojet.plugins.mobs.equipment.head;

import org.bukkit.ChatColor;
import org.bukkit.enchantments.Enchantment;

import com.joojet.plugins.mobs.enums.PlayerHead;
import com.joojet.plugins.mobs.interfaces.Equipment;

public class PiglinCaptainHead extends Equipment
{
	public PiglinCaptainHead (ChatColor color)
	{
		super (PlayerHead.PIGLIN_CAPTAIN, color);
		this.setDisplayName("Head of the Piglin Captain");
		this.addLoreToItemMeta("The head of a true leader in this godforsaken underworld.");
		this.addUnsafeEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 3);
		this.addDefenseAttributes(3.0, 3.5, 0.10);
	}
}
