package com.joojet.plugins.mobs.allies.golem;

import org.bukkit.ChatColor;
import org.bukkit.block.Biome;

import com.joojet.plugins.mobs.enums.CustomPotionEffect;
import com.joojet.plugins.mobs.enums.MonsterType;
import com.joojet.plugins.mobs.equipment.chest.ThePecks;
import com.joojet.plugins.mobs.monsters.MobEquipment;

public class JohnJae extends MobEquipment
{
	public JohnJae ()
	{
		super (MonsterType.JOHN_JAE);
		this.addBiomes(Biome.THE_VOID);
		this.name = "John Jae";
		this.color = ChatColor.GOLD;
		this.showName = true;

		
		this.addPotionEffect(CustomPotionEffect.RESISTANCE_II, 
				CustomPotionEffect.REGEN, 
				CustomPotionEffect.STRENGTH_II, 
				CustomPotionEffect.FIRE_RESISTANCE,
				CustomPotionEffect.JUMP_BOOST, 
				CustomPotionEffect.SPEED);
		
		this.health = 250;
		
		// Chestplate
		this.chestplate = new ThePecks (this.color);
	}
}
