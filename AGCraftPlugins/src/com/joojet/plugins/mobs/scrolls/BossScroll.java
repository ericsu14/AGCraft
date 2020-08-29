package com.joojet.plugins.mobs.scrolls;

import org.bukkit.ChatColor;
import org.bukkit.entity.EntityType;

import com.joojet.plugins.mobs.monsters.MobEquipment;

public class BossScroll extends SummoningScroll 
{
	public BossScroll (MobEquipment equipment, EntityType type)
	{
		super (equipment, type);
		this.wordsPerLine = 9;
		this.loreColor = ChatColor.DARK_RED;
		this.addLoreToItemMeta("WARNING! A powerful mob lurks inside of this scroll. Use with extreme caution!");
	}
}
