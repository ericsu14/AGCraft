package com.joojet.plugins.mobs;

import java.util.ArrayList;

import org.bukkit.Material;
import org.bukkit.block.Biome;
import org.bukkit.block.Block;
import org.bukkit.block.BlockState;
import org.bukkit.block.Skull;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Item;
import org.bukkit.entity.LargeFireball;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.block.BlockDropItemEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.event.entity.CreatureSpawnEvent.SpawnReason;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.EntityExplodeEvent;
import org.bukkit.inventory.ItemStack;
import org.spigotmc.event.entity.EntityMountEvent;

import com.joojet.plugins.agcraft.config.ServerConfigFile;
import com.joojet.plugins.agcraft.interfaces.AGListener;
import com.joojet.plugins.agcraft.main.AGCraftPlugin;
import com.joojet.plugins.mobs.bossbar.BossBarController;
import com.joojet.plugins.mobs.damage.entities.DamageDisplayEntity;
import com.joojet.plugins.mobs.drops.MonsterDrop;
import com.joojet.plugins.mobs.enums.MobFlag;
import com.joojet.plugins.mobs.enums.MonsterStat;
import com.joojet.plugins.mobs.enums.ThemedServerEvent;
import com.joojet.plugins.mobs.equipment.Equipment;
import com.joojet.plugins.mobs.equipment.EquipmentLoader;
import com.joojet.plugins.mobs.fireworks.tasks.SpawnFireworksOnLocationTask;
import com.joojet.plugins.mobs.interpreter.MonsterTypeInterpreter;
import com.joojet.plugins.mobs.interpreter.SummoningScrollInterpreter;
import com.joojet.plugins.mobs.interpreter.ThemedServerEventInterpreter;
import com.joojet.plugins.mobs.metadata.EquipmentTypeMetadata;
import com.joojet.plugins.mobs.monsters.MobEquipment;
import com.joojet.plugins.mobs.scrolls.SummoningScroll;
import com.joojet.plugins.mobs.spawnhandlers.AmplifiedMobHandler;
import com.joojet.plugins.mobs.spawnhandlers.BeatTheBruinsHandler;
import com.joojet.plugins.mobs.spawnhandlers.JulyFourthHandler;
import com.joojet.plugins.mobs.spawnhandlers.UHCHandler;

public class AmplifiedMobSpawner extends AGListener 
{			
	// Stores the server-wide event mode, which may add custom themed mobs or events into the normal game world
	protected ThemedServerEvent serverEventMode = ThemedServerEvent.DEFAULT;
	
	/** Search trie used to lookup custom monsters by name */
	protected MonsterTypeInterpreter monsterTypeInterpreter;
	/** Stores the command interpreter used for server event types */
	protected ThemedServerEventInterpreter serverEventInterpreter;
	/** Search trie used to initialize custom summoning scroll instances */
	protected SummoningScrollInterpreter summonTypeInterpreter;
	
	/** Contains an internal search trie allowing custom equipment to be able to be looked up by its
	 *  Equipment Type identifier. */
	public EquipmentLoader equipmentLoader;
	
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
	public AmplifiedMobSpawner (MonsterTypeInterpreter monsterTypeInterpreter, SummoningScrollInterpreter summonTypeInterpreter, BossBarController bossBarController)
	{
		this.monsterTypeInterpreter = monsterTypeInterpreter;
		this.bossBarController = bossBarController;
		this.summonTypeInterpreter = summonTypeInterpreter;
		this.julyFourthHandler = new JulyFourthHandler (this.monsterTypeInterpreter, this.summonTypeInterpreter, this.bossBarController);
		this.amplifiedMobHandler = new AmplifiedMobHandler(this.monsterTypeInterpreter, this.summonTypeInterpreter, this.bossBarController);
		this.bruinHandler = new BeatTheBruinsHandler (this.monsterTypeInterpreter, this.summonTypeInterpreter, this.bossBarController);
		this.uhcHandler = new UHCHandler(this.monsterTypeInterpreter, this.summonTypeInterpreter, this.bossBarController);
	}
	
	@Override
	public void onEnable ()
	{
		this.equipmentLoader = new EquipmentLoader();
		this.serverEventInterpreter = new ThemedServerEventInterpreter();
		
		// Inserts a dummy damage display entity into the monster type interpreter so it can be referenced
		DamageDisplayEntity ent = new DamageDisplayEntity ("PLACEHOLDER");
		this.monsterTypeInterpreter.insertWord(ent.toString(), ent);
		this.summonTypeInterpreter.insertWord(ent.toString(), new SummoningScroll (ent, EntityType.ARMOR_STAND));
	}
	
	/** Amplifies mob spawns */
	@EventHandler(priority = EventPriority.HIGH)
	public void onEntitySpawn (CreatureSpawnEvent event)
	{
		
		EntityType type = event.getEntityType();
		SpawnReason reason = event.getSpawnReason();
		LivingEntity entity = event.getEntity();
		Biome biome = entity.getLocation().getBlock().getBiome();
		
		// Handles Server Mode mob spawns
		switch (AGCraftPlugin.plugin.serverMode)
		{
			case UHC:
				this.uhcHandler.createSpawnEventHandlerTask(entity, type, reason, biome);
				return;
			case MINIGAME:
				return;
			default:
				break;
		}
		
		// Handles server wide event mob spawns
		switch (this.serverEventMode)
		{
			case JULY_FOURTH:
				this.julyFourthHandler.createSpawnEventHandlerTask(entity, type, reason, biome);
				break;
			case BEAT_THE_BRUINS:
				this.bruinHandler.createSpawnEventHandlerTask(entity, type, reason, biome);
				break;
			default:
				break;
		}
		
		// Handles normal spawn events
		this.amplifiedMobHandler.createSpawnEventHandlerTask(entity, type, reason, biome);
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
			MobEquipment entityEquipment = this.monsterTypeInterpreter.getMobEquipmentFromEntity(entity);
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
		MobEquipment equipment = this.monsterTypeInterpreter.getMobEquipmentFromEntity(tamed);
		if (equipment != null && equipment.containsFlag(MobFlag.ENABLE_PERSISTENCE_UPON_RIDING))
		{
			tamed.setRemoveWhenFarAway(false);
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
		
		Equipment equipmentData = this.equipmentLoader.getEquipmentData (item.getItemMeta());
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
		Equipment equipmentData = this.equipmentLoader.getEquipmentData (skull);
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

	@Override
	public void loadConfigVariables(ServerConfigFile config) 
	{
		// Themed server event mode
		this.serverEventMode = config.searchElementFromInterpreter(this.serverEventInterpreter,
				ThemedServerEvent.getKey(), ThemedServerEvent.DEFAULT);
		this.amplifiedMobHandler.getSpawnChanceFromConfigFile(config);
		this.julyFourthHandler.getSpawnChanceFromConfigFile(config);
		this.bruinHandler.getSpawnChanceFromConfigFile(config);
		this.uhcHandler.getSpawnChanceFromConfigFile(config);
	}

	@Override
	public void onDisable() {
		// TODO Auto-generated method stub
		
	}
	
}
