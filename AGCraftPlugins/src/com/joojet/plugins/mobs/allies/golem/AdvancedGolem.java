package com.joojet.plugins.mobs.allies.golem;

import org.bukkit.ChatColor;
import org.bukkit.block.Biome;

import com.joojet.plugins.mobs.enums.CustomPotionEffect;
import com.joojet.plugins.mobs.interfaces.MobEquipment;

public class AdvancedGolem extends MobEquipment
{
	public AdvancedGolem ()
	{
		this.addBiomes(Biome.THE_VOID);
		
		this.name = "Advanced Golem";
		this.color = ChatColor.LIGHT_PURPLE;
		this.health = 200;
		this.showName = true;
		
		this.addPotionEffect(CustomPotionEffect.RESISTANCE, CustomPotionEffect.SPEED,
				CustomPotionEffect.STRENGTH);
	}
}
