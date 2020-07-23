package com.joojet.plugins.mobs.monsters.zombie_pigmen;

import org.bukkit.ChatColor;
import org.bukkit.block.Biome;

import com.joojet.plugins.mobs.enums.CustomPotionEffect;
import com.joojet.plugins.mobs.equipment.head.CyborgPigmanHead;
import com.joojet.plugins.mobs.equipment.weapons.PigmanSword;
import com.joojet.plugins.mobs.interfaces.MobEquipment;

public class TheTerminator extends MobEquipment
{
	public TheTerminator ()
	{
		this.name = "The Terminator";
		this.color = ChatColor.GOLD;
		this.health = 40;
		
		this.addBiomes(Biome.NETHER_WASTES, Biome.SOUL_SAND_VALLEY, Biome.CRIMSON_FOREST, Biome.WARPED_FOREST,
				Biome.BASALT_DELTAS);
		
		this.addPotionEffect(CustomPotionEffect.STRENGTH_II);
		this.addPotionEffect(CustomPotionEffect.SPEED);
		
		this.huntOnSpawn = true;
		this.spawnLightning = true;
		this.huntRadius = 100;
		
		this.helmet = new CyborgPigmanHead (this.color);
		this.weapon = new PigmanSword (this.color);
	}
}
