package com.joojet.plugins.mobs.equipment.head;

import org.bukkit.ChatColor;
import org.bukkit.enchantments.Enchantment;

import com.joojet.plugins.mobs.enums.EquipmentTypes;
import com.joojet.plugins.mobs.enums.PlayerHead;
import com.joojet.plugins.mobs.equipment.Equipment;

public class ZombiePigmenHead extends Equipment
{
	public ZombiePigmenHead (ChatColor color)
	{
		super (EquipmentTypes.ZOMBIE_PIGMAN_HEAD, PlayerHead.ZOMBIE_PIGMEN, color);
		this.addDefenseAttributes(2.0, 2.0, 0.10);
		this.addUnsafeEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 3);
		this.setDisplayName("Veteran Zombie Pigmen Head");
		this.addLoreToItemMeta("A head from a Zombie Pigmen that has gone through many years of torment...");
	}
}
