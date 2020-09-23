package com.joojet.plugins.mobs;

import java.util.ArrayList;
import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.block.Biome;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LargeFireball;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.entity.WanderingTrader;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.event.entity.CreatureSpawnEvent.SpawnReason;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.EntityExplodeEvent;
import org.bukkit.event.entity.EntityShootBowEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataHolder;
import org.bukkit.plugin.Plugin;
import org.spigotmc.event.entity.EntityMountEvent;

import com.joojet.plugins.agcraft.main.AGCraftPlugin;
import com.joojet.plugins.mobs.drops.MonsterDrop;
import com.joojet.plugins.mobs.enums.MobFlag;
import com.joojet.plugins.mobs.enums.MonsterStat;
import com.joojet.plugins.mobs.equipment.EquipmentLoader;
import com.joojet.plugins.mobs.fireworks.tasks.SpawnFireworksOnLocationTask;
import com.joojet.plugins.mobs.interpreter.MonsterTypeInterpreter;
import com.joojet.plugins.mobs.metadata.MonsterTypeMetadata;
import com.joojet.plugins.mobs.monsters.MobEquipment;
import com.joojet.plugins.mobs.spawnhandlers.AmplifiedMobHandler;
import com.joojet.plugins.mobs.spawnhandlers.BeatTheBruinsHandler;
import com.joojet.plugins.mobs.spawnhandlers.JulyFourthHandler;
import com.joojet.plugins.mobs.spawnhandlers.UHCHandler;
import com.joojet.plugins.mobs.util.EquipmentTools;
import com.joojet.plugins.mobs.villager.VillagerEquipment;
import com.joojet.plugins.mobs.villager.wandering.WanderingVillagerTypes;

public class AmplifiedMobSpawner implements Listener 
{	
	/** Key used to reference the Amplified mob spawner's spawn chance */
	public final static String spawnChanceKey = "amplified-spawn-chance";
	
	/** Key used to reference the amplified mob spawner's debug mode */
	public final static String debugModeKey = "amplified-debug-mode";
	
	/** Search trie used to lookup custom monsters by name */
	public static MonsterTypeInterpreter mobTable = new MonsterTypeInterpreter ();
	
	/** Contains an internal search trie allowing custom equipment to be able to be looked up by its
	 *  Equipment Type identifier. */
	public static EquipmentLoader equipmentLoader = new EquipmentLoader ();
	
	/** Used to generate random numbers */
	private Random rand = new Random ();
	
	/** Mob equipment factories */
	private WanderingVillagerTypes wanderingTypes;
	
	/** A list of spawn handlers for custom events */
	private JulyFourthHandler julyFourthHandler;
	private UHCHandler uhcHandler;
	private AmplifiedMobHandler amplifiedMobHandler;
	private BeatTheBruinsHandler bruinHandler;
	
	/** Creates a new instance of this mob spawner class,
	 *  which adds listeners to Minecraft's mob spawn events for
	 *  having a certain chance of equipping them with custom armor, buffs, and weapons. */
	public AmplifiedMobSpawner ()
	{
		this.wanderingTypes = new WanderingVillagerTypes();
		this.julyFourthHandler = new JulyFourthHandler ();
		this.amplifiedMobHandler = new AmplifiedMobHandler();
		this.bruinHandler = new BeatTheBruinsHandler ();
		this.uhcHandler = new UHCHandler();
	}
	
	public void onEnable ()
	{
		Bukkit.getPluginManager().registerEvents(this, (Plugin) this);
	}
	
	/** Amplifies mob spawns */
	@EventHandler
	public void onEntitySpawn (CreatureSpawnEvent event)
	{
		
		EntityType type = event.getEntityType();
		SpawnReason reason = event.getSpawnReason();
		LivingEntity entity = event.getEntity();
		Biome biome = entity.getLocation().getBlock().getBiome();
		
		double roll = rand.nextDouble();
		
		// Handles Server Mode mob spawns
		switch (AGCraftPlugin.plugin.serverMode)
		{
			case UHC:
				this.uhcHandler.handleSpawnEvent(entity, type, reason, biome, roll);
				return;
			case MINIGAME:
				return;
			default:
				break;
		}
		
		// Handles server wide event mob spawns
		switch (AGCraftPlugin.plugin.serverEventMode)
		{
			case JULY_FOURTH:
				this.julyFourthHandler.handleSpawnEvent(entity, type, reason, biome, roll);
				break;
			case BEAT_THE_BRUINS:
				this.bruinHandler.handleSpawnEvent(entity, type, reason, biome, roll);
				break;
			default:
				break;
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
		
		// Handles normal spawn events
		this.amplifiedMobHandler.handleSpawnEvent(entity, type, reason, biome, roll);
	}
	
	/** Modifies entity experience drops on entity death events */
	@EventHandler
	public void onEntityDeath (EntityDeathEvent event)
	{
		// Attempts to remove the entity's active boss bar runnable if it has one 
		if (event.getEntity() instanceof LivingEntity)
		{
			LivingEntity entity = (LivingEntity) event.getEntity();
			
			// Modifies the entity's experience drops if it has any custom experience
			MobEquipment entityEquipment = getMobEquipmentFromEntity(entity);
			if (entityEquipment == null)
			{
				return;
			}
			
			if (entityEquipment.containsStat(MonsterStat.EXPERIENCE) && event.getDroppedExp() > 0.0)
			{
				event.setDroppedExp(entityEquipment.getStat(MonsterStat.EXPERIENCE).intValue());
			}
			
			// Adds any custom loot the monster may have
			if (!entityEquipment.getMonsterDrops().isEmpty())
			{
				ItemStack droppedItem;
				ArrayList <MonsterDrop> drops = entityEquipment.getMonsterDrops();
				for (MonsterDrop drop : drops)
				{
					droppedItem = drop.generateDrop(0.0);
					if (droppedItem != null)
					{
						event.getDrops().add(droppedItem);
					}
				}
			}
			
			// Spawns small fireworks show if this entity dies and
			// has that flag enabled
			if (entityEquipment.containsFlag(MobFlag.FIREWORK_DEATH))
			{
				new SpawnFireworksOnLocationTask(entity.getLocation(), 4, 2, 12).runTaskTimer(AGCraftPlugin.plugin, 30, 10);
			}
		}
	}
	
	/** Prevents Giant-caused fireball explosions from griefing the world */
	@EventHandler
	public void cancelGiantGriefing (EntityExplodeEvent event)
	{
		Entity entity = event.getEntity();
		
		if (entity instanceof LargeFireball)
		{
			LargeFireball fireball = (LargeFireball) entity;
			if (fireball.getShooter() != null
					&& fireball.getYield() > 1.5F)
			{
				event.blockList().clear();
			}
		}
	}
	
	/** Enforces the ENABLE_PERSISTENCE_UPON_RIDING flag, allowing any tameable mob who has this flag
	 *  to have their naturally despawning properties permanently disabled when a player rides on that
	 *  mob for the first time.  */
	@EventHandler
	public void enforcePersistenceUponRidingFlag (EntityMountEvent event)
	{
		if (!(event.getEntity() instanceof Player)
				|| !(event.getMount() instanceof LivingEntity))
		{
			return;
		}
		
		LivingEntity tamed = (LivingEntity) event.getMount();
		if (tamed instanceof LivingEntity)
		{
			MobEquipment equipment = getMobEquipmentFromEntity(tamed);
			if (equipment.containsFlag(MobFlag.ENABLE_PERSISTENCE_UPON_RIDING))
			{
				tamed.setRemoveWhenFarAway(false);
			}
		}
	}
	
	/** Captures entity-launched arrows and changes the arrow to its custom tipped arrow if
	 *  it has one defined for it. This also modifies the base damage of the shot arrow
	 *  if it belongs to a custom monster that has that custom attribute for arrows. */
	@EventHandler
	public void modifyCustomArrows (EntityShootBowEvent event)
	{
		if (event.getProjectile() == null 
				|| !(event.getEntity() instanceof LivingEntity))
		{
			return;
		}
		
		LivingEntity entity = (LivingEntity) event.getEntity();
		MobEquipment equipment = getMobEquipmentFromEntity (entity);
		
		if (equipment != null)
		{
			if (event.getProjectile() instanceof Arrow)
			{
				Arrow arrow = (Arrow) event.getProjectile();
				/* Transforms arrow to a tipped arrow if its shooter has custom data 
				 * specified for their arrows */
				if (equipment.hasTippedArrow())
				{
					equipment.getTippedArrow().applyPotionDataToArrow(arrow);
				}
				
				if (equipment.containsStat(MonsterStat.BASE_ARROW_DAMAGE))
				{
					arrow.setDamage(equipment.getStat(MonsterStat.BASE_ARROW_DAMAGE));
				}
			}
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
		EquipmentTools.equipEntity(trader, (MobEquipment) equipment);
	}
	
	/** Finds and returns a LivingEntity's custom mob equipment object.
	 *  Returns null if the entity does not have custom mob metadata.
	 *  @param entity - The living entity where we are extracting its custom mob equipment from */
	public static MobEquipment getMobEquipmentFromEntity (LivingEntity entity)
	{
		// First check if the entity has custom mob metadata
		if (entity == null)
		{
			return null;
		}
		
		PersistentDataHolder holder = (PersistentDataHolder) entity;
		String entityMeta = new MonsterTypeMetadata ().getStringMetadata(holder);
		if (entityMeta == null || entityMeta.isEmpty())
		{
			return null;
		}
		
		// Extract custom metadata from the entity and use its string to lookup its own mob equipment
		MobEquipment entityEquipment = mobTable.searchTrie(entityMeta);
		
		return entityEquipment;
	}
	
}
