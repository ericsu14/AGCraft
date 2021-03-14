package com.joojet.plugins.mobs.equipment.boots;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.EquipmentSlot;

import com.joojet.plugins.agcraft.enums.TextPattern;
import com.joojet.plugins.agcraft.util.StringUtil;
import com.joojet.plugins.mobs.enums.EquipmentType;
import com.joojet.plugins.mobs.equipment.Equipment;

public class MrJohnsonFeet extends Equipment 
{

	public MrJohnsonFeet(ChatColor chatColor) 
	{
		super(EquipmentType.MR_JOHNSON_FEET, Material.NETHERITE_BOOTS, EquipmentSlot.FEET, chatColor);
		this.setDisplayName(StringUtil.alternateTextColors("Mr. Johnson's Slithery Backend", TextPattern.WORD, 
				ChatColor.GOLD, ChatColor.GOLD, ChatColor.DARK_GRAY, ChatColor.DARK_BLUE));
		this.addUnsafeEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 1);
		this.addSpeedAttribute(0.10);
		this.makeUnbreakable();
	}

}
