package com.joojet.plugins.mobs.util;

import java.util.EnumMap;
import java.util.EnumSet;

import org.bukkit.entity.EntityType;

/**
 * Contains a mapping between every mob in the game and every mob that monster is naturally hostile to
 */
public class NaturalHostileMobMappings 
{
	/** Hostile mob to entity mappings */
	public EnumMap <EntityType, EnumSet<EntityType>> mappings;
	
	/**
	 * Initializes the natural hostile mob mapping list
	 */
	public NaturalHostileMobMappings ()
	{
		this.mappings = new EnumMap<EntityType, EnumSet<EntityType>>(EntityType.class);
		// Zombie variants
		this.mapHostileEntityTargets(EntityType.ZOMBIE, EntityType.PLAYER, EntityType.VILLAGER, 
				EntityType.WANDERING_TRADER, EntityType.IRON_GOLEM, EntityType.TURTLE);
		this.mapHostileEntityTargets(EntityType.HUSK, EntityType.PLAYER, EntityType.VILLAGER, 
				EntityType.WANDERING_TRADER, EntityType.IRON_GOLEM, EntityType.TURTLE);
		this.mapHostileEntityTargets(EntityType.ZOMBIE_VILLAGER, EntityType.PLAYER, EntityType.VILLAGER, 
				EntityType.WANDERING_TRADER, EntityType.IRON_GOLEM, EntityType.TURTLE);
		// Drowned
		this.mapHostileEntityTargets(EntityType.DROWNED, EntityType.PLAYER, EntityType.VILLAGER, 
				EntityType.WANDERING_TRADER, EntityType.IRON_GOLEM, EntityType.TURTLE, EntityType.AXOLOTL);
		// Skeleton variants
		this.mapHostileEntityTargets(EntityType.SKELETON, EntityType.PLAYER, EntityType.IRON_GOLEM, EntityType.TURTLE);
		this.mapHostileEntityTargets(EntityType.STRAY, EntityType.PLAYER, EntityType.IRON_GOLEM, EntityType.TURTLE);
		// Wither skeleton targets piglin variants
		this.mapHostileEntityTargets(EntityType.WITHER_SKELETON, EntityType.PLAYER, EntityType.IRON_GOLEM, EntityType.TURTLE, 
				EntityType.PIGLIN, EntityType.PIGLIN_BRUTE);
		// Phantom
		this.mapHostileEntityTargets(EntityType.PHANTOM, EntityType.PLAYER);
		// Illager variants
		this.mapHostileEntityTargets(EntityType.PILLAGER, EntityType.PLAYER, EntityType.VILLAGER, 
				EntityType.WANDERING_TRADER, EntityType.IRON_GOLEM);
		this.mapHostileEntityTargets(EntityType.RAVAGER, EntityType.PLAYER, EntityType.VILLAGER, 
				EntityType.WANDERING_TRADER, EntityType.IRON_GOLEM);
		this.mapHostileEntityTargets(EntityType.ILLUSIONER, EntityType.PLAYER, EntityType.VILLAGER, 
				EntityType.WANDERING_TRADER, EntityType.IRON_GOLEM);
		this.mapHostileEntityTargets(EntityType.VINDICATOR, EntityType.PLAYER, EntityType.VILLAGER, 
				EntityType.WANDERING_TRADER, EntityType.IRON_GOLEM);
		this.mapHostileEntityTargets(EntityType.EVOKER, EntityType.PLAYER, EntityType.VILLAGER, 
				EntityType.WANDERING_TRADER, EntityType.IRON_GOLEM);
		// Spider variants
		this.mapHostileEntityTargets(EntityType.SPIDER, EntityType.PLAYER, EntityType.IRON_GOLEM);
		this.mapHostileEntityTargets(EntityType.CAVE_SPIDER, EntityType.PLAYER, EntityType.IRON_GOLEM);
		// Blaze
		this.mapHostileEntityTargets(EntityType.BLAZE, EntityType.PLAYER);
		// Pigmans
		this.mapHostileEntityTargets(EntityType.ZOMBIFIED_PIGLIN, EntityType.PLAYER, EntityType.VILLAGER, 
				EntityType.WANDERING_TRADER, EntityType.IRON_GOLEM, EntityType.TURTLE);
		// Guardian variants
		this.mapHostileEntityTargets(EntityType.GUARDIAN, EntityType.PLAYER, EntityType.SQUID, 
				EntityType.GLOW_SQUID);
		this.mapHostileEntityTargets(EntityType.ELDER_GUARDIAN, EntityType.PLAYER, EntityType.SQUID, 
				EntityType.GLOW_SQUID);
		// Slime variants
		this.mapHostileEntityTargets(EntityType.SLIME, EntityType.PLAYER, EntityType.IRON_GOLEM);
		this.mapHostileEntityTargets(EntityType.MAGMA_CUBE, EntityType.PLAYER, EntityType.IRON_GOLEM);
		// Silverfish variants
		this.mapHostileEntityTargets(EntityType.SILVERFISH, EntityType.PLAYER);
		this.mapHostileEntityTargets(EntityType.ENDERMITE, EntityType.PLAYER);
		// Endermen
		this.mapHostileEntityTargets(EntityType.ENDERMAN, EntityType.PLAYER, EntityType.ENDERMITE);
		// Ghast
		this.mapHostileEntityTargets(EntityType.GHAST, EntityType.PLAYER);
		// Shulker
		this.mapHostileEntityTargets(EntityType.SHULKER, EntityType.PLAYER);
		// Bee movie
		this.mapHostileEntityTargets(EntityType.BEE, EntityType.PLAYER);
		// Wolves
		this.mapHostileEntityTargets(EntityType.WOLF, EntityType.PLAYER, EntityType.SKELETON, EntityType.WITHER_SKELETON, EntityType.STRAY);
		// Polar bear
		this.mapHostileEntityTargets(EntityType.POLAR_BEAR, EntityType.PLAYER, EntityType.FOX);
		// Golem variants
		this.mapHostileEntityTargets(EntityType.IRON_GOLEM, EntityType.ZOMBIE, EntityType.SKELETON, EntityType.PILLAGER,
				EntityType.SPIDER, EntityType.CAVE_SPIDER, EntityType.EVOKER, EntityType.STRAY,
				EntityType.HUSK, EntityType.BLAZE, EntityType.PIGLIN, EntityType.DROWNED,
				EntityType.ENDERMAN, EntityType.ILLUSIONER, EntityType.POLAR_BEAR, EntityType.VINDICATOR,
				EntityType.RAVAGER, EntityType.WITHER_SKELETON, EntityType.WITCH,
				EntityType.GHAST, EntityType.GUARDIAN, EntityType.ELDER_GUARDIAN,
				EntityType.SLIME, EntityType.MAGMA_CUBE, EntityType.ENDERMITE, EntityType.PHANTOM,
				EntityType.ZOMBIFIED_PIGLIN, EntityType.WITHER, EntityType.GIANT, EntityType.HOGLIN,
				EntityType.VEX, EntityType.PIGLIN_BRUTE, EntityType.ZOGLIN, EntityType.ENDER_DRAGON,
				EntityType.ZOMBIE_VILLAGER, EntityType.SHULKER);
		this.mapHostileEntityTargets(EntityType.SNOWMAN, EntityType.ZOMBIE, EntityType.SKELETON, EntityType.PILLAGER,
				EntityType.SPIDER, EntityType.CAVE_SPIDER, EntityType.EVOKER, EntityType.STRAY,
				EntityType.HUSK, EntityType.BLAZE, EntityType.PIGLIN, EntityType.DROWNED,
				EntityType.ENDERMAN, EntityType.ILLUSIONER, EntityType.POLAR_BEAR, EntityType.VINDICATOR,
				EntityType.RAVAGER, EntityType.WITHER_SKELETON, EntityType.WITCH,
				EntityType.GHAST, EntityType.GUARDIAN, EntityType.ELDER_GUARDIAN,
				EntityType.SLIME, EntityType.MAGMA_CUBE, EntityType.ENDERMITE, EntityType.PHANTOM,
				EntityType.ZOMBIFIED_PIGLIN, EntityType.WITHER, EntityType.GIANT, EntityType.HOGLIN,
				EntityType.VEX, EntityType.PIGLIN_BRUTE, EntityType.ZOGLIN, EntityType.ENDER_DRAGON,
				EntityType.ZOMBIE_VILLAGER, EntityType.SHULKER);
		// Foxes
		this.mapHostileEntityTargets(EntityType.FOX, EntityType.CHICKEN, EntityType.RABBIT, EntityType.TROPICAL_FISH, EntityType.SALMON, 
				EntityType.COD, EntityType.PUFFERFISH);
		// kitties
		this.mapHostileEntityTargets(EntityType.CAT, EntityType.CHICKEN, EntityType.TURTLE);
		this.mapHostileEntityTargets(EntityType.OCELOT, EntityType.CHICKEN, EntityType.TURTLE);
	}
	
	/**
	 * Maps a hostile entity to a list of natural targets
	 * @param entity The entity being mapped
	 * @param targets List of targets the entity is naturally hostile to
	 */
	public void mapHostileEntityTargets (EntityType entity, EntityType ...targets)
	{
		EnumSet <EntityType> targetMappings = EnumSet.noneOf(EntityType.class);
		for (EntityType target : targets)
		{
			targetMappings.add(target);
		}
		this.mappings.put(entity, targetMappings);
	}
	
	/**
	 * Returns true if the entity is naturally hostile to the target
	 * @param entity Entity being checked
	 * @param target The target the entity is set to hunt
	 * @return True if the entity is naturally hostile to the target. False otherwise
	 */
	public boolean isNaturallyHostile (EntityType entity, EntityType target)
	{
		return (this.mappings.containsKey(entity) ? this.mappings.get(entity).contains(target) : false);
	}
}
