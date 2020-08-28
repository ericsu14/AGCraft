package com.joojet.plugins.mobs.util;

import org.bukkit.entity.EntityType;
import org.bukkit.entity.*;

public class ConvertEntity 
{
	/** Converts an  type enum to its equivalent Bukkit Entity  class type */
	public static Class<?> getNMSEntity (EntityType type)
	{
		switch (type)
		{
			case ZOMBIE:
				return Zombie.class;
			case SKELETON:
				return Skeleton.class;
			case WITHER_SKELETON:
				return WitherSkeleton.class;
			case STRAY:
				return Stray.class;
			case HUSK:
				return Husk.class;
			case ENDERMAN:
				return Enderman.class;
			case POLAR_BEAR:
				return PolarBear.class;
			case PIGLIN:
				return Piglin.class;
			case ZOMBIFIED_PIGLIN:
				return PigZombie.class;
			case SILVERFISH:
				return Silverfish.class;
			case CHICKEN:
				return Chicken.class;
			case CREEPER:
				return Creeper.class;
			case BLAZE:
				return Blaze.class;
			case BEE:
				return Bee.class;
			case EVOKER:
				return Evoker.class;
			case FOX:
				return Fox.class;
			case HOGLIN:
				return Hoglin.class;
			case GHAST:
				return Ghast.class;
			case SLIME:
				return Slime.class;
			case ILLUSIONER:
				return Illusioner.class;
			case WITCH:
				return Witch.class;
			case GIANT:
				return Giant.class;
			case GUARDIAN:
				return Guardian.class;
			case ELDER_GUARDIAN:
				return ElderGuardian.class;
			case PILLAGER:
				return Pillager.class;
			case RAVAGER:
				return Ravager.class;
			case IRON_GOLEM:
				return IronGolem.class;
			case SNOWMAN:
				return Snowman.class;
			case WOLF:
				return Wolf.class;
			case CAT:
				return Cat.class;
			case OCELOT:
				return Ocelot.class;
			case VILLAGER:
				return Villager.class;
			case WANDERING_TRADER:
				return WanderingTrader.class;
			case RABBIT:
				return Rabbit.class;
			case PIG:
				return Pig.class;
			case COW:
				return Cow.class;
			case SPIDER:
				return Spider.class;
			case CAVE_SPIDER:
				return CaveSpider.class;
			case PLAYER:
				return Player.class;
			case MAGMA_CUBE:
				return MagmaCube.class;
			case DROWNED:
				return Drowned.class;
			case VEX:
				return Vex.class;
			case VINDICATOR:
				return Vindicator.class;
			case ENDERMITE:
				return Endermite.class;
			case ZOMBIE_VILLAGER:
				return ZombieVillager.class;
			case PHANTOM:
				return Phantom.class;
			case PIGLIN_BRUTE:
				return PiglinBrute.class;
			case ARMOR_STAND:
				return ArmorStand.class;
			case WITHER:
				return Wither.class;
			default:
				break;
		}
		
		return null;
	}
}
