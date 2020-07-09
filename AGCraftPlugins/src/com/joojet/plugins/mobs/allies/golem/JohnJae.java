package com.joojet.plugins.mobs.allies.golem;

import org.bukkit.ChatColor;

import com.joojet.plugins.mobs.enums.CustomPotionEffect;
import com.joojet.plugins.mobs.equipment.chest.ThePecks;
import com.joojet.plugins.mobs.interfaces.MobEquipment;

public class JohnJae extends MobEquipment
{
	public JohnJae ()
	{
		this.name = "John Jae";
		this.color = ChatColor.GOLD;
		this.showName = true;

		this.addPotionEffect(CustomPotionEffect.RESISTANCE_II);
		this.addPotionEffect(CustomPotionEffect.REGEN);
		this.addPotionEffect(CustomPotionEffect.STRENGTH_II);
		this.addPotionEffect(CustomPotionEffect.FIRE_RESISTANCE);
		this.addPotionEffect(CustomPotionEffect.JUMP_BOOST);
		this.addPotionEffect(CustomPotionEffect.SPEED);
		
		this.health = 250;
		
		// Chestplate
		this.chestplate = new ThePecks (this.color);
	}
}
