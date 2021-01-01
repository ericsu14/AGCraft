package com.joojet.plugins.mobs.equipment.head;

import org.bukkit.ChatColor;
import org.bukkit.enchantments.Enchantment;

import com.joojet.plugins.agcraft.enums.TextPattern;
import com.joojet.plugins.agcraft.util.StringUtil;
import com.joojet.plugins.mobs.enums.EquipmentType;
import com.joojet.plugins.mobs.enums.PlayerHead;
import com.joojet.plugins.mobs.equipment.Equipment;

public class HellwakerHead extends Equipment
{
	public HellwakerHead (ChatColor color)
	{
		super (EquipmentType.HELLWALKER_HEAD, PlayerHead.ETERNAL_SHADOW_CLONE_JOOJETSU, color);
		this.wordsPerLine = 6;
		this.loreColor = ChatColor.DARK_RED;
		this.setDisplayName(StringUtil.alternateTextColors("The Head of the Hellwalker", TextPattern.WORD, ChatColor.RED, ChatColor.DARK_GRAY));
		this.addUnsafeEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 4);
		this.addUnsafeEnchantment(Enchantment.PROTECTION_PROJECTILE, 4);
		this.addAttackAttributes(2.0, 0.1);
		this.addDefenseAttributes(3.0, 4.0, 0.25);
		this.addHealthAttributes(8.0);
		this.makeSoulbound();
	}
}
