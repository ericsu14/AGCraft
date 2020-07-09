package com.joojet.plugins.mobs.allies.snowman;

import org.bukkit.ChatColor;

import com.joojet.plugins.mobs.enums.CustomPotionEffect;
import com.joojet.plugins.mobs.equipment.head.FrostyFace;
import com.joojet.plugins.mobs.interfaces.MobEquipment;

public class Frosty extends MobEquipment
{
	public Frosty ()
	{
		this.name = "Frosty the Snowman";
		this.color = ChatColor.AQUA;
		this.health = 30;
		this.showName = true;
		
		this.addPotionEffect(CustomPotionEffect.RESISTANCE);
		this.addPotionEffect(CustomPotionEffect.WATER_BREATHING);
		this.addPotionEffect(CustomPotionEffect.FIRE_RESISTANCE);
		this.addPotionEffect(CustomPotionEffect.REGEN);
		
		// Chestplate
		this.helmet = new FrostyFace (this.color);
	}
}
