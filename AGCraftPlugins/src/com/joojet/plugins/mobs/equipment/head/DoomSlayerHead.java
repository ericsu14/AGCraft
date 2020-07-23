package com.joojet.plugins.mobs.equipment.head;

import org.bukkit.ChatColor;
import org.bukkit.enchantments.Enchantment;

import com.joojet.plugins.mobs.enums.PlayerHead;
import com.joojet.plugins.mobs.interfaces.Equipment;

public class DoomSlayerHead extends Equipment
{
	public DoomSlayerHead (ChatColor color)
	{
		super (PlayerHead.DOOM_SLAYER, color);
		this.setDisplayName("The Head of The Doom Slayer");
		this.addLoreToItemMeta("Tempered by the fires of Hell, his iron will remained steadfast through the passage that preys upon the weak....");
		this.addDefenseAttributes(3.0, 5.0, 0.20);
		this.addHealthAttributes(10.0);
		this.addAttackAttributes(2.0, 0.25);
		this.addUnsafeEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 4);
		this.addUnsafeEnchantment(Enchantment.WATER_WORKER, 1);
		this.addUnsafeEnchantment(Enchantment.OXYGEN, 3);
		this.addUnsafeEnchantment(Enchantment.BINDING_CURSE, 1);
	}
}
