package com.joojet.plugins.mobs.monsters.factions;

import org.bukkit.entity.EntityType;

import com.joojet.plugins.mobs.enums.Faction;
import com.joojet.plugins.mobs.enums.MonsterType;
import com.joojet.plugins.mobs.monsters.MobEquipment;

public class USCFaction extends MobEquipment
{
	public USCFaction (MonsterType type)
	{
		super (type);
		this.addFactions(Faction.USC);
		this.addRivalFactions(Faction.UCLA, Faction.DOOM_GUY);
		this.addTargetsToHitList(EntityType.ZOMBIE, EntityType.SKELETON, EntityType.PILLAGER,
				EntityType.SPIDER, EntityType.CAVE_SPIDER, EntityType.EVOKER, EntityType.STRAY,
				EntityType.HUSK, EntityType.BLAZE, EntityType.PIGLIN, EntityType.DROWNED,
				EntityType.ENDERMAN, EntityType.ILLUSIONER, EntityType.POLAR_BEAR, EntityType.VINDICATOR,
				EntityType.RAVAGER, EntityType.WITHER_SKELETON, EntityType.WITCH,
				EntityType.GHAST, EntityType.GUARDIAN, EntityType.ELDER_GUARDIAN,
				EntityType.SLIME, EntityType.MAGMA_CUBE, EntityType.ENDERMITE, EntityType.PHANTOM);
		
		this.addEntitiesToIgnoreList(EntityType.PLAYER, EntityType.WOLF, EntityType.CAT,
				EntityType.IRON_GOLEM, EntityType.SNOWMAN, EntityType.DOLPHIN, EntityType.VILLAGER,
				EntityType.WANDERING_TRADER, EntityType.CREEPER, EntityType.PANDA);
	}
}
