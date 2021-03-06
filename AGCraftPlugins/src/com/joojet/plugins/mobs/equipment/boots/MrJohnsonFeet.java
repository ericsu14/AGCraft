package com.joojet.plugins.mobs.equipment.boots;

import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.inventory.EquipmentSlot;

import com.joojet.plugins.agcraft.enums.TextPattern;
import com.joojet.plugins.agcraft.util.StringUtil;
import com.joojet.plugins.mobs.enums.EquipmentType;
import com.joojet.plugins.mobs.equipment.LeatherEquipment;

public class MrJohnsonFeet extends LeatherEquipment 
{

	public MrJohnsonFeet(ChatColor chatColor) 
	{
		super(EquipmentType.MR_JOHNSON_FEET, Material.LEATHER_BOOTS, EquipmentSlot.FEET, chatColor);
		this.setDisplayName(StringUtil.alternateTextColors("Mr. Johnson's Slithery Backend", TextPattern.WORD, 
				ChatColor.GOLD, ChatColor.GOLD, ChatColor.DARK_GRAY, ChatColor.DARK_BLUE));
		this.setColor(Color.BLACK);
		this.addDefenseAttributes(3.0, 2.0, 0.1);
		this.addSpeedAttribute(0.10);
		this.makeUnbreakable();
	}

}
