package com.joojet.plugins.mobs.monsters.zombie;

import org.bukkit.ChatColor;
import org.bukkit.block.Biome;

import com.joojet.plugins.mobs.enums.CustomPotionEffect;
import com.joojet.plugins.mobs.equipment.boots.LightweightIronBoots;
import com.joojet.plugins.mobs.equipment.chest.ReinforcedDiamondChestplate;
import com.joojet.plugins.mobs.equipment.head.ReinforcedIronHelmet;
import com.joojet.plugins.mobs.equipment.leggings.ReinforcedIronLeggings;
import com.joojet.plugins.mobs.equipment.weapons.SharpenedIronSword;
import com.joojet.plugins.mobs.interfaces.MobEquipment;

public class BadassZombie extends MobEquipment
{
	public BadassZombie ()
	{
		this.name = "Badass Zombie";
		this.color = ChatColor.LIGHT_PURPLE;
		
		this.addBiomes(Biome.THE_VOID);
		
		// Custom potion effects
		this.addPotionEffect(CustomPotionEffect.FIRE_RESISTANCE);
		
		// Weapon
		this.weapon = new SharpenedIronSword (this.color);
		// Helmet
		this.helmet = new ReinforcedIronHelmet (this.color);
		// Chestplate
		this.chestplate = new ReinforcedDiamondChestplate (this.color);
		// Leggings
		this.leggings = new ReinforcedIronLeggings (this.color);
		// Boots
		this.boots = new LightweightIronBoots (this.color);
	}
}
