package com.joojet.plugins.mobs.allies.factions;

import org.bukkit.block.Biome;
import org.bukkit.entity.EntityType;

import com.joojet.plugins.mobs.enums.Faction;
import com.joojet.plugins.mobs.enums.MonsterType;
import com.joojet.plugins.mobs.monsters.MobEquipment;

/** Represents a generic allied mob that has a preset ignore list. */
public abstract class AlliedMob extends MobEquipment
{
	public AlliedMob (MonsterType type)
	{
		super (type);
		this.addBiomes(Biome.THE_VOID);
		this.addEntitiesToIgnoreList(EntityType.PLAYER, EntityType.HORSE,
				EntityType.WOLF, EntityType.SNOWMAN, EntityType.IRON_GOLEM,
				EntityType.CAT, EntityType.FOX, EntityType.OCELOT, EntityType.BEE,
				EntityType.OCELOT, EntityType.VILLAGER, EntityType.WANDERING_TRADER, EntityType.DOLPHIN, EntityType.LLAMA);
		this.addFactions(Faction.ALLIES);
		this.addRivalFactions(Faction.UCLA, Faction.DOOM_GUY, Faction.NETHER, Faction.PHANTOM);
		this.addTargetsToHitList(EntityType.ZOMBIE, EntityType.SKELETON, EntityType.PILLAGER,
				EntityType.SPIDER, EntityType.CAVE_SPIDER, EntityType.EVOKER, EntityType.STRAY,
				EntityType.HUSK, EntityType.BLAZE, EntityType.PIGLIN, EntityType.DROWNED,
				EntityType.ENDERMAN, EntityType.ILLUSIONER, EntityType.POLAR_BEAR, EntityType.VINDICATOR,
				EntityType.RAVAGER, EntityType.WITHER_SKELETON, EntityType.WITCH,
				EntityType.GHAST, EntityType.GUARDIAN, EntityType.ELDER_GUARDIAN,
				EntityType.SLIME, EntityType.MAGMA_CUBE, EntityType.ENDERMITE, EntityType.PHANTOM,
				EntityType.ZOMBIFIED_PIGLIN, EntityType.WITHER, EntityType.GIANT, EntityType.HOGLIN,
				EntityType.VEX, EntityType.PIGLIN_BRUTE, EntityType.ZOGLIN, EntityType.ENDER_DRAGON,
				EntityType.ZOMBIE_VILLAGER);
	}
}
