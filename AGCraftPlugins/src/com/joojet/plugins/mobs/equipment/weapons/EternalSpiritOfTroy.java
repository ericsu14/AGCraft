package com.joojet.plugins.mobs.equipment.weapons;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.EquipmentSlot;

import com.joojet.plugins.agcraft.enums.TextPattern;
import com.joojet.plugins.agcraft.util.StringUtil;
import com.joojet.plugins.mobs.enums.EquipmentType;
import com.joojet.plugins.mobs.equipment.Equipment;


public class EternalSpiritOfTroy extends Equipment 
{
	public EternalSpiritOfTroy ()
	{
		super (EquipmentType.ETERNAL_SPIRIT_OF_TROY, Material.BOW, EquipmentSlot.HAND, ChatColor.GOLD);
		this.setDisplayName(StringUtil.alternateTextColors("The Eternal Spirit of Troy", TextPattern.WORD, 
				ChatColor.RED, ChatColor.GOLD));
		this.loreColor = ChatColor.GOLD;
		this.wordsPerLine = 6;
		this.addLoreToItemMeta("A weapon weilded by legends.");
		this.addUnsafeEnchantment(Enchantment.ARROW_DAMAGE, 6);
		this.addUnsafeEnchantment(Enchantment.DURABILITY, 3);
		this.addUnsafeEnchantment(Enchantment.ARROW_FIRE, 1);
		this.addUnsafeEnchantment(Enchantment.ARROW_INFINITE, 1);
		this.addUnsafeEnchantment(Enchantment.ARROW_KNOCKBACK, 2);
		this.makeSoulbound();
	}
}
