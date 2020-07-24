package com.joojet.plugins.mobs.allies.snowman;

import org.bukkit.ChatColor;
import org.bukkit.block.Biome;

import com.joojet.plugins.mobs.enums.CustomPotionEffect;
import com.joojet.plugins.mobs.equipment.boots.LetItGo;
import com.joojet.plugins.mobs.equipment.head.ScruffyFace;
import com.joojet.plugins.mobs.interfaces.MobEquipment;

public class Scruffy extends MobEquipment
{
	public Scruffy ()
	{
		this.addBiomes(Biome.THE_VOID);
		this.name = "Scruffy";
		this.color = ChatColor.GOLD;
		this.health = 40;
		this.showName = true;
		
		this.addPotionEffect(CustomPotionEffect.RESISTANCE, CustomPotionEffect.WATER_BREATHING,
				CustomPotionEffect.FIRE_RESISTANCE, CustomPotionEffect.REGEN, CustomPotionEffect.SPEED);
		
		// Chestplate
		this.helmet = new ScruffyFace (this.color);
		
		// Boots
		this.boots = new LetItGo (this.color);
	}
		
}
