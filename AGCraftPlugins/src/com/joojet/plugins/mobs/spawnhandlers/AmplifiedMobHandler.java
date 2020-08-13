package com.joojet.plugins.mobs.spawnhandlers;

import org.bukkit.block.Biome;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.entity.CreatureSpawnEvent.SpawnReason;

import com.joojet.plugins.agcraft.main.AGCraftPlugin;
import com.joojet.plugins.mobs.allies.golem.GolemTypes;
import com.joojet.plugins.mobs.allies.snowman.SnowmanTypes;
import com.joojet.plugins.mobs.allies.wolf.WolfTypes;
import com.joojet.plugins.mobs.interfaces.AmplifiedSpawnHandler;
import com.joojet.plugins.mobs.monsters.MobEquipment;
import com.joojet.plugins.mobs.monsters.husk.HuskTypes;
import com.joojet.plugins.mobs.monsters.piglin.PiglinTypes;
import com.joojet.plugins.mobs.monsters.skeleton.SkeletonTypes;
import com.joojet.plugins.mobs.monsters.spider.SpiderTypes;
import com.joojet.plugins.mobs.monsters.wither_skeleton.WitherSkeletonTypes;
import com.joojet.plugins.mobs.monsters.zombie.ZombieTypes;
import com.joojet.plugins.mobs.monsters.zombie_pigmen.ZombiePigmenTypes;
import com.joojet.plugins.mobs.util.EquipmentTools;

public class AmplifiedMobHandler implements AmplifiedSpawnHandler 
{
	/** Mob equipment factories */
	private ZombieTypes zombieTypes;
	private SkeletonTypes skeletonTypes;
	private SpiderTypes spiderTypes;
	private GolemTypes golemTypes;
	private SnowmanTypes snowmanTypes;
	private HuskTypes huskTypes;
	private WolfTypes wolfTypes;
	private WitherSkeletonTypes witherSkeletonTypes;
	private ZombiePigmenTypes zombiePigmenTypes;
	private PiglinTypes piglinTypes;
	
	public AmplifiedMobHandler ()
	{
		this.zombieTypes = new ZombieTypes();
		this.skeletonTypes = new SkeletonTypes();
		this.spiderTypes = new SpiderTypes();
		this.golemTypes = new GolemTypes();
		this.snowmanTypes = new SnowmanTypes();
		this.huskTypes = new HuskTypes();
		this.wolfTypes = new WolfTypes ();
		this.witherSkeletonTypes = new WitherSkeletonTypes();
		this.zombiePigmenTypes = new ZombiePigmenTypes();
		this.piglinTypes = new PiglinTypes();
	}
	
	@Override
	public void handleSpawnEvent(LivingEntity entity, EntityType type, SpawnReason reason, Biome biome, double roll) 
	{
		// Do not alter any mob that isn't spawned into the world naturally or dice roll fails
		if ((!reasonFilter(reason) || roll > AGCraftPlugin.plugin.customMobSpawnChance) && !AGCraftPlugin.plugin.enableDebugMode)
		{
			return;
		}
		
		MobEquipment mobEquipment;
		switch (type)
		{
			case ZOMBIE:
				mobEquipment = zombieTypes.getRandomEquipment(biome);
				break;
			case SKELETON:
				mobEquipment = skeletonTypes.getRandomEquipment(biome);
				break;
			case SPIDER:
				mobEquipment = spiderTypes.getRandomEquipment(biome);
				break;
			case IRON_GOLEM:
				mobEquipment = golemTypes.getRandomEquipment(biome);
				break;
			case SNOWMAN:
				mobEquipment = snowmanTypes.getRandomEquipment(biome);
				break;
			case HUSK:
				mobEquipment = huskTypes.getRandomEquipment(biome);
				break;
			case WOLF:
				mobEquipment = wolfTypes.getRandomEquipment(biome);
				break;
			case WITHER_SKELETON:
				mobEquipment = this.witherSkeletonTypes.getRandomEquipment(biome);
				break;
			case ZOMBIFIED_PIGLIN:
				mobEquipment = this.zombiePigmenTypes.getRandomEquipment(biome);
				break;
			case PIGLIN:
				mobEquipment = this.piglinTypes.getRandomEquipment(biome);
				break;
			default:
				return;
		}
		
		EquipmentTools.equipEntity(entity, mobEquipment);
		
	}

	@Override
	public boolean reasonFilter(SpawnReason reason) 
	{
		return (reason == SpawnReason.NATURAL ||
				reason == SpawnReason.BUILD_SNOWMAN ||
				reason == SpawnReason.BUILD_IRONGOLEM ||
				reason == SpawnReason.VILLAGE_DEFENSE ||
				reason == SpawnReason.BREEDING);
	}

}