package com.joojet.plugins.mobs.monsters.spider;

import org.bukkit.ChatColor;
import org.bukkit.block.Biome;

import com.joojet.plugins.mobs.enums.CustomPotionEffect;
import com.joojet.plugins.mobs.equipment.chest.DarkNetheriteChestplate;
import com.joojet.plugins.mobs.equipment.weapons.FireVenomFang;
import com.joojet.plugins.mobs.monsters.MobEquipment;

public class EnragedSpider extends MobEquipment
{
	public EnragedSpider ()
	{
		this.name = "One Enraged Firey Boi";
		this.color = ChatColor.LIGHT_PURPLE;
		this.onFire = true;
		
		this.addBiomes(Biome.THE_VOID);
		
		this.setDropRates(0.0f, 0.0f, 0.0f, 0.0f, 0.05f, 0.0f);
		
		// Custom potion effects
		this.effects.add(CustomPotionEffect.SPEED.getPotionEffect());
		this.effects.add(CustomPotionEffect.FIRE_RESISTANCE.getPotionEffect());
		
		// Weapon
		this.weapon = new FireVenomFang (this.color);
		// Chestplate
		this.chestplate = new DarkNetheriteChestplate (this.color);
	}
}
