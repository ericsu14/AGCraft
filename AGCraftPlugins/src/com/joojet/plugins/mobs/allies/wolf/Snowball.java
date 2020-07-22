package com.joojet.plugins.mobs.allies.wolf;

import org.bukkit.ChatColor;
import org.bukkit.block.Biome;

import com.joojet.plugins.mobs.enums.CustomPotionEffect;
import com.joojet.plugins.mobs.interfaces.MobEquipment;

public class Snowball extends MobEquipment
{
	public Snowball ()
	{
		this.addBiomes(Biome.THE_VOID);
		this.color = ChatColor.AQUA;
		this.name = "Snowball";
		this.addPotionEffect(CustomPotionEffect.RESISTANCE);
		
	}
}
