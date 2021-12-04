package com.joojet.plugins.mobs.monsters.factions;

import org.bukkit.block.Biome;

import com.joojet.plugins.mobs.enums.MonsterType;
import com.joojet.plugins.mobs.monsters.MobEquipment;

/** Extend from this class when you want your monster to spawn only in the overworld */
public class OverworldMob extends MobEquipment
{

	public OverworldMob(MonsterType mobType) 
	{
		super(mobType);
		
		// Add every biome contained in the overworld.
		this.addBiomes(Biome.ICE_SPIKES, Biome.SNOWY_TAIGA, Biome.FROZEN_RIVER, Biome.SNOWY_BEACH, Biome.TAIGA,
				Biome.PLAINS, Biome.SUNFLOWER_PLAINS, Biome.FOREST, Biome.FLOWER_FOREST,
				Biome.BIRCH_FOREST, Biome.DARK_FOREST, Biome.SWAMP, Biome.JUNGLE,
				Biome.BAMBOO_JUNGLE, Biome.RIVER,
				Biome.BEACH, Biome.DESERT, Biome.SAVANNA,
				Biome.BADLANDS, Biome.ERODED_BADLANDS, Biome.SAVANNA_PLATEAU, Biome.WARM_OCEAN,
				Biome.LUKEWARM_OCEAN, Biome.DEEP_LUKEWARM_OCEAN, Biome.OCEAN,
				Biome.DEEP_OCEAN, Biome.DEEP_COLD_OCEAN, Biome.COLD_OCEAN,
				Biome.FROZEN_OCEAN, Biome.DEEP_FROZEN_OCEAN);
	}
	
}
