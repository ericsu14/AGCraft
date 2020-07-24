package com.joojet.plugins.mobs.monsters.zombie_pigmen;

import org.bukkit.ChatColor;
import org.bukkit.block.Biome;

import com.joojet.plugins.mobs.enums.CustomPotionEffect;
import com.joojet.plugins.mobs.equipment.head.DarkNetheriteHelmet;
import com.joojet.plugins.mobs.equipment.offhand.PigmanDagger;
import com.joojet.plugins.mobs.equipment.weapons.PigmanSword;
import com.joojet.plugins.mobs.interfaces.MobEquipment;


public class AkimboPigman extends MobEquipment 
{
	public AkimboPigman ()
	{
		this.name = "Akimbo Pigman";
		this.health = 40;
		this.color = ChatColor.GOLD;
		this.showName = true;
		
		this.addPotionEffect(CustomPotionEffect.SPEED);
		
		this.addBiomes(Biome.NETHER_WASTES, Biome.SOUL_SAND_VALLEY, Biome.CRIMSON_FOREST, Biome.WARPED_FOREST,
				Biome.BASALT_DELTAS);
		
		this.helmet = new DarkNetheriteHelmet (this.color);
		this.weapon = new PigmanSword (this.color);
		this.offhand = new PigmanDagger (this.color);
	}
}
