package com.joojet.plugins.mobs.monsters.wither_skeleton;

import org.bukkit.ChatColor;
import org.bukkit.block.Biome;

import com.joojet.plugins.mobs.enums.CustomPotionEffect;
import com.joojet.plugins.mobs.equipment.boots.LightweightNetheriteBoots;
import com.joojet.plugins.mobs.equipment.chest.DarkNetheriteChestplate;
import com.joojet.plugins.mobs.equipment.weapons.PerpetualTorment;
import com.joojet.plugins.mobs.interfaces.MobEquipment;

public class SoulDestroyer extends MobEquipment 
{
	public SoulDestroyer ()
	{
		this.name = "Soul Destroyer";
		this.color = ChatColor.LIGHT_PURPLE;
		this.health = 30;
		
		this.addBiomes(Biome.NETHER_WASTES, Biome.SOUL_SAND_VALLEY, Biome.CRIMSON_FOREST, Biome.WARPED_FOREST,
				Biome.BASALT_DELTAS);
		
		this.addPotionEffect(CustomPotionEffect.SPEED);
		this.addPotionEffect(CustomPotionEffect.FIRE_RESISTANCE);
		
		this.chestplate = new DarkNetheriteChestplate (this.color);
		this.boots = new LightweightNetheriteBoots (this.color);
		
		this.weapon = new PerpetualTorment (this.color);
	}
}
