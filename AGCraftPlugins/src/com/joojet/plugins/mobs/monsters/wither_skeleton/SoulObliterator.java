package com.joojet.plugins.mobs.monsters.wither_skeleton;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.Biome;
import org.bukkit.entity.EntityType;
import org.bukkit.inventory.ItemStack;

import com.joojet.plugins.mobs.enums.CustomPotionEffect;
import com.joojet.plugins.mobs.enums.Faction;
import com.joojet.plugins.mobs.enums.MobFlag;
import com.joojet.plugins.mobs.enums.MonsterType;
import com.joojet.plugins.mobs.equipment.boots.LightweightDiamondBoots;
import com.joojet.plugins.mobs.equipment.chest.ReinforcedDiamondChestplate;
import com.joojet.plugins.mobs.equipment.offhand.EnhancedWitheringArrow;
import com.joojet.plugins.mobs.equipment.weapons.AngelOfDeath;
import com.joojet.plugins.mobs.monsters.MobEquipment;


public class SoulObliterator extends MobEquipment
{
	public SoulObliterator ()
	{
		super (MonsterType.SOUL_OBLITERATOR);
		this.name = "Soul Obliterator";
		this.color = ChatColor.GOLD;
		this.health = 16;
		this.addMobFlags(MobFlag.SHOW_NAME);
		
		this.addBiomes(Biome.NETHER_WASTES, Biome.SOUL_SAND_VALLEY, Biome.CRIMSON_FOREST, Biome.WARPED_FOREST,
				Biome.BASALT_DELTAS);
		
		this.addFactions(Faction.NETHER);
		this.addRivalFactions(Faction.DOOM_GUY);
		this.addTargetsToHitList(EntityType.WITHER_SKELETON);
		
		this.addPotionEffect(CustomPotionEffect.FIRE_RESISTANCE);
		this.helmet = new ItemStack (Material.WITHER_SKELETON_SKULL, 1);
		this.chestplate = new ReinforcedDiamondChestplate (this.color);
		this.boots = new LightweightDiamondBoots (this.color);
		this.weapon = new AngelOfDeath (this.color);
		this.offhand = new EnhancedWitheringArrow (this.color);
	}
}
