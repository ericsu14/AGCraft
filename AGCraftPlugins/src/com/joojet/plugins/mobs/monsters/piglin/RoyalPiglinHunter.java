package com.joojet.plugins.mobs.monsters.piglin;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.Biome;

import com.joojet.plugins.mobs.drops.MonsterDrop;
import com.joojet.plugins.mobs.drops.SummoningScrollDrop;
import com.joojet.plugins.mobs.enums.CustomPotionEffect;
import com.joojet.plugins.mobs.enums.MonsterStat;
import com.joojet.plugins.mobs.enums.MonsterType;
import com.joojet.plugins.mobs.enums.SummonTypes;
import com.joojet.plugins.mobs.equipment.boots.LightweightNetheriteBoots;
import com.joojet.plugins.mobs.equipment.chest.DarkNetheriteChestplate;
import com.joojet.plugins.mobs.equipment.head.RoyalPiglinHunterHead;
import com.joojet.plugins.mobs.equipment.leggings.DarkNetheriteLeggings;
import com.joojet.plugins.mobs.equipment.weapons.RoyalPiglinCrossbow;
import com.joojet.plugins.mobs.monsters.factions.classifications.MythicMob;
import com.joojet.plugins.music.enums.MusicType;

public class RoyalPiglinHunter extends MythicMob
{
	public RoyalPiglinHunter ()
	{
		super (MonsterType.ROYAL_PIGLIN_HUNTER);
		this.color = ChatColor.DARK_RED;
		
		this.setStat(MonsterStat.HEALTH, 30.0);
		this.setStat(MonsterStat.BASE_ARMOR, 8.0);
		this.setStat(MonsterStat.BASE_ARMOR_TOUGHNESS, 6.0);
		this.setStat(MonsterStat.BASE_ARROW_DAMAGE, 48.0);
		this.setStat(MonsterStat.ARROW_CRITICAL_CHANCE, 60.0);
		this.setStat(MonsterStat.ARROW_PIERCING_CHANCE, 100.0);
		
		this.addMonsterDrops(new MonsterDrop (Material.ENCHANTED_GOLDEN_APPLE, 0.20, 1, 3),
				new MonsterDrop (Material.DIAMOND, 1.00, 2, 4),
				new MonsterDrop (Material.GOLD_BLOCK, 1.00, 1, 3),
				new SummoningScrollDrop (SummonTypes.JOHNNY_RUSNAK, 0.15),
				new SummoningScrollDrop (SummonTypes.FROLF, 0.15),
				new MonsterDrop (Material.EMERALD, 0.50, 1, 7),
				new SummoningScrollDrop (SummonTypes.ETERNAL_TROJAN_ARCHER, 0.10),
				new SummoningScrollDrop (SummonTypes.JOHN_JAE, 0.07));
		
		this.setStat(MonsterStat.EXPERIENCE, 500.0);
		
		this.addBiomes(Biome.NETHER_WASTES, Biome.SOUL_SAND_VALLEY, Biome.CRIMSON_FOREST, Biome.WARPED_FOREST,
				Biome.BASALT_DELTAS);
		
		this.addPotionEffect(CustomPotionEffect.FULL_HEALING, CustomPotionEffect.SPEED);
		
		this.helmet = new RoyalPiglinHunterHead (this.color);
		this.chestplate = new DarkNetheriteChestplate (this.color);
		this.leggings = new DarkNetheriteLeggings (this.color);
		this.boots = new LightweightNetheriteBoots (this.color);
		this.weapon = new RoyalPiglinCrossbow (this.color);
		
		this.bossTheme = MusicType.GORO_THEME;
	}
}
