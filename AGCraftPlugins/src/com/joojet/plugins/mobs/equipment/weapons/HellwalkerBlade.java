package com.joojet.plugins.mobs.equipment.weapons;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.EquipmentSlot;

import com.joojet.plugins.agcraft.enums.TextPattern;
import com.joojet.plugins.agcraft.util.StringUtil;
import com.joojet.plugins.mobs.enums.EquipmentType;
import com.joojet.plugins.mobs.equipment.Equipment;

public class HellwalkerBlade extends Equipment
{
	public HellwalkerBlade (ChatColor color)
	{
		super (EquipmentType.HELLWALKER_BLADE, Material.NETHERITE_SWORD, EquipmentSlot.HAND, color);
		this.setDisplayName(StringUtil.alternateTextColors("Hellwalker Blade", TextPattern.WORD, ChatColor.RED, ChatColor.DARK_GRAY));
		this.wordsPerLine = 6;
		this.addAttackAttributes(10.0, 1.8);
		this.addUnsafeEnchantment(Enchantment.DAMAGE_UNDEAD, 5);
		this.addUnsafeEnchantment(Enchantment.DURABILITY, 3);
		this.addUnsafeEnchantment(Enchantment.FIRE_ASPECT, 2);
		this.addUnsafeEnchantment(Enchantment.SWEEPING_EDGE, 5);
		this.loreColor = ChatColor.DARK_RED;
		this.addLoreToItemMeta("The torment that is brought upon those who taste the bite of his blade is much more vibrant than the darkness that awaits them...");
		this.addSpeedAttribute(0.05);
		this.makeSoulbound();
	}
}
