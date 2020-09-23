package com.joojet.plugins.mobs.equipment.weapons;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.EquipmentSlot;

import com.joojet.plugins.agcraft.enums.TextPattern;
import com.joojet.plugins.agcraft.util.StringUtil;
import com.joojet.plugins.mobs.enums.EquipmentType;
import com.joojet.plugins.mobs.equipment.Equipment;

public class TheTrojanDestroyer extends Equipment 
{
	public TheTrojanDestroyer ()
	{
		super (EquipmentType.THE_TROJAN_DESTROYER, Material.BOW, EquipmentSlot.HAND, ChatColor.AQUA);
		this.setDisplayName(StringUtil.alternateTextColors("The Trojan Destroyer", TextPattern.WORD, 
				ChatColor.AQUA, ChatColor.GOLD));
		this.wordsPerLine = 6;
		this.loreColor = ChatColor.GOLD;
		this.addLoreToItemMeta("This will send you to the shadow realm...");
		this.addUnsafeEnchantment(Enchantment.ARROW_DAMAGE, 6);
		this.addUnsafeEnchantment(Enchantment.VANISHING_CURSE, 1);
		this.addUnsafeEnchantment(Enchantment.ARROW_FIRE, 2);
		this.addUnsafeEnchantment(Enchantment.ARROW_KNOCKBACK, 2);
		this.addSpeedAttribute(0.05);
	}
}
