package com.joojet.plugins.mobs.equipment.leggings;

import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.inventory.EquipmentSlot;

import com.joojet.plugins.agcraft.enums.TextPattern;
import com.joojet.plugins.agcraft.util.StringUtil;
import com.joojet.plugins.mobs.enums.EquipmentType;
import com.joojet.plugins.mobs.equipment.LeatherEquipment;

public class MrJohnsonLeggings extends LeatherEquipment 
{

	public MrJohnsonLeggings(ChatColor chatColor) 
	{
		super(EquipmentType.MR_JOHNSON_LEGGINGS, Material.LEATHER_LEGGINGS, EquipmentSlot.LEGS, chatColor);
		this.addDefenseAttributes(5.0, 2.0, 0.10);
		this.setDisplayName(StringUtil.alternateTextColors("Mr. Johnson's Lower Exoskeleton", TextPattern.WORD, 
				ChatColor.GOLD, ChatColor.GOLD, ChatColor.DARK_GRAY, ChatColor.DARK_BLUE));
		this.setColor(Color.fromRGB(17, 41, 89));
		this.makeUnbreakable();
	}

}
