package com.joojet.plugins.mobs.equipment.offhand;

import org.bukkit.ChatColor;
import org.bukkit.DyeColor;
import org.bukkit.block.banner.Pattern;
import org.bukkit.block.banner.PatternType;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.EquipmentSlot;

import com.joojet.plugins.agcraft.enums.TextPattern;
import com.joojet.plugins.agcraft.util.StringUtil;
import com.joojet.plugins.mobs.enums.EquipmentTypes;
import com.joojet.plugins.mobs.equipment.ShieldEquipment;

public class RightCrashSymbol extends ShieldEquipment
{
	public RightCrashSymbol (ChatColor color)
	{
		super (EquipmentTypes.RIGHT_CRASH_SYMBOL, DyeColor.YELLOW, EquipmentSlot.OFF_HAND, color);
		this.loreColor = ChatColor.GOLD;
		this.wordsPerLine = 5;
		this.setDisplayName("Right Crash Symbol");
		this.setDisplayName(StringUtil.alternateTextColors("Right Crash Symbol", TextPattern.WORD,
				ChatColor.RED, ChatColor.GOLD));
		this.addLoreToItemMeta("Hold this crash symbol in your offhand to deal increased damage!");
		
		this.addPatterns(new Pattern (DyeColor.BLACK, PatternType.FLOWER),
				new Pattern (DyeColor.YELLOW, PatternType.STRIPE_BOTTOM),
				new Pattern (DyeColor.BLACK, PatternType.STRIPE_DOWNRIGHT),
				new Pattern (DyeColor.YELLOW, PatternType.STRIPE_TOP),
				new Pattern (DyeColor.YELLOW, PatternType.CURLY_BORDER),
				new Pattern (DyeColor.YELLOW, PatternType.STRIPE_LEFT),
				new Pattern (DyeColor.YELLOW, PatternType.STRIPE_RIGHT));
		
		this.addAttackAttributes(3.0, 0.0);
		this.addHealthAttributes(10.0);
		this.addUnsafeEnchantment(Enchantment.MENDING, 1);
		this.addUnsafeEnchantment(Enchantment.DURABILITY, 3);
		this.makeSoulbound();
	}
}
