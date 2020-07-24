package com.joojet.plugins.mobs.equipment.head;

import org.bukkit.ChatColor;

import com.joojet.plugins.mobs.enums.PlayerHead;
import com.joojet.plugins.mobs.equipment.Equipment;


public class USAHat extends Equipment
{
	public USAHat ()
	{
		super (PlayerHead.UNCLE_SAMS, ChatColor.GOLD);
		this.setDisplayName(this.americanizeText("USA"));
		this.addDefenseAttributes(3.0, 1.5, 0.05);
		this.addLoreToItemMeta("Wear to be blinded with complete patriotism!");
	}
}
