package com.joojet.plugins.mobs.monsters.zombie_pigmen;

import org.bukkit.ChatColor;
import org.bukkit.block.Biome;

import com.joojet.plugins.mobs.equipment.head.ZombiePigmenHead;
import com.joojet.plugins.mobs.equipment.leggings.RoyalGoldLeggings;
import com.joojet.plugins.mobs.equipment.weapons.PigmanSword;
import com.joojet.plugins.mobs.interfaces.MobEquipment;


public class VeteranZombiePigmen extends MobEquipment
{
	public VeteranZombiePigmen ()
	{
		this.name = "Veteran Zombie Pigmen";
		this.color = ChatColor.LIGHT_PURPLE;
		
		this.addBiomes(Biome.NETHER_WASTES, Biome.SOUL_SAND_VALLEY, Biome.CRIMSON_FOREST, Biome.WARPED_FOREST,
				Biome.BASALT_DELTAS);
		
		this.helmet = new ZombiePigmenHead (this.color);
		this.leggings = new RoyalGoldLeggings (this.color);
		this.weapon = new PigmanSword (this.color);
		
	}
}
