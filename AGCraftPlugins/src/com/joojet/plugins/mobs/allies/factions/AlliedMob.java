package com.joojet.plugins.mobs.allies.factions;

import org.bukkit.block.Biome;
import org.bukkit.entity.EntityType;

import com.joojet.plugins.mobs.enums.MonsterType;
import com.joojet.plugins.mobs.monsters.MobEquipment;

/** Represents a generic allied mob that has a pre-computed ignore list
 *  and not in any faction. */
public abstract class AlliedMob extends MobEquipment
{
	public AlliedMob (MonsterType type)
	{
		super (type);
		this.addBiomes(Biome.THE_VOID);
		this.addEntitiesToIgnoreList(EntityType.PLAYER, EntityType.HORSE,
				EntityType.WOLF, EntityType.SNOWMAN, EntityType.IRON_GOLEM,
				EntityType.CAT, EntityType.FOX, EntityType.OCELOT, EntityType.BEE,
				EntityType.OCELOT);
	}
}
