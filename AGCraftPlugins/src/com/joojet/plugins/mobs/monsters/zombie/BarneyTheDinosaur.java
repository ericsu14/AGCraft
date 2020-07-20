package com.joojet.plugins.mobs.monsters.zombie;

import org.bukkit.ChatColor;
import org.bukkit.block.Biome;

import com.joojet.plugins.mobs.enums.CustomPotionEffect;
import com.joojet.plugins.mobs.equipment.boots.BarneyFeet;
import com.joojet.plugins.mobs.equipment.chest.BarneyChest;
import com.joojet.plugins.mobs.equipment.head.BarneyHead;
import com.joojet.plugins.mobs.equipment.leggings.BarneyLegs;
import com.joojet.plugins.mobs.equipment.offhand.BarneyTotem;
import com.joojet.plugins.mobs.equipment.weapons.BarneyDagger;
import com.joojet.plugins.mobs.interfaces.MobEquipment;

public class BarneyTheDinosaur extends MobEquipment
{
	public BarneyTheDinosaur ()
	{
		this.huntOnSpawn = true;
		this.spawnLightning = true;
		this.showName = true;
		this.huntRadius = 50;
		this.health = 8;
		this.addBiomes(Biome.FLOWER_FOREST, Biome.DARK_FOREST, Biome.DARK_FOREST_HILLS,
				Biome.SWAMP, Biome.SWAMP_HILLS, Biome.JUNGLE, Biome.JUNGLE_HILLS, Biome.JUNGLE_EDGE,
				Biome.GRAVELLY_MOUNTAINS, Biome.SUNFLOWER_PLAINS);
		this.name = "Barney the Dinosaur";
		this.color = ChatColor.DARK_PURPLE;
		this.setDropRates(0.01f, 0.01f, 0.01f, 0.01f, 0.01f, 1.00f);
		this.helmet = new BarneyHead (this.color);
		this.chestplate = new BarneyChest (this.color);
		this.leggings = new BarneyLegs (this.color);
		this.boots = new BarneyFeet (this.color);
		this.weapon = new BarneyDagger (this.color);
		this.offhand = new BarneyTotem (this.color);
		this.addPotionEffect(CustomPotionEffect.SPEED);
		this.addPotionEffect(CustomPotionEffect.FIRE_RESISTANCE);
	}
}
