package com.joojet.plugins.mobs.equipment.head;

import org.bukkit.ChatColor;
import org.bukkit.enchantments.Enchantment;

import com.joojet.plugins.mobs.enums.PlayerHead;
import com.joojet.plugins.mobs.interfaces.Equipment;

public class PharaohHead extends Equipment
{
	public PharaohHead (ChatColor color)
	{
		super (PlayerHead.PHARAOH, color);
		this.addUnsafeEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 3);
		this.addDefenseAttributes(2.0, 1.0, 0.05);
		this.setDisplayName("Head of the Fallen Pharaoh");
		this.addLoreToItemMeta("Once a ruler, fallen from grace.");
	}
}
