package com.joojet.plugins.mobs.monsters.factions;

import java.util.List;

import org.bukkit.block.Biome;

import com.joojet.plugins.mobs.enums.MonsterType;
import com.joojet.plugins.mobs.monsters.MobEquipment;
import com.joojet.plugins.mobs.skills.AbstractSkill;

/** Extend from this class when you want your monster to spawn only in the overworld */
public class OverworldMob extends MobEquipment
{

	public OverworldMob(MonsterType mobType) 
	{
		super(mobType);
		
		// Add every biome contained in the overworld.
		this.addBiomes(Biome.SNOWY_TUNDRA, Biome.ICE_SPIKES, Biome.SNOWY_TAIGA,
				Biome.SNOWY_TAIGA_MOUNTAINS, Biome.FROZEN_RIVER, Biome.SNOWY_BEACH,
				Biome.MOUNTAINS, Biome.GRAVELLY_MOUNTAINS, Biome.WOODED_MOUNTAINS, 
				Biome.MODIFIED_GRAVELLY_MOUNTAINS, Biome.TAIGA, Biome.TAIGA_MOUNTAINS,
				Biome.GIANT_TREE_TAIGA, Biome.GIANT_SPRUCE_TAIGA, Biome.STONE_SHORE,
				Biome.PLAINS, Biome.SUNFLOWER_PLAINS, Biome.FOREST, Biome.FLOWER_FOREST,
				Biome.BIRCH_FOREST, Biome.TALL_BIRCH_FOREST, Biome.DARK_FOREST,
				Biome.DARK_FOREST_HILLS, Biome.SWAMP, Biome.SWAMP_HILLS, Biome.JUNGLE,
				Biome.MODIFIED_JUNGLE, Biome.JUNGLE_EDGE, Biome.MODIFIED_JUNGLE_EDGE,
				Biome.BAMBOO_JUNGLE, Biome.BAMBOO_JUNGLE_HILLS, Biome.RIVER,
				Biome.BEACH, Biome.DESERT, Biome.DESERT_LAKES, Biome.SAVANNA,
				Biome.SHATTERED_SAVANNA, Biome.SHATTERED_SAVANNA_PLATEAU, 
				Biome.BADLANDS, Biome.BADLANDS_PLATEAU, Biome.ERODED_BADLANDS,
				Biome.WOODED_BADLANDS_PLATEAU, Biome.WOODED_HILLS,
				Biome.MODIFIED_WOODED_BADLANDS_PLATEAU, Biome.MODIFIED_BADLANDS_PLATEAU,
				Biome.BADLANDS_PLATEAU, Biome.SAVANNA_PLATEAU, Biome.WARM_OCEAN,
				Biome.LUKEWARM_OCEAN, Biome.DEEP_LUKEWARM_OCEAN, Biome.OCEAN,
				Biome.DEEP_OCEAN, Biome.DEEP_COLD_OCEAN, Biome.COLD_OCEAN,
				Biome.FROZEN_OCEAN, Biome.DEEP_FROZEN_OCEAN, Biome.BAMBOO_JUNGLE_HILLS,
				Biome.BIRCH_FOREST_HILLS, Biome.DARK_FOREST_HILLS, Biome.DESERT_HILLS,
				Biome.GIANT_SPRUCE_TAIGA_HILLS, Biome.JUNGLE_HILLS, Biome.SNOWY_TAIGA_HILLS,
				Biome.SWAMP_HILLS, Biome.TAIGA_HILLS, Biome.WOODED_HILLS, Biome.TALL_BIRCH_HILLS,
				Biome.MOUNTAIN_EDGE, Biome.DEEP_WARM_OCEAN);
	}

	@Override
	public void loadCustomSkills(List<AbstractSkill> skills) 
	{
		
	}
	
}
