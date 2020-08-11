package com.joojet.plugins.mobs.monsters.wither_skeleton;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.Biome;
import org.bukkit.inventory.ItemStack;

import com.joojet.plugins.mobs.enums.CustomPotionEffect;
import com.joojet.plugins.mobs.enums.MonsterType;
import com.joojet.plugins.mobs.equipment.boots.LightweightNetheriteBoots;
import com.joojet.plugins.mobs.equipment.chest.DarkNetheriteChestplate;
import com.joojet.plugins.mobs.equipment.weapons.PerpetualTorment;
import com.joojet.plugins.mobs.monsters.MobEquipment;

public class SoulDestroyer extends MobEquipment 
{
	public SoulDestroyer ()
	{
		super (MonsterType.SOUL_DESTROYER);
		this.name = "Soul Destroyer";
		this.color = ChatColor.LIGHT_PURPLE;
		
		this.addBiomes(Biome.NETHER_WASTES, Biome.SOUL_SAND_VALLEY, Biome.CRIMSON_FOREST, Biome.WARPED_FOREST,
				Biome.BASALT_DELTAS);
		
		this.addPotionEffect(CustomPotionEffect.FIRE_RESISTANCE);
		this.helmet = new ItemStack (Material.WITHER_SKELETON_SKULL, 1);
		this.chestplate = new DarkNetheriteChestplate (this.color);
		this.boots = new LightweightNetheriteBoots (this.color);
		this.weapon = new PerpetualTorment (this.color);
	}
}
