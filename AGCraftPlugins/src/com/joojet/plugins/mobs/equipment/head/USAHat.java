package com.joojet.plugins.mobs.equipment.head;

import org.bukkit.ChatColor;

import com.joojet.plugins.agcraft.enums.TextPattern;
import com.joojet.plugins.agcraft.util.StringUtil;
import com.joojet.plugins.mobs.enums.EquipmentType;
import com.joojet.plugins.mobs.enums.PlayerHead;
import com.joojet.plugins.mobs.equipment.Equipment;


public class USAHat extends Equipment
{
	public USAHat ()
	{
		super (EquipmentType.USA_HAT, PlayerHead.UNCLE_SAMS, ChatColor.GOLD);
		this.setDisplayName(StringUtil.alternateTextColors("USA", TextPattern.CHARACTER, ChatColor.RED, ChatColor.WHITE,
				ChatColor.BLUE));
		this.addDefenseAttributes(3.0, 1.5, 0.05);
		this.addLoreToItemMeta("Wear to be blinded with complete patriotism!");
	}
}
