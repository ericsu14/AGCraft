package com.joojet.plugins.mobs.monsters.zombie.beatthebruins;

import org.bukkit.block.Biome;
import org.bukkit.entity.EntityType;

import com.joojet.plugins.mobs.enums.MonsterStat;
import com.joojet.plugins.mobs.monsters.MountedMob;
import com.joojet.plugins.mobs.monsters.giant.beatthebruins.GiantBruin;

public class GiantBruinTamer extends UCLAJock
{
	public GiantBruinTamer ()
	{
		super();
		this.name = "Giant Bruin Tamer";
		this.addBiomes(Biome.PLAINS, Biome.DESERT, Biome.DESERT_HILLS, Biome.DESERT_LAKES,
				Biome.BADLANDS, Biome.BADLANDS_PLATEAU, Biome.ERODED_BADLANDS, Biome.SAVANNA_PLATEAU,
				Biome.SNOWY_TUNDRA, Biome.FROZEN_RIVER, Biome.SNOWY_BEACH,
				Biome.GRAVELLY_MOUNTAINS, Biome.MOUNTAINS, Biome.STONE_SHORE,
				Biome.SUNFLOWER_PLAINS, Biome.SWAMP, Biome.JUNGLE_EDGE, Biome.MODIFIED_JUNGLE_EDGE,
				Biome.BEACH, Biome.ERODED_BADLANDS, Biome.SHATTERED_SAVANNA_PLATEAU,
				Biome.SHATTERED_SAVANNA, Biome.MODIFIED_GRAVELLY_MOUNTAINS);
		this.mount = new MountedMob (EntityType.GIANT, new GiantBruin());
		this.setStat(MonsterStat.Y_LIMIT, 55.0);
		this.setStat(MonsterStat.HEALTH, 30.0);
	}
}
