package com.joojet.plugins.mobs.monsters.wither_skeleton;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.Biome;
import org.bukkit.inventory.ItemStack;

import com.joojet.plugins.mobs.enums.CustomPotionEffect;
import com.joojet.plugins.mobs.equipment.boots.DoomGuyFeet;
import com.joojet.plugins.mobs.equipment.chest.DoomChest;
import com.joojet.plugins.mobs.equipment.head.DoomSlayerHead;
import com.joojet.plugins.mobs.equipment.leggings.DoomGuyLegs;
import com.joojet.plugins.mobs.equipment.weapons.DoomBlade;
import com.joojet.plugins.mobs.monsters.MobEquipment;

public class DoomGuy extends MobEquipment
{
	public DoomGuy ()
	{
		this.name = "The Doom Slayer";
		this.setDropRates(0.03f, 0.03f, 0.03f, 0.03f, 0.05f, 0.15f);
		this.color = ChatColor.GOLD;
		
		this.addBiomes(Biome.NETHER_WASTES, Biome.SOUL_SAND_VALLEY, Biome.CRIMSON_FOREST, Biome.WARPED_FOREST,
				Biome.BASALT_DELTAS);
		
		this.addPotionEffect(CustomPotionEffect.FIRE_RESISTANCE);
		
		this.spawnLightning = true;
		this.showName = true;
		this.huntOnSpawn = true;
		this.huntRadius = 125;
		
		this.helmet = new DoomSlayerHead (this.color);
		this.chestplate = new DoomChest (this.color);
		this.leggings = new DoomGuyLegs (this.color);
		this.boots = new DoomGuyFeet (this.color);
		this.weapon = new DoomBlade (this.color);
		this.offhand = new ItemStack (Material.WITHER_SKELETON_SKULL, 1);
	}
}