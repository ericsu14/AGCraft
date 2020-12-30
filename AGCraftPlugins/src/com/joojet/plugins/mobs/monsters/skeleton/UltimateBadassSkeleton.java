package com.joojet.plugins.mobs.monsters.skeleton;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.Biome;

import com.joojet.plugins.mobs.drops.MonsterDrop;
import com.joojet.plugins.mobs.enums.CustomPotionEffect;
import com.joojet.plugins.mobs.enums.MobFlag;
import com.joojet.plugins.mobs.enums.MonsterStat;
import com.joojet.plugins.mobs.enums.MonsterType;
import com.joojet.plugins.mobs.equipment.boots.LightweightNetheriteBoots;
import com.joojet.plugins.mobs.equipment.chest.DarkNetheriteChestplate;
import com.joojet.plugins.mobs.equipment.head.AGSpotted;
import com.joojet.plugins.mobs.equipment.leggings.DarkNetheriteLeggings;
import com.joojet.plugins.mobs.equipment.offhand.ThanosArrow;
import com.joojet.plugins.mobs.equipment.weapons.SpiritualFantasy;
import com.joojet.plugins.mobs.monsters.factions.classifications.LegendaryMob;
import com.joojet.plugins.music.enums.MusicType;

public class UltimateBadassSkeleton extends LegendaryMob
{
	public UltimateBadassSkeleton ()
	{
		super (MonsterType.ULTIMATE_BADASS_SKELETON);
		this.name = "#agspotted";
		this.color = ChatColor.GOLD;
		this.addMobFlags(MobFlag.SHOW_NAME, MobFlag.BOSS_BAR);
		this.setStat(MonsterStat.HEALTH, 16.0);
		this.setStat(MonsterStat.ARROW_CRITICAL_CHANCE, 0.40);
		this.setStat(MonsterStat.ARROW_PIERCING_CHANCE, 0.75);
		
		this.addBiomes(Biome.THE_VOID);
		this.addPotionEffect(CustomPotionEffect.SPEED);
		
		// Weapon
		this.weapon = new SpiritualFantasy (this.color);
		// Offhand
		this.offhand = new ThanosArrow (this.color);
		// Helmet
		this.helmet = new AGSpotted (this.color);
		// Chestplate
		this.chestplate = new DarkNetheriteChestplate (this.color);
		// Leggings
		this.leggings = new DarkNetheriteLeggings (this.color);
		// Boots
		this.boots = new LightweightNetheriteBoots (this.color);
		
		this.addMonsterDrops(new MonsterDrop (Material.DIAMOND, 0.05, 1, 2),
				new MonsterDrop (Material.ENCHANTED_GOLDEN_APPLE, 0.30, 1, 1),
				new MonsterDrop (Material.GOLDEN_CARROT, 0.075, 3, 16));
		
		this.setStat(MonsterStat.EXPERIENCE, 50.0);
		this.bossTheme = MusicType.HAIKYUU;
	}
}
