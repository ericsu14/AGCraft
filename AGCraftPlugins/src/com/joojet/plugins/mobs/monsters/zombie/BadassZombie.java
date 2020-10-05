package com.joojet.plugins.mobs.monsters.zombie;

import org.bukkit.ChatColor;
import org.bukkit.block.Biome;

import com.joojet.plugins.mobs.enums.CustomPotionEffect;
import com.joojet.plugins.mobs.enums.MonsterStat;
import com.joojet.plugins.mobs.enums.MonsterType;
import com.joojet.plugins.mobs.equipment.boots.LightweightIronBoots;
import com.joojet.plugins.mobs.equipment.chest.ReinforcedDiamondChestplate;
import com.joojet.plugins.mobs.equipment.head.ReinforcedIronHelmet;
import com.joojet.plugins.mobs.equipment.leggings.ReinforcedIronLeggings;
import com.joojet.plugins.mobs.equipment.weapons.SharpenedIronSword;
import com.joojet.plugins.mobs.monsters.factions.EpicMob;

public class BadassZombie extends EpicMob
{
	public BadassZombie ()
	{
		super (MonsterType.BADASS_ZOMBIE);
		this.name = "Badass Zombie";
		this.color = ChatColor.LIGHT_PURPLE;
		this.setStat(MonsterStat.HEALTH, 16.0);
		
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
		
		this.setStat(MonsterStat.EXPERIENCE, 25.0);
	}
}
