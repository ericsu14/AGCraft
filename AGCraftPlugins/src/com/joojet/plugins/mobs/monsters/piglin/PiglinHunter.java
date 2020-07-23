package com.joojet.plugins.mobs.monsters.piglin;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.Biome;
import org.bukkit.inventory.ItemStack;

import com.joojet.plugins.mobs.equipment.boots.RoyalGoldBoots;
import com.joojet.plugins.mobs.equipment.head.PiglinHunterHelmet;
import com.joojet.plugins.mobs.equipment.weapons.ShotBow;
import com.joojet.plugins.mobs.interfaces.MobEquipment;

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
		this.weapon = new ShotBow (this.color);
		this.offhand = new ItemStack (Material.TOTEM_OF_UNDYING, 1);
	}
}
