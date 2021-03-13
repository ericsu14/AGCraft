package com.joojet.plugins.mobs.equipment.chest;

import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.inventory.EquipmentSlot;

import com.joojet.plugins.agcraft.enums.TextPattern;
import com.joojet.plugins.agcraft.util.StringUtil;
import com.joojet.plugins.mobs.enums.EquipmentType;
import com.joojet.plugins.mobs.equipment.LeatherEquipment;

public class MrJohnsonTunic extends LeatherEquipment 
{

	public MrJohnsonTunic(ChatColor color) 
	{
		super(EquipmentType.MR_JOHNSON_TUNIC, Material.LEATHER_CHESTPLATE, EquipmentSlot.CHEST, color);
		this.setColor(Color.fromRGB(47, 43, 40));
		this.setDisplayName(StringUtil.alternateTextColors("Mr. Johnson's Upper Exoskeleton", TextPattern.WORD, 
				ChatColor.GOLD, ChatColor.GOLD, ChatColor.DARK_GRAY, ChatColor.DARK_BLUE));
		this.addDefenseAttributes(8.0, 3.0, 0.15);
		this.makeUnbreakable();
	}

}
