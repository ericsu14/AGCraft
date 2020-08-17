package com.joojet.plugins.mobs.spawnhandlers;

import org.bukkit.block.Biome;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.entity.CreatureSpawnEvent.SpawnReason;

import com.joojet.plugins.agcraft.main.AGCraftPlugin;
import com.joojet.plugins.mobs.allies.golem.GolemTypes;
import com.joojet.plugins.mobs.allies.snowman.SnowmanTypes;
import com.joojet.plugins.mobs.allies.wolf.WolfTypes;
import com.joojet.plugins.mobs.monsters.MobEquipment;
import com.joojet.plugins.mobs.monsters.husk.HuskTypes;
import com.joojet.plugins.mobs.monsters.piglin.PiglinTypes;
import com.joojet.plugins.mobs.monsters.skeleton.SkeletonTypes;
import com.joojet.plugins.mobs.monsters.spider.SpiderTypes;
import com.joojet.plugins.mobs.monsters.wither_skeleton.WitherSkeletonTypes;
import com.joojet.plugins.mobs.monsters.zombie.ZombieTypes;
import com.joojet.plugins.mobs.monsters.zombie_pigmen.ZombiePigmenTypes;
import com.joojet.plugins.mobs.util.EquipmentTools;

public class AmplifiedMobHandler extends AmplifiedSpawnHandler 
{
	public AmplifiedMobHandler ()
	{
		this.addMonsterTypes(new ZombieTypes(),
				new SkeletonTypes(),
				new SpiderTypes(),
				new GolemTypes(),
				new SnowmanTypes(),
				new HuskTypes(),
				new WolfTypes(),
				new WitherSkeletonTypes(),
				new ZombiePigmenTypes(),
				new PiglinTypes());
		
		this.addSpawnReasons(SpawnReason.NATURAL, SpawnReason.BUILD_IRONGOLEM,
				SpawnReason.BUILD_SNOWMAN, SpawnReason.VILLAGE_DEFENSE, SpawnReason.BREEDING);
	}
	
	@Override
	public void handleSpawnEvent(LivingEntity entity, EntityType type, SpawnReason reason, Biome biome, double roll) 
	{
		// Do not alter any mob that isn't spawned into the world naturally or dice roll fails
		if ((!reasonFilter(reason) || roll > AGCraftPlugin.plugin.customMobSpawnChance) && !AGCraftPlugin.plugin.enableDebugMode)
		{
			return;
		}
		
		MobEquipment mobEquipment = this.getRandomEqipment(type, biome);
		if (mobEquipment != null)
		{
			EquipmentTools.equipEntity(entity, mobEquipment);
		}
		
	}

}
