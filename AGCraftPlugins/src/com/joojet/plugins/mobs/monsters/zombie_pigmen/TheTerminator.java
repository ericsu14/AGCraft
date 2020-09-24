package com.joojet.plugins.mobs.monsters.zombie_pigmen;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.Biome;
import org.bukkit.entity.EntityType;

import com.joojet.plugins.mobs.drops.MonsterDrop;
import com.joojet.plugins.mobs.enums.CustomPotionEffect;
import com.joojet.plugins.mobs.enums.MobFlag;
import com.joojet.plugins.mobs.enums.MonsterStat;
import com.joojet.plugins.mobs.enums.MonsterType;
import com.joojet.plugins.mobs.equipment.boots.LightweightNetheriteBoots;
import com.joojet.plugins.mobs.equipment.chest.DarkNetheriteChestplate;
import com.joojet.plugins.mobs.equipment.head.CyborgPigmanHead;
import com.joojet.plugins.mobs.equipment.leggings.DarkNetheriteLeggings;
import com.joojet.plugins.mobs.equipment.offhand.TerminatorDagger;
import com.joojet.plugins.mobs.equipment.weapons.TerminatorSword;
import com.joojet.plugins.mobs.monsters.MobEquipment;

public class TheTerminator extends MobEquipment
{
	public TheTerminator ()
	{
		super (MonsterType.THE_TERMINATOR);
		this.name = "The Terminator";
		this.color = ChatColor.DARK_RED;
		
		this.setStat(MonsterStat.HEALTH, 16.0);
		this.setStat(MonsterStat.HUNT_ON_SPAWN_RADIUS, 100.0);
		
		this.addMobFlags(MobFlag.SHOW_NAME, MobFlag.SPAWN_LIGHTNING, MobFlag.HUNT_ON_SPAWN, MobFlag.BOSS_BAR);
		this.addTargetsToHitList(EntityType.PLAYER);
		this.addBiomes(Biome.NETHER_WASTES, Biome.SOUL_SAND_VALLEY, Biome.CRIMSON_FOREST, Biome.WARPED_FOREST,
				Biome.BASALT_DELTAS);
		this.addPotionEffect(CustomPotionEffect.SPEED);
		
		this.helmet = new CyborgPigmanHead (this.color);
		this.chestplate = new DarkNetheriteChestplate (this.color);
		this.leggings = new DarkNetheriteLeggings (this.color);
		this.boots = new LightweightNetheriteBoots (this.color);
		this.weapon = new TerminatorSword (this.color);
		this.offhand = new TerminatorDagger (this.color);
		
		this.addMonsterDrops(new MonsterDrop (Material.DIAMOND, 0.35, 1, 3),
				new MonsterDrop (Material.ENCHANTED_GOLDEN_APPLE, 1.00, 1, 1));
		
		this.setStat(MonsterStat.EXPERIENCE, 60.0);
	}
}
