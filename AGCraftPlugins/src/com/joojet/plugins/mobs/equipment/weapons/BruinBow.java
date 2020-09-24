package com.joojet.plugins.mobs.equipment.weapons;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.EquipmentSlot;

import com.joojet.plugins.agcraft.enums.TextPattern;
import com.joojet.plugins.agcraft.util.StringUtil;
import com.joojet.plugins.mobs.enums.EquipmentType;
import com.joojet.plugins.mobs.equipment.Equipment;


public class BruinBow extends Equipment
{
	public BruinBow ()
	{
		super (EquipmentType.BRUIN_BOW, Material.BOW, EquipmentSlot.HAND, ChatColor.AQUA);
		this.setDisplayName(StringUtil.alternateTextColors("Bruin Bow", TextPattern.WORD, 
				ChatColor.AQUA, ChatColor.GOLD));
		this.loreColor = ChatColor.GOLD;
		this.wordsPerLine = 6;
		this.addLoreToItemMeta("The Bruins are up to no good here...");
		this.addUnsafeEnchantment(Enchantment.ARROW_DAMAGE, 4);
		this.addUnsafeEnchantment(Enchantment.ARROW_KNOCKBACK, 1);
		this.addUnsafeEnchantment(Enchantment.DURABILITY, 2);
		this.addUnsafeEnchantment(Enchantment.ARROW_INFINITE, 1);
	}
}
