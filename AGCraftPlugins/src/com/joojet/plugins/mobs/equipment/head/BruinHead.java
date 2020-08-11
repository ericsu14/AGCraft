package com.joojet.plugins.mobs.equipment.head;

import org.bukkit.ChatColor;
import org.bukkit.enchantments.Enchantment;

import com.joojet.plugins.mobs.enums.PlayerHead;
import com.joojet.plugins.mobs.equipment.Equipment;

public class BruinHead extends Equipment
{
	public BruinHead ()
	{
		super (PlayerHead.UCLA_BRUIN, ChatColor.AQUA);
		this.wordsPerLine = 6;
		this.loreColor = ChatColor.GOLD;
		this.setDisplayName("The Helmet of the UCLA Bruin");
		this.addLoreToItemMeta("This is one school you definitely don't want to see winnin'");
		this.addUnsafeEnchantment(Enchantment.PROTECTION_PROJECTILE, 3);
		this.addUnsafeEnchantment(Enchantment.BINDING_CURSE, 1);
		this.addDefenseAttributes(3.0, 2.0, 0.15);
		this.addAttackAttributes(1.0, 0.0);
	}
}
