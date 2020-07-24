package com.joojet.plugins.mobs.monsters.piglin;

import org.bukkit.ChatColor;
import org.bukkit.block.Biome;

import com.joojet.plugins.mobs.equipment.chest.DarkNetheriteChestplate;
import com.joojet.plugins.mobs.equipment.head.PiglinCaptainHead;
import com.joojet.plugins.mobs.equipment.weapons.PiglinAxe;
import com.joojet.plugins.mobs.monsters.MobEquipment;

public class PiglinCaptain extends MobEquipment 
{
	public PiglinCaptain ()
	{
		this.name = "Piglin Captain";
		this.color = ChatColor.GOLD;
		this.showName = true;
		this.health = 40;
		
		this.addBiomes(Biome.NETHER_WASTES, Biome.SOUL_SAND_VALLEY, Biome.CRIMSON_FOREST, Biome.WARPED_FOREST,
				Biome.BASALT_DELTAS);
		
		this.helmet = new PiglinCaptainHead (this.color);
		this.weapon = new PiglinAxe (this.color);
		this.chestplate = new DarkNetheriteChestplate (this.color);
	}
}
