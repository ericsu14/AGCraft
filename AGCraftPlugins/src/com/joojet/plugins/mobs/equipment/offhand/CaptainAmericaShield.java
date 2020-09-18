package com.joojet.plugins.mobs.equipment.offhand;

import org.bukkit.ChatColor;
import org.bukkit.DyeColor;
import org.bukkit.block.banner.Pattern;
import org.bukkit.block.banner.PatternType;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemFlag;

import com.joojet.plugins.agcraft.enums.TextPattern;
import com.joojet.plugins.agcraft.util.StringUtil;
import com.joojet.plugins.mobs.enums.EquipmentTypes;
import com.joojet.plugins.mobs.equipment.ShieldEquipment;

public class CaptainAmericaShield extends ShieldEquipment
{
	public CaptainAmericaShield (ChatColor color)
	{
		super (EquipmentTypes.CAPTAIN_AMERICA_SHIELD, DyeColor.WHITE, EquipmentSlot.OFF_HAND, color);
		this.loreColor = ChatColor.AQUA;
		this.setDisplayName(StringUtil.alternateTextColors("Captain America's Shield", TextPattern.WORD, 
				ChatColor.RED, ChatColor.WHITE, ChatColor.BLUE));
		
		this.addPatterns(new Pattern (DyeColor.RED, PatternType.STRIPE_SMALL),
				new Pattern (DyeColor.BLUE, PatternType.CURLY_BORDER),
				new Pattern (DyeColor.BLUE, PatternType.RHOMBUS_MIDDLE),
				new Pattern (DyeColor.BLUE, PatternType.FLOWER),
				new Pattern (DyeColor.WHITE, PatternType.CIRCLE_MIDDLE),
				new Pattern (DyeColor.BLUE, PatternType.BORDER));
		
		this.addItemFlags(ItemFlag.HIDE_POTION_EFFECTS, ItemFlag.HIDE_ENCHANTS);
		this.makeUnbreakable();
		this.addAttackAttributes(6.0, 0.0);
		this.addDefenseAttributes(0.0, 4.0, 0.15);
		this.addHealthAttributes(14.0);
		this.addUnsafeEnchantment(Enchantment.DURABILITY, 5);
		this.addLoreToItemMeta("Crafted from Adamantium, this shield makes you feel like a complete superhero!");
		this.makeSoulbound();
	}
}
