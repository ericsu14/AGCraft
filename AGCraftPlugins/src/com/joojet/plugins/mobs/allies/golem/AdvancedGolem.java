package com.joojet.plugins.mobs.allies.golem;

import org.bukkit.ChatColor;

import com.joojet.plugins.mobs.enums.CustomPotionEffect;
import com.joojet.plugins.mobs.interfaces.MobEquipment;

public class AdvancedGolem extends MobEquipment
{
	public AdvancedGolem ()
	{
		this.name = "Advanced Golem";
		this.color = ChatColor.LIGHT_PURPLE;
		this.health = 200;
		this.showName = true;
		
		this.addPotionEffect(CustomPotionEffect.RESISTANCE);
		this.addPotionEffect(CustomPotionEffect.SPEED);
		this.addPotionEffect(CustomPotionEffect.STRENGTH);
	}
}
