package com.joojet.plugins.mobs.allies.wolf;

import org.bukkit.ChatColor;
import org.bukkit.block.Biome;

import com.joojet.plugins.mobs.enums.CustomPotionEffect;
import com.joojet.plugins.mobs.equipment.head.AlphaWolfHead;
import com.joojet.plugins.mobs.interfaces.MobEquipment;

public class Cookie extends MobEquipment
{
	public Cookie ()
	{
		this.addBiomes(Biome.THE_VOID);
		this.color = ChatColor.GOLD;
		this.name = "Cookie";
		this.addPotionEffect(CustomPotionEffect.REGEN);
		this.helmet = new AlphaWolfHead (this.color);
	}
}
