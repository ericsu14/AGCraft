package com.joojet.plugins.mobs.util;

import org.bukkit.entity.EntityType;

import net.minecraft.server.level.EntityPlayer;
import net.minecraft.world.entity.ambient.*;
import net.minecraft.world.entity.animal.*;
import net.minecraft.world.entity.animal.horse.*;
import net.minecraft.world.entity.boss.enderdragon.*;
import net.minecraft.world.entity.boss.wither.*;
import net.minecraft.world.entity.decoration.*;
import net.minecraft.world.entity.monster.*;
import net.minecraft.world.entity.monster.hoglin.*;
import net.minecraft.world.entity.monster.piglin.*;
import net.minecraft.world.entity.npc.*;


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
			case PIGLIN_BRUTE:
				return EntityPiglinBrute.class;
			case ARMOR_STAND:
				return EntityArmorStand.class;
			case WITHER:
				return EntityWither.class;
			case SQUID:
				return EntitySquid.class;
			case COD:
				return EntityCod.class;
			case TROPICAL_FISH:
				return EntityTropicalFish.class;
			case PUFFERFISH:
				return EntityPufferFish.class;
			case SALMON:
				return EntitySalmon.class;
			case BAT:
				return EntityBat.class;
			case PANDA:
				return EntityPanda.class;
			case SHEEP:
				return EntitySheep.class;
			case SHULKER:
				return EntityShulker.class;
			case ITEM_FRAME:
				return EntityItemFrame.class;
			case DONKEY:
				return EntityHorseDonkey.class;
			case LLAMA:
				return EntityLlama.class;
			case MULE:
				return EntityHorseMule.class;
			case TURTLE:
				return EntityTurtle.class;
			case PARROT:
				return EntityParrot.class;
			case ZOGLIN:
				return EntityZoglin.class;
			case ENDER_DRAGON:
				return EntityEnderDragon.class;
			default:
				break;
		}
		return null;
	}
}
