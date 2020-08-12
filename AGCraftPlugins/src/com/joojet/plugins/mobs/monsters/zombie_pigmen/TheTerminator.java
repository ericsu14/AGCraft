package com.joojet.plugins.mobs.monsters.zombie_pigmen;

import org.bukkit.ChatColor;
import org.bukkit.block.Biome;
import org.bukkit.entity.EntityType;

import com.joojet.plugins.mobs.enums.CustomPotionEffect;
import com.joojet.plugins.mobs.enums.MonsterType;
import com.joojet.plugins.mobs.equipment.boots.LightweightNetheriteBoots;
import com.joojet.plugins.mobs.equipment.chest.DarkNetheriteChestplate;
import com.joojet.plugins.mobs.equipment.head.CyborgPigmanHead;
import com.joojet.plugins.mobs.equipment.leggings.DarkNetheriteLeggings;
import com.joojet.plugins.mobs.equipment.offhand.PigmanDagger;
import com.joojet.plugins.mobs.equipment.weapons.PigmanSword;
import com.joojet.plugins.mobs.monsters.MobEquipment;

public class TheTerminator extends MobEquipment
{
	public TheTerminator ()
	{
		super (MonsterType.THE_TERMINATOR);
		this.name = "The Terminator";
		this.color = ChatColor.DARK_RED;
		this.health = 12;
		this.showName = true;
		
		this.addTargetsToHitList(EntityType.PLAYER);
		
		this.addBiomes(Biome.NETHER_WASTES, Biome.SOUL_SAND_VALLEY, Biome.CRIMSON_FOREST, Biome.WARPED_FOREST,
				Biome.BASALT_DELTAS);
		
		this.addPotionEffect(CustomPotionEffect.SPEED);
		
		this.huntOnSpawn = true;
		this.spawnLightning = true;
		this.huntRadius = 100;
		
		this.helmet = new CyborgPigmanHead (this.color);
		this.chestplate = new DarkNetheriteChestplate (this.color);
		this.leggings = new DarkNetheriteLeggings (this.color);
		this.boots = new LightweightNetheriteBoots (this.color);
		this.weapon = new PigmanSword (this.color);
		this.offhand = new PigmanDagger (this.color);
	}
}
