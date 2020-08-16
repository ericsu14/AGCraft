package com.joojet.plugins.mobs.util;

import org.bukkit.entity.EntityType;

import net.minecraft.server.v1_16_R2.EntityBee;
import net.minecraft.server.v1_16_R2.EntityBlaze;
import net.minecraft.server.v1_16_R2.EntityCat;
import net.minecraft.server.v1_16_R2.EntityCaveSpider;
import net.minecraft.server.v1_16_R2.EntityChicken;
import net.minecraft.server.v1_16_R2.EntityCow;
import net.minecraft.server.v1_16_R2.EntityCreeper;
import net.minecraft.server.v1_16_R2.EntityDrowned;
import net.minecraft.server.v1_16_R2.EntityEnderman;
import net.minecraft.server.v1_16_R2.EntityEndermite;
import net.minecraft.server.v1_16_R2.EntityEvoker;
import net.minecraft.server.v1_16_R2.EntityFox;
import net.minecraft.server.v1_16_R2.EntityGhast;
import net.minecraft.server.v1_16_R2.EntityHoglin;
import net.minecraft.server.v1_16_R2.EntityMagmaCube;
import net.minecraft.server.v1_16_R2.EntityOcelot;
import net.minecraft.server.v1_16_R2.EntityPhantom;
import net.minecraft.server.v1_16_R2.EntityPig;
import net.minecraft.server.v1_16_R2.EntityPiglin;
import net.minecraft.server.v1_16_R2.EntityPillager;
import net.minecraft.server.v1_16_R2.EntityPlayer;
import net.minecraft.server.v1_16_R2.EntityPolarBear;
import net.minecraft.server.v1_16_R2.EntityRabbit;
import net.minecraft.server.v1_16_R2.EntityRavager;
import net.minecraft.server.v1_16_R2.EntitySilverfish;
import net.minecraft.server.v1_16_R2.EntitySkeleton;
import net.minecraft.server.v1_16_R2.EntitySkeletonStray;
import net.minecraft.server.v1_16_R2.EntityZombie;
import net.minecraft.server.v1_16_R2.EntityZombieHusk;
import net.minecraft.server.v1_16_R2.EntityZombieVillager;
import net.minecraft.server.v1_16_R2.EntitySkeletonWither;
import net.minecraft.server.v1_16_R2.EntitySlime;
import net.minecraft.server.v1_16_R2.EntitySnowman;
import net.minecraft.server.v1_16_R2.EntitySpider;
import net.minecraft.server.v1_16_R2.EntityVex;
import net.minecraft.server.v1_16_R2.EntityVillager;
import net.minecraft.server.v1_16_R2.EntityVillagerTrader;
import net.minecraft.server.v1_16_R2.EntityVindicator;
import net.minecraft.server.v1_16_R2.EntityWitch;
import net.minecraft.server.v1_16_R2.EntityWolf;
import net.minecraft.server.v1_16_R2.EntityPigZombie;
import net.minecraft.server.v1_16_R2.EntityIllagerIllusioner;
import net.minecraft.server.v1_16_R2.EntityIronGolem;
import net.minecraft.server.v1_16_R2.EntityGiantZombie;
import net.minecraft.server.v1_16_R2.EntityGuardian;
import net.minecraft.server.v1_16_R2.EntityGuardianElder;

public class ConvertEntity 
{
	/** Converts an entity type enum to its equivalent NMS entity class type */
	public static Class<?> getNMSEntity (EntityType type)
	{
		switch (type)
		{
			case ZOMBIE:
				return EntityZombie.class;
			case SKELETON:
				return EntitySkeleton.class;
			case WITHER_SKELETON:
				return EntitySkeletonWither.class;
			case STRAY:
				return EntitySkeletonStray.class;
			case HUSK:
				return EntityZombieHusk.class;
			case ENDERMAN:
				return EntityEnderman.class;
			case POLAR_BEAR:
				return EntityPolarBear.class;
			case PIGLIN:
				return EntityPiglin.class;
			case ZOMBIFIED_PIGLIN:
				return EntityPigZombie.class;
			case SILVERFISH:
				return EntitySilverfish.class;
			case CHICKEN:
				return EntityChicken.class;
			case CREEPER:
				return EntityCreeper.class;
			case BLAZE:
				return EntityBlaze.class;
			case BEE:
				return EntityBee.class;
			case EVOKER:
				return EntityEvoker.class;
			case FOX:
				return EntityFox.class;
			case HOGLIN:
				return EntityHoglin.class;
			case GHAST:
				return EntityGhast.class;
			case SLIME:
				return EntitySlime.class;
			case ILLUSIONER:
				return EntityIllagerIllusioner.class;
			case WITCH:
				return EntityWitch.class;
			case GIANT:
				return EntityGiantZombie.class;
			case GUARDIAN:
				return EntityGuardian.class;
			case ELDER_GUARDIAN:
				return EntityGuardianElder.class;
			case PILLAGER:
				return EntityPillager.class;
			case RAVAGER:
				return EntityRavager.class;
			case IRON_GOLEM:
				return EntityIronGolem.class;
			case SNOWMAN:
				return EntitySnowman.class;
			case WOLF:
				return EntityWolf.class;
			case CAT:
				return EntityCat.class;
			case OCELOT:
				return EntityOcelot.class;
			case VILLAGER:
				return EntityVillager.class;
			case WANDERING_TRADER:
				return EntityVillagerTrader.class;
			case RABBIT:
				return EntityRabbit.class;
			case PIG:
				return EntityPig.class;
			case COW:
				return EntityCow.class;
			case SPIDER:
				return EntitySpider.class;
			case CAVE_SPIDER:
				return EntityCaveSpider.class;
			case PLAYER:
				return EntityPlayer.class;
			case MAGMA_CUBE:
				return EntityMagmaCube.class;
			case DROWNED:
				return EntityDrowned.class;
			case VEX:
				return EntityVex.class;
			case VINDICATOR:
				return EntityVindicator.class;
			case ENDERMITE:
				return EntityEndermite.class;
			case ZOMBIE_VILLAGER:
				return EntityZombieVillager.class;
			case PHANTOM:
				return EntityPhantom.class;
			default:
				break;
		}
		
		return null;
	}
}
