package com.joojet.plugins.mobs.monsters.piglin;

import org.bukkit.ChatColor;
import org.bukkit.block.Biome;

import com.joojet.plugins.mobs.equipment.boots.RoyalGoldBoots;
import com.joojet.plugins.mobs.equipment.head.PiglinHunterHelmet;
import com.joojet.plugins.mobs.equipment.offhand.HurtfulArrow;
import com.joojet.plugins.mobs.equipment.weapons.ShotBow;
import com.joojet.plugins.mobs.monsters.MobEquipment;

public class PiglinHunter extends MobEquipment 
{
	public PiglinHunter ()
	{
		this.name = "Piglin Hunter";
		this.color = ChatColor.BLUE;
		
		this.addBiomes(Biome.NETHER_WASTES, Biome.SOUL_SAND_VALLEY, Biome.CRIMSON_FOREST, Biome.WARPED_FOREST,
				Biome.BASALT_DELTAS);
		
		this.helmet = new PiglinHunterHelmet (this.color);
		this.boots = new RoyalGoldBoots (this.color);
		this.weapon = new ShotBow (ChatColor.GOLD);
		this.offhand = new HurtfulArrow (this.color, 64);
	}
}
