package com.joojet.plugins.mobs.monsters.wither_skeleton;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.Biome;
import org.bukkit.inventory.ItemStack;

import com.joojet.plugins.mobs.enums.CustomPotionEffect;
import com.joojet.plugins.mobs.equipment.boots.LightweightDiamondBoots;
import com.joojet.plugins.mobs.equipment.chest.ReinforcedDiamondChestplate;
import com.joojet.plugins.mobs.equipment.offhand.EnhancedWitheringArrow;
import com.joojet.plugins.mobs.equipment.weapons.AngelOfDeath;
import com.joojet.plugins.mobs.interfaces.MobEquipment;


public class SoulObliterator extends MobEquipment
{
	public SoulObliterator ()
	{
		this.name = "Soul Obliterator";
		this.color = ChatColor.LIGHT_PURPLE;
		this.health = 16;
		
		this.addBiomes(Biome.NETHER_WASTES, Biome.SOUL_SAND_VALLEY, Biome.CRIMSON_FOREST, Biome.WARPED_FOREST,
				Biome.BASALT_DELTAS);
		
		this.addPotionEffect(CustomPotionEffect.FIRE_RESISTANCE);
		this.helmet = new ItemStack (Material.WITHER_SKELETON_SKULL, 1);
		this.chestplate = new ReinforcedDiamondChestplate (this.color);
		this.boots = new LightweightDiamondBoots (this.color);
		this.weapon = new AngelOfDeath (this.color);
		this.offhand = new EnhancedWitheringArrow (this.color);
	}
}
