package com.joojet.plugins.mobs;

import java.util.ArrayList;
import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.block.Biome;
import org.bukkit.block.Block;
import org.bukkit.block.BlockState;
import org.bukkit.block.Skull;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Item;
import org.bukkit.entity.LargeFireball;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockDropItemEvent;
import org.bukkit.event.block.BlockPlaceEvent;
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
import com.joojet.plugins.mobs.bossbar.BossBarController;
import com.joojet.plugins.mobs.drops.MonsterDrop;
import com.joojet.plugins.mobs.enums.MobFlag;
import com.joojet.plugins.mobs.enums.MonsterStat;
import com.joojet.plugins.mobs.equipment.Equipment;
import com.joojet.plugins.mobs.equipment.EquipmentLoader;
import com.joojet.plugins.mobs.fireworks.tasks.SpawnFireworksOnLocationTask;
import com.joojet.plugins.mobs.interpreter.MonsterTypeInterpreter;
import com.joojet.plugins.mobs.metadata.EquipmentTypeMetadata;
import com.joojet.plugins.mobs.metadata.MonsterTypeMetadata;
import com.joojet.plugins.mobs.monsters.MobEquipment;
import com.joojet.plugins.mobs.spawnhandlers.AmplifiedMobHandler;
import com.joojet.plugins.mobs.spawnhandlers.BeatTheBruinsHandler;
import com.joojet.plugins.mobs.spawnhandlers.JulyFourthHandler;
import com.joojet.plugins.mobs.spawnhandlers.UHCHandler;
import com.joojet.plugins.mobs.util.LocationOffset;

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
	
	/** A list of spawn handlers for custom events */
	private JulyFourthHandler julyFourthHandler;
	private UHCHandler uhcHandler;
	private AmplifiedMobHandler amplifiedMobHandler;
	private BeatTheBruinsHandler bruinHandler;
	
	/** A reference to the boss bar controller defined in main */
	protected BossBarController bossBarController;
	
	/** Creates a new instance of this mob spawner class,
	 *  which adds listeners to Minecraft's mob spawn events for
	 *  having a certain chance of equipping them with custom armor, buffs, and weapons. */
	public AmplifiedMobSpawner (BossBarController bossBarController)
	{
		this.bossBarController = bossBarController;
		this.julyFourthHandler = new JulyFourthHandler (this.bossBarController);
		this.amplifiedMobHandler = new AmplifiedMobHandler(this.bossBarController);
		this.bruinHandler = new BeatTheBruinsHandler (this.bossBarController);
		this.uhcHandler = new UHCHandler(this.bossBarController);
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
				this.uhcHandler.createSpawnEventHandlerTask(entity, type, reason, biome, roll);
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
				this.julyFourthHandler.createSpawnEventHandlerTask(entity, type, reason, biome, roll);
				break;
			case BEAT_THE_BRUINS:
				this.bruinHandler.createSpawnEventHandlerTask(entity, type, reason, biome, roll);
				break;
			default:
				break;
		}
		
		// Handles normal spawn events
		this.amplifiedMobHandler.createSpawnEventHandlerTask(entity, type, reason, biome, roll);
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
		MobEquipment equipment = getMobEquipmentFromEntity(tamed);
		if (equipment.containsFlag(MobFlag.ENABLE_PERSISTENCE_UPON_RIDING))
		{
			tamed.setRemoveWhenFarAway(false);
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
				
				/** Converts this arrow into a critical arrow if the mob has a crit chance stat set */
				if (equipment.containsStat(MonsterStat.ARROW_CRITICAL_CHANCE))
				{
					boolean isCritical = (this.rand.nextDouble() <= equipment.getStat(MonsterStat.ARROW_CRITICAL_CHANCE));
					arrow.setCritical(isCritical);
					
					if (isCritical && equipment.containsStat(MonsterStat.ARROW_PIERCING_CHANCE))
					{
						boolean isPiercing = (this.rand.nextDouble() <= equipment.getStat(MonsterStat.ARROW_PIERCING_CHANCE));
						if (isPiercing)
						{
							arrow.setPierceLevel(1);
							arrow.setKnockbackStrength(arrow.getKnockbackStrength() + 1);
							
							// Give an audio and visual cue that the mob is using a piercing arrow
							Location entityLocation = entity.getEyeLocation();
							entity.getWorld().playSound(entityLocation, Sound.ENTITY_PLAYER_ATTACK_CRIT, 1.0f, 1.0f);
							for (int i = 0; i < 30; ++i)
							{
								entity.getWorld().spawnParticle(Particle.SPELL_MOB, LocationOffset.addRandomOffsetOnLocation(entityLocation, 1),
										0, (128 / 256D), 0, 0, 1, null);
							}
						}
					}
				}
			}
		}
	}
	
	/** If the player places a custom player head (defined in equipments) down into the world,
	 *  transfer the head's equipment data to that block so it can be retrieved once the player
	 *  breaks the custom head block. */
	@EventHandler
	public void transferCustomHeadDatatoBlock (BlockPlaceEvent event)
	{
		ItemStack item = event.getItemInHand();
		Block block = event.getBlockPlaced();
		
		if (item.getType() != Material.PLAYER_HEAD
				&& block.getType() != Material.PLAYER_HEAD)
		{
			return;
		}
		
		Equipment equipmentData = getEquipmentData (item.getItemMeta());
		if (equipmentData != null)
		{
			Skull skull = (Skull) block.getState();
			new EquipmentTypeMetadata (equipmentData).addStringMetadata(skull);
			skull.update(true);
		}
		
	}
	
	/** If the player breaks a player head block and the block has a custom equipment identifier
	 *  stored in its metadata container, transfer it to the player head drop. */
	@EventHandler
	public void transferCustomHeadDataOnBlockBreak (BlockDropItemEvent event)
	{
		BlockState blockState = event.getBlockState();
		if ((blockState.getType() != Material.PLAYER_HEAD && 
				blockState.getType() != Material.PLAYER_WALL_HEAD) || event.getItems().isEmpty())
		{
			return;
		}
		
		Skull skull = (Skull) blockState;
		Equipment equipmentData = getEquipmentData (skull);
		if (equipmentData != null)
		{
			for (Item drop : event.getItems())
			{
				if (drop.getItemStack().getType() == Material.PLAYER_HEAD)
				{
					drop.setItemStack(equipmentData);
					break;
				}
			}
		}
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
	
	/** Retrieves equipment data from an equipment or block's persistent data container. */
	public static Equipment getEquipmentData (PersistentDataHolder holder)
	{
		String idenfitier = new EquipmentTypeMetadata().getStringMetadata(holder);
		if (idenfitier != null)
		{
			Equipment equipment = equipmentLoader.getInterpreter().searchTrie(idenfitier);
			return equipment;
		}
		return null;
	}
	
}
