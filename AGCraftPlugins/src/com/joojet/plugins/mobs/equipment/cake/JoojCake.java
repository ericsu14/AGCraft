package com.joojet.plugins.mobs.equipment.cake;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemFlag;

import com.joojet.plugins.agcraft.enums.TextPattern;
import com.joojet.plugins.agcraft.util.StringUtil;
import com.joojet.plugins.mobs.enums.EquipmentType;
import com.joojet.plugins.mobs.equipment.Equipment;

public class JoojCake extends Equipment 
{
	public JoojCake ()
	{
		super (EquipmentType.JOOJ_CAKE, Material.CAKE, EquipmentSlot.OFF_HAND, ChatColor.GOLD);
		this.setDisplayName(StringUtil.alternateTextColors("jooj's Birthday Cake", TextPattern.WORD, 
				ChatColor.RED, ChatColor.DARK_RED, ChatColor.GOLD));
		
		this.loreColor = ChatColor.DARK_PURPLE;
		this.wordsPerLine = 8;
		
		this.wordsPerLine = 6;
		this.addLoreToItemMeta(StringUtil.alternateTextColors("It may be weird to bake a cake for myself, but I want everyone to "
				+ "enjoy a little slice of the spiritual fantasy!", TextPattern.WORD,
				ChatColor.RED, ChatColor.GOLD, ChatColor.YELLOW, ChatColor.GREEN, ChatColor.BLUE, ChatColor.DARK_PURPLE, ChatColor.LIGHT_PURPLE));
		this.addUnsafeEnchantment(Enchantment.LOOT_BONUS_BLOCKS, 3);
		this.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		this.makeSoulbound();
	}
}
