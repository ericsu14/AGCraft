package com.joojet.plugins.mobs.allies.snowman;

import org.bukkit.ChatColor;
import org.bukkit.block.Biome;

import com.joojet.plugins.mobs.enums.CustomPotionEffect;
import com.joojet.plugins.mobs.equipment.head.FrostyFace;
import com.joojet.plugins.mobs.interfaces.MobEquipment;

public class Frosty extends MobEquipment
{
	public Frosty ()
	{
		this.addBiomes(Biome.THE_VOID);
		this.name = "Frosty the Snowman";
		this.color = ChatColor.AQUA;
		this.health = 30;
		this.showName = true;
		
		this.addPotionEffect(CustomPotionEffect.RESISTANCE, 
				CustomPotionEffect.WATER_BREATHING, 
				CustomPotionEffect.FIRE_RESISTANCE, 
				CustomPotionEffect.REGEN);
		
		// Chestplate
		this.helmet = new FrostyFace (this.color);
	}
}
