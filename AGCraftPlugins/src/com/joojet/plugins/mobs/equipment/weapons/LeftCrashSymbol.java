package com.joojet.plugins.mobs.equipment.weapons;

import org.bukkit.ChatColor;
import org.bukkit.DyeColor;
import org.bukkit.block.banner.Pattern;
import org.bukkit.block.banner.PatternType;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.EquipmentSlot;

import com.joojet.plugins.mobs.equipment.ShieldEquipment;

public class LeftCrashSymbol extends ShieldEquipment
{
	public LeftCrashSymbol (ChatColor color)
	{
		super (DyeColor.YELLOW, EquipmentSlot.HAND, color);
		this.loreColor = ChatColor.GOLD;
		this.wordsPerLine = 5;
		this.setDisplayName("Left Crash Symbol");
		this.addLoreToItemMeta("Bash your enemies with this crash symbol! Not intended to be used as a shield, but more as a weapon!");
		
		this.addPatterns(new Pattern (DyeColor.BLACK, PatternType.FLOWER),
				new Pattern (DyeColor.YELLOW, PatternType.STRIPE_BOTTOM),
				new Pattern (DyeColor.BLACK, PatternType.STRIPE_DOWNLEFT),
				new Pattern (DyeColor.YELLOW, PatternType.STRIPE_TOP),
				new Pattern (DyeColor.YELLOW, PatternType.CURLY_BORDER),
				new Pattern (DyeColor.YELLOW, PatternType.STRIPE_LEFT),
				new Pattern (DyeColor.YELLOW, PatternType.STRIPE_RIGHT));
		
		this.addAttackAttributes(7.0, 0.0);
		this.addUnsafeEnchantment(Enchantment.DAMAGE_ALL, 5);
		this.addUnsafeEnchantment(Enchantment.MENDING, 1);
		this.addUnsafeEnchantment(Enchantment.DURABILITY, 3);
		this.addUnsafeEnchantment(Enchantment.SWEEPING_EDGE, 4);
	}
}
