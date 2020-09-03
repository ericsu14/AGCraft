package com.joojet.plugins.mobs.equipment.offhand;

import org.bukkit.ChatColor;
import org.bukkit.DyeColor;
import org.bukkit.block.banner.Pattern;
import org.bukkit.block.banner.PatternType;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemFlag;

import com.joojet.plugins.mobs.enums.EquipmentTypes;
import com.joojet.plugins.mobs.equipment.ShieldEquipment;

public class BlueAndGold extends ShieldEquipment 
{
	public BlueAndGold ()
	{
		super (EquipmentTypes.BLUE_AND_GOLD, DyeColor.BLUE, EquipmentSlot.OFF_HAND, ChatColor.GOLD);
		this.wordsPerLine = 8;
		this.loreColor = ChatColor.AQUA;
		this.setDisplayName("The " + ChatColor.BLUE + "Blue " + this.chatColor + "and Gold");
		this.addLoreToItemMeta("A special one-off sheild that proudly carries the colors of AΓΩ.");
		
		this.addPatterns(new Pattern (DyeColor.YELLOW, PatternType.STRIPE_SMALL),
				new Pattern (DyeColor.BLUE, PatternType.STRIPE_BOTTOM),
				new Pattern (DyeColor.BLUE, PatternType.STRIPE_TOP),
				new Pattern (DyeColor.BLUE, PatternType.RHOMBUS_MIDDLE),
				new Pattern (DyeColor.YELLOW, PatternType.TRIANGLES_BOTTOM),
				new Pattern (DyeColor.YELLOW, PatternType.TRIANGLES_TOP),
				new Pattern (DyeColor.YELLOW, PatternType.STRAIGHT_CROSS),
				new Pattern (DyeColor.BLUE, PatternType.CURLY_BORDER));
		
		this.addItemFlags(ItemFlag.HIDE_POTION_EFFECTS, ItemFlag.HIDE_ENCHANTS);
		this.makeUnbreakable();
		this.addAttackAttributes(4.0, 0.0);
		this.addDefenseAttributes(4.0, 4.0, 0.10);
		this.addHealthAttributes(20.0);
		this.addUnsafeEnchantment(Enchantment.DURABILITY, 5);
		this.makeSoulbound();
	}
}
