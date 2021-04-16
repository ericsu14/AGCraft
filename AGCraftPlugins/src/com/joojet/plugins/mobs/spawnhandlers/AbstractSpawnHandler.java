package com.joojet.plugins.mobs.spawnhandlers;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

import org.bukkit.block.Biome;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.entity.CreatureSpawnEvent.SpawnReason;
import org.bukkit.scheduler.BukkitRunnable;

import com.joojet.plugins.agcraft.config.ServerConfigFile;
import com.joojet.plugins.agcraft.main.AGCraftPlugin;
import com.joojet.plugins.mobs.bossbar.BossBarController;
import com.joojet.plugins.mobs.interpreter.MonsterTypeInterpreter;
import com.joojet.plugins.mobs.interpreter.SummoningScrollInterpreter;
import com.joojet.plugins.mobs.monsters.MobEquipment;
import com.joojet.plugins.mobs.monsters.MonsterTypes;
import com.joojet.plugins.mobs.skills.runnable.MobSkillRunner;
import com.joojet.plugins.mobs.spawning.FairSpawnController;
import com.joojet.plugins.mobs.util.EquipmentTools;

public abstract class AbstractSpawnHandler 
{
	/** Used to control mob spawns based on the passed spawn reason.
	 *  Thus, mobs can only be converted into custom mobs if its given spawn
	 *  reason exists in this set */
	protected EnumSet <SpawnReason> spawnReasonFilter;
	/** Stores a hash table of custom Monster type instances, where its key is the entity type
	 *  that class supports. */
	protected HashMap <EntityType, MonsterTypes> mobEquipmentTable;
	/** A reference to the boss bar controller defined in main */
	protected BossBarController bossBarController;
	/** Search trie used to lookup custom monsters by name */
	protected MonsterTypeInterpreter monsterTypeInterpreter;
	/** Search trie to initialize summoning scroll instances */
	protected SummoningScrollInterpreter summonTypeInterpreter;
	/** Mob conversion chance */
	protected Double spawnChance;
	/** A key used to look up this handler's specific spawn chance from the main server config file */
	protected String spawnChanceKey;
	/** A random number generator used to spawn mobs */
	protected Random rand;
	/** Used to make spawns fairer by controlling which mobs spawn based on nearby player's current equipment */
	protected FairSpawnController fairSpawnController;
	/** Mob Skill runnable used to control our custom skill system */
	protected MobSkillRunner mobSkillRunner;
	
	/** Creates a new instance of an Abstract Spawn Handler
	 * 	@param monsterTypeInterpreter - A reference to the monster type interpreter, which is used to register
	 *         all custom mob instances into a searchable trie.
	 *  @param bossBarController - A reference to the boss bar controller instance used to handle boss bar events
	 *  @param spawnChance - Controls the chance in which custom mobs will spawn
	 *  @param spawnChanceKey - A key used to look up this handler's specific spawn chance variable
	 *         from the config file */
	public AbstractSpawnHandler (MonsterTypeInterpreter monsterTypeInterpreter, SummoningScrollInterpreter summonTypeInterpreter, 
			BossBarController bossBarController, MobSkillRunner mobSkillRunner, String spawnChanceKey)
	{
		this.spawnReasonFilter = EnumSet.noneOf(SpawnReason.class);
		this.mobEquipmentTable = new HashMap <EntityType, MonsterTypes> ();
		this.monsterTypeInterpreter = monsterTypeInterpreter;
		this.summonTypeInterpreter = summonTypeInterpreter;
		this.bossBarController = bossBarController;
		this.mobSkillRunner = mobSkillRunner;
		this.spawnChance = 0.15;
		this.spawnChanceKey = spawnChanceKey;
		this.fairSpawnController = new FairSpawnController (128);
		this.rand = new Random ();
	}
	
	/** Offloads the handleSpawnEvent task to Bukkit's scheduler. This ensures that our custom entity modification code 
	 *  runs on the next tick, which should decrease the chances of the server throwing a ConcurrentModification exception.
	 *  @param entity - The entity potentially being transformed into a custom mob
	 *  @param type - The entity's EntityType
	 *  @param reason - The reason on why this entity is spawned
	 *  @param biome - The biome in which this entity is spawned in
	 *  @param roll - A random number determining if this entity should spawn */
	public void createSpawnEventHandlerTask (LivingEntity entity, EntityType type, SpawnReason reason, Biome biome)
	{
		new BukkitRunnable() 
		{
			@Override
			public void run() 
			{
				handleSpawnEvent (entity, type, reason, biome);
			}
			
		}.runTask(AGCraftPlugin.plugin);
	}
	
	/** Handles a mob spawn event caught in the Amplified Mob Spawn listener.
	 *  @param entity - The entity potentially being transformed into a custom mob
	 *  @param type - The entity's EntityType
	 *  @param reason - The reason on why this entity is spawned
	 *  @param biome - The biome in which this entity is spawned in
	 *  @param roll - A random number determining if this entity should spawn */
	protected abstract void handleSpawnEvent (LivingEntity entity, EntityType type, SpawnReason reason, Biome biome);
	
	/** Converts a living entity into a random amplified monster based on its EntityType
	 * 	@param entity - The entity potentially being transformed into a custom mob
	 *  @param type - The entity's EntityType
	 *  @param reason - The reason on why this entity is spawned
	 *  @param biome - The biome in which this entity is spawned in */
	public void transformLivingEntityIntoAmplifiedMob (LivingEntity entity, EntityType type, SpawnReason reason, Biome biome)
	{
		MobEquipment equipment = this.getRandomEqipment(type, biome);
		if (equipment != null && this.fairSpawnController.getAverageThreatScore(entity) >= equipment.getFairSpawnThreshold())
		{
			EquipmentTools.equipEntity(entity, equipment, this.bossBarController);
		}
	}
	
	
	/** Adds a list of spawn reasons into the internal spawn reason filter */
	public void addSpawnReasons (SpawnReason... reasons)
	{
		for (SpawnReason reason : reasons)
		{
			this.spawnReasonFilter.add(reason);
		}
	}
	
	/** Adds mob equipment to the mob equipment table */
	public void addMonsterTypes (MonsterTypes... mobTypes)
	{
		for (MonsterTypes mobType : mobTypes)
		{
			List <EntityType> supportedEntities = mobType.getSupportedEntities();
			for (EntityType entity : supportedEntities)
			{
				if (!this.mobEquipmentTable.containsKey(entity))
				{
					try 
					{
						this.mobEquipmentTable.put(entity, mobType.clone());
					} 
					catch (CloneNotSupportedException e)
					{
						e.printStackTrace();
					}
				}
				else
				{
					this.mobEquipmentTable.get(entity).mergeMonsterTypes(mobType);
				}
			}
		}
	}
	
	/** Gets random mob equipment from the mob equipment table
	 *  @param type - Monster's entity type
	 *  @param biome - The biome the original entity spawns in */
	public MobEquipment getRandomEqipment (EntityType type, Biome biome)
	{
		MobEquipment result = null;
		MonsterTypes mobTypes = this.mobEquipmentTable.get(type);
		if (mobTypes != null)
		{
			result = mobTypes.getRandomEquipment(biome, this.mobSkillRunner);
		}
		return result;
	}
	
	/** Returns true if the passed spawn reason exists in our spawn reason filter.
	 *  @param reason - Spawn Reason being checked */
	public boolean reasonFilter (SpawnReason reason)
	{
		return spawnReasonFilter.contains(reason);
	}
	
	/** Does a canSpawn check, which returns true if the generated random number is
	 *  less or equal than the spawn chance and the passed spawn reason satifies the
	 *  spawn reason filter.
	 *  @param reason - The entity's spawn reason*/
	public boolean canSpawn (SpawnReason reason)
	{
		return (this.rand.nextDouble() < this.spawnChance && spawnReasonFilter.contains(reason));
	}
	
	/** Invokes the server's configuration file loader to retrieve its 
	 *  specified spawn chance
	 *  @param config - A reference to the server's config file loader */
	public void getSpawnChanceFromConfigFile (ServerConfigFile config)
	{
		this.spawnChance = config.getValueAsDouble(this.spawnChanceKey);
		this.fairSpawnController.setMaxRadius(config.getValueAsInteger(FairSpawnController.CUSTOM_MOB_SCAN_RADIUS));
	}
}
