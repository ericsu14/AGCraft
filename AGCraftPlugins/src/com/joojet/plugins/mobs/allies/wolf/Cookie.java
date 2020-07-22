package com.joojet.plugins.mobs.allies.wolf;

import org.bukkit.ChatColor;
import org.bukkit.block.Biome;

import com.joojet.plugins.mobs.enums.CustomPotionEffect;
import com.joojet.plugins.mobs.equipment.chest.CookieHeart;
import com.joojet.plugins.mobs.interfaces.MobEquipment;

public class Cookie extends MobEquipment
{
	public Cookie ()
	{
		this.addBiomes(Biome.THE_VOID);
		this.setDropRates(0.00f, 1.15f, 0.00f, 0.00f, 0.00f, 0.00f);
		this.color = ChatColor.GOLD;
		this.name = "Cookie";
		this.addPotionEffect(CustomPotionEffect.REGEN);
		this.addPotionEffect(CustomPotionEffect.FIRE_RESISTANCE);
		this.chestplate = new CookieHeart (this.color);
	}
}
