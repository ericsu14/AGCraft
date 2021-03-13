package com.joojet.plugins.mobs.spawnhandlers;

import org.bukkit.ChatColor;
import org.bukkit.block.Biome;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.WanderingTrader;
import org.bukkit.event.entity.CreatureSpawnEvent.SpawnReason;

import com.joojet.plugins.agcraft.main.AGCraftPlugin;
import com.joojet.plugins.mobs.allies.golem.GolemTypes;
import com.joojet.plugins.mobs.allies.snowman.SnowmanTypes;
import com.joojet.plugins.mobs.allies.wolf.WolfTypes;
import com.joojet.plugins.mobs.bossbar.BossBarController;
import com.joojet.plugins.mobs.interpreter.MonsterTypeInterpreter;
import com.joojet.plugins.mobs.interpreter.SummoningScrollInterpreter;
import com.joojet.plugins.mobs.metadata.MonsterTypeMetadata;
import com.joojet.plugins.mobs.monsters.MobEquipment;
import com.joojet.plugins.mobs.monsters.chicken.ChickenTypes;
import com.joojet.plugins.mobs.monsters.hoglins.HoglinTypes;
import com.joojet.plugins.mobs.monsters.husk.HuskTypes;
import com.joojet.plugins.mobs.monsters.piglin.PiglinTypes;
import com.joojet.plugins.mobs.monsters.skeleton.SkeletonTypes;
import com.joojet.plugins.mobs.monsters.spider.SpiderTypes;
import com.joojet.plugins.mobs.monsters.squid.SquidTypes;
import com.joojet.plugins.mobs.monsters.wither_skeleton.WitherSkeletonTypes;
import com.joojet.plugins.mobs.monsters.zombie.ZombieTypes;
import com.joojet.plugins.mobs.monsters.zombie_pigmen.ZombiePigmenTypes;
import com.joojet.plugins.mobs.skills.runnable.MobSkillRunner;
import com.joojet.plugins.mobs.util.EquipmentTools;
import com.joojet.plugins.mobs.villager.VillagerEquipment;
import com.joojet.plugins.mobs.villager.wandering.WanderingVillagerTypes;

public class AmplifiedMobHandler extends AbstractSpawnHandler 
{
	/** The key used to reference this handler's spawn chance variable from the config file*/
	public static final String AMPLIFIED_MOB_HANDLER_KEY = "amplified-spawn-chance";
	/** Stores custom wandering villager instances */
	private WanderingVillagerTypes wanderingTypes;
	
	public AmplifiedMobHandler (MonsterTypeInterpreter monsterTypeInterpreter, SummoningScrollInterpreter summonTypeInterpreter, 
			BossBarController bossBarController, MobSkillRunner mobSkillRunner)
	{
		super (monsterTypeInterpreter, summonTypeInterpreter, bossBarController, mobSkillRunner, AMPLIFIED_MOB_HANDLER_KEY);
		this.wanderingTypes = new WanderingVillagerTypes (this.monsterTypeInterpreter, this.summonTypeInterpreter);
		
		this.addMonsterTypes(new ZombieTypes(this.monsterTypeInterpreter, this.summonTypeInterpreter),
				new SkeletonTypes(this.monsterTypeInterpreter, this.summonTypeInterpreter),
				new SpiderTypes(this.monsterTypeInterpreter, this.summonTypeInterpreter),
				new GolemTypes(this.monsterTypeInterpreter, this.summonTypeInterpreter),
				new SnowmanTypes(this.monsterTypeInterpreter, this.summonTypeInterpreter),
				new HuskTypes(this.monsterTypeInterpreter, this.summonTypeInterpreter),
				new WolfTypes(this.monsterTypeInterpreter, this.summonTypeInterpreter),
				new WitherSkeletonTypes(this.monsterTypeInterpreter, this.summonTypeInterpreter),
				new ZombiePigmenTypes(this.monsterTypeInterpreter, this.summonTypeInterpreter),
				new PiglinTypes(this.monsterTypeInterpreter, this.summonTypeInterpreter),
				new HoglinTypes (this.monsterTypeInterpreter, this.summonTypeInterpreter),
				new ChickenTypes (this.monsterTypeInterpreter, this.summonTypeInterpreter),
				new SquidTypes (this.monsterTypeInterpreter, this.summonTypeInterpreter));
		
		this.addSpawnReasons(SpawnReason.NATURAL, SpawnReason.BUILD_IRONGOLEM,
				SpawnReason.BUILD_SNOWMAN, SpawnReason.VILLAGE_DEFENSE, SpawnReason.BREEDING,
				SpawnReason.EGG, SpawnReason.DISPENSE_EGG, SpawnReason.JOCKEY);
	}
	
	@Override
	public void handleSpawnEvent(LivingEntity entity, EntityType type, SpawnReason reason, Biome biome) 
	{
		// If the entity already contains custom mob metadata, do nothing
		if (new MonsterTypeMetadata().getStringMetadata(entity) != null)
		{
			return;
		}
		
		// If the entity is a wandering trader, transform him
		if ((reason == SpawnReason.NATURAL || reason == SpawnReason.SPAWNER_EGG) 
				&& type == EntityType.WANDERING_TRADER)
		{
			this.transformWanderingTrader(entity, biome);
			return;
		}
		
		// Switch to raider handler if the spawn reason is RAID
		if (reason == SpawnReason.RAID)
		{
			this.makeRaiderNameVisible(entity, type);
			return;
		}
		
		// Do not alter any mob that isn't spawned into the world naturally or dice roll fails
		if (this.canSpawn(reason) || AGCraftPlugin.plugin.enableDebugMode)
		{
			this.transformLivingEntityIntoAmplifiedMob (entity, type, reason, biome);
		}
		
	}
	
	/** Makes the names of raider mobs visible */
	public void makeRaiderNameVisible (LivingEntity entity, EntityType type)
	{
		StringBuilder name = new StringBuilder (type.name().toLowerCase());
		name.replace(0, 1, type.name().toUpperCase().substring(0,1));
		name.append(" Raider");
		entity.setCustomName(ChatColor.RED + name.toString());
		entity.setCustomNameVisible(true);
	}
	
	/** Transforms a wandering trader into Frolf */
	public void transformWanderingTrader (LivingEntity entity, Biome biome)
	{
		WanderingTrader trader = (WanderingTrader) entity;
		VillagerEquipment equipment = (VillagerEquipment) wanderingTypes.getRandomEquipment(biome);
		EquipmentTools.equipEntity(trader, (MobEquipment) equipment, this.bossBarController);
	}

}
