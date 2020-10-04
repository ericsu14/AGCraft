package com.joojet.plugins.mobs.equipment.weapons;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.EquipmentSlot;

import com.joojet.plugins.agcraft.enums.TextPattern;
import com.joojet.plugins.agcraft.util.StringUtil;
import com.joojet.plugins.mobs.enums.EquipmentType;
import com.joojet.plugins.mobs.equipment.Equipment;

public class BruinSword extends Equipment
{
	public BruinSword (ChatColor color)
	{
		super (EquipmentType.BRUIN_SWORD, Material.IRON_SWORD, EquipmentSlot.HAND, color);
		this.setDisplayName(StringUtil.alternateTextColors("The Bruin Sword", TextPattern.WORD, 
				ChatColor.AQUA, ChatColor.GOLD));
		this.addUnsafeEnchantment(Enchantment.DAMAGE_ALL, 3);
		this.addUnsafeEnchantment(Enchantment.DURABILITY, 3);
		this.addAttackAttributes(6.0, 1.8);
		this.loreColor = ChatColor.GOLD;
		this.addLoreToItemMeta("The Bruins are up to no good. This weapons just proves it.");
	}
}
