package com.joojet.plugins.mobs.equipment.offhand;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.EquipmentSlot;

import com.joojet.plugins.agcraft.enums.TextPattern;
import com.joojet.plugins.agcraft.util.StringUtil;
import com.joojet.plugins.mobs.enums.EquipmentType;
import com.joojet.plugins.mobs.equipment.Equipment;

public class HellwalkerDagger extends Equipment
{
	public HellwalkerDagger (ChatColor color)
	{
		super (EquipmentType.HELLWALKER_DAGGER, Material.DIAMOND_SWORD, EquipmentSlot.OFF_HAND, color);
		this.wordsPerLine = 6;
		this.loreColor = ChatColor.DARK_RED;
		this.setDisplayName(StringUtil.alternateTextColors("Hellwalker Dagger", TextPattern.WORD, ChatColor.RED, ChatColor.DARK_GRAY));
		this.addAttackAttributes(7.0, 0.3);
		this.addUnsafeEnchantment(Enchantment.MENDING, 1);
		this.addLoreToItemMeta("The torment that is brought upon those who taste the bite of his blade is much more vibrant than the darkness that awaits them...");
		this.addLoreToItemMeta("Hold in your offhand to deal increased damage.");
		this.makeSoulbound();
	}
}
