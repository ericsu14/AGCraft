package com.joojet.plugins.mobs.monsters.piglin;

import org.bukkit.ChatColor;
import org.bukkit.block.Biome;

import com.joojet.plugins.mobs.enums.MobFlag;
import com.joojet.plugins.mobs.enums.MonsterStat;
import com.joojet.plugins.mobs.enums.MonsterType;
import com.joojet.plugins.mobs.equipment.chest.DarkNetheriteChestplate;
import com.joojet.plugins.mobs.equipment.head.PiglinCaptainHead;
import com.joojet.plugins.mobs.equipment.weapons.PiglinAxe;
import com.joojet.plugins.mobs.monsters.factions.classifications.LegendaryMob;
import com.joojet.plugins.music.enums.MusicType;

public class PiglinCaptain extends LegendaryMob 
{
	public PiglinCaptain ()
	{
		super (MonsterType.PIGLIN_CAPTAIN);
		this.name = "Piglin Captain";
		this.color = ChatColor.GOLD;
		this.setStat(MonsterStat.HEALTH, 40.0);
		
		this.addMobFlags(MobFlag.SHOW_NAME, MobFlag.BOSS_BAR);
		
		this.addBiomes(Biome.NETHER_WASTES, Biome.SOUL_SAND_VALLEY, Biome.CRIMSON_FOREST, Biome.WARPED_FOREST,
				Biome.BASALT_DELTAS);
		
		this.helmet = new PiglinCaptainHead (this.color);
		this.weapon = new PiglinAxe (this.color);
		this.chestplate = new DarkNetheriteChestplate (this.color);
		
		this.setStat(MonsterStat.EXPERIENCE, 45.0);
		this.bossTheme = MusicType.KUZE_THEME;
	}
}
