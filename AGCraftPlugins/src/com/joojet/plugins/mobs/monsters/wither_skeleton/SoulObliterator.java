package com.joojet.plugins.mobs.monsters.wither_skeleton;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.Biome;
import org.bukkit.entity.EntityType;

import com.joojet.plugins.mobs.drops.MonsterDrop;
import com.joojet.plugins.mobs.enums.CustomPotionEffect;
import com.joojet.plugins.mobs.enums.Faction;
import com.joojet.plugins.mobs.enums.MobFlag;
import com.joojet.plugins.mobs.enums.MonsterStat;
import com.joojet.plugins.mobs.enums.MonsterType;
import com.joojet.plugins.mobs.equipment.boots.LightweightDiamondBoots;
import com.joojet.plugins.mobs.equipment.chest.ReinforcedDiamondChestplate;
import com.joojet.plugins.mobs.equipment.head.ReinforcedDiamondHelmet;
import com.joojet.plugins.mobs.equipment.leggings.ReinforcedDiamondLeggings;
import com.joojet.plugins.mobs.equipment.offhand.EnhancedWitheringArrow;
import com.joojet.plugins.mobs.equipment.weapons.AngelOfDeath;
import com.joojet.plugins.mobs.monsters.factions.classifications.MythicMob;


public class SoulObliterator extends MythicMob
{
	public SoulObliterator ()
	{
		super (MonsterType.SOUL_OBLITERATOR);
		this.name = "Soul Obliterator";
		this.color = ChatColor.DARK_RED;
		this.setStat(MonsterStat.HEALTH, 40.0);
		this.addMobFlags(MobFlag.SHOW_NAME, MobFlag.BOSS_BAR,
				MobFlag.IGNORE_NON_FACTION_ENTITIES);
		
		this.setDropRates(0.15f, 0.03f, 0.03f, 0.03f, 0.05f, 0.15f);
		
		this.addBiomes(Biome.NETHER_WASTES, Biome.SOUL_SAND_VALLEY, Biome.CRIMSON_FOREST, Biome.WARPED_FOREST,
				Biome.BASALT_DELTAS);
		
		this.addFactions(Faction.NETHER);
		this.addRivalFactions(Faction.DOOM_GUY, Faction.USC, Faction.UCLA);
		this.addTargetsToHitList(EntityType.WITHER_SKELETON, EntityType.SKELETON, EntityType.ZOMBIE,
				EntityType.HUSK, EntityType.STRAY);
		
		this.addPotionEffect(CustomPotionEffect.FIRE_RESISTANCE);
		this.helmet = new ReinforcedDiamondHelmet (this.color);
		this.chestplate = new ReinforcedDiamondChestplate (this.color);
		this.leggings = new ReinforcedDiamondLeggings (this.color);
		this.boots = new LightweightDiamondBoots (this.color);
		this.weapon = new AngelOfDeath (this.color);
		this.offhand = new EnhancedWitheringArrow (this.color);
		
		this.addMonsterDrops(new MonsterDrop (Material.ENCHANTED_GOLDEN_APPLE, 0.20, 1, 2),
				new MonsterDrop (Material.WITHER_SKELETON_SKULL, 0.03, 1, 1),
				new MonsterDrop (Material.DIAMOND, 0.10, 1, 3));
		
		this.setStat(MonsterStat.EXPERIENCE, 45.0);
		this.setStat(MonsterStat.ARROW_CRITICAL_CHANCE, 0.30);
		this.setStat(MonsterStat.ARROW_PIERCING_CHANCE, 0.75);
	}
}
