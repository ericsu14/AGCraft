package com.joojet.plugins.mobs;

import java.util.ArrayList;
import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.block.Biome;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Firework;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.entity.WanderingTrader;
import org.bukkit.entity.Wolf;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.event.entity.CreatureSpawnEvent.SpawnReason;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.FireworkMeta;
import org.bukkit.plugin.Plugin;

import com.joojet.plugins.agcraft.main.AGCraftPlugin;
import com.joojet.plugins.mobs.allies.golem.GolemTypes;
import com.joojet.plugins.mobs.allies.snowman.SnowmanTypes;
import com.joojet.plugins.mobs.allies.wolf.WolfTypes;
import com.joojet.plugins.mobs.fireworks.FireworkTypes;
import com.joojet.plugins.mobs.monsters.MobEquipment;
import com.joojet.plugins.mobs.monsters.ghast.UHCGhastTypes;
import com.joojet.plugins.mobs.monsters.husk.HuskTypes;
import com.joojet.plugins.mobs.monsters.phantom.FireworkPhantom;
import com.joojet.plugins.mobs.monsters.piglin.PiglinTypes;
import com.joojet.plugins.mobs.monsters.pillager.PatrioticPillager;
import com.joojet.plugins.mobs.monsters.skeleton.PatrioticSkeleton;
import com.joojet.plugins.mobs.monsters.skeleton.SkeletonTypes;
import com.joojet.plugins.mobs.monsters.spider.SpiderTypes;
import com.joojet.plugins.mobs.monsters.wither_skeleton.WitherSkeletonTypes;
import com.joojet.plugins.mobs.monsters.zombie.PatrioticZombie;
import com.joojet.plugins.mobs.monsters.zombie.ZombieTypes;
import com.joojet.plugins.mobs.monsters.zombie_pigmen.ZombiePigmenTypes;
import com.joojet.plugins.mobs.util.EquipmentTools;
import com.joojet.plugins.mobs.villager.VillagerEquipment;
import com.joojet.plugins.mobs.villager.wandering.WanderingVillagerTypes;
import com.joojet.plugins.warp.scantools.ScanEntities;

import net.md_5.bungee.api.ChatColor;

public class AmplifiedMobSpawner implements Listener 
{	
	// Key used to reference the Amplified mob spawner's spawn chance
	public final static String spawnChanceKey = "amplified-spawn-chance";
	
	// Key used to reference the amplified mob spawner's debug mode
	public final static String debugModeKey = "amplified-debug-mode";
	
	private Random rand = new Random ();
	
	// Mob equipment factories
	private ZombieTypes zombieTypes;
	private SkeletonTypes skeletonTypes;
	private SpiderTypes spiderTypes;
	private GolemTypes golemTypes;
	private SnowmanTypes snowmanTypes;
	private HuskTypes huskTypes;
	private WolfTypes wolfTypes;
	private WanderingVillagerTypes wanderingTypes;
	private WitherSkeletonTypes witherSkeletonTypes;
	private ZombiePigmenTypes zombiePigmenTypes;
	private PiglinTypes piglinTypes;
	private UHCGhastTypes uhcGhastTypes;

	
	// Used to generate random fireworks
	private FireworkTypes fwTypes;
	
	/** Creates a new instance of this mob spawner class,
	 *  which adds listeners to Minecraft's mob spawn events for
	 *  having a certain chance of equipping them with custom armor, buffs, and weapons.
	 *  	@param serverEvent - Defines a special server event, which if set to any registered value other than DEFAULT,
	 *                           custom holiday themed mobs and special events will start occuring. */
	public AmplifiedMobSpawner ()
	{
		this.zombieTypes = new ZombieTypes();
		this.skeletonTypes = new SkeletonTypes();
		this.spiderTypes = new SpiderTypes();
		this.golemTypes = new GolemTypes();
		this.snowmanTypes = new SnowmanTypes();
		this.huskTypes = new HuskTypes();
		this.wanderingTypes = new WanderingVillagerTypes();
		this.wolfTypes = new WolfTypes ();
		this.fwTypes = new FireworkTypes();
		this.witherSkeletonTypes = new WitherSkeletonTypes();
		this.zombiePigmenTypes = new ZombiePigmenTypes();
		this.piglinTypes = new PiglinTypes();
		this.uhcGhastTypes = new UHCGhastTypes();
	}
	
	public void onEnable ()
	{
		Bukkit.getPluginManager().registerEvents(this, (Plugin) this);
	}
	
	/** Returns true if the passed spawn reason agrees with the set filters */
	public boolean reasonFilter (SpawnReason reason)
	{
		return (reason == SpawnReason.NATURAL ||
				reason == SpawnReason.BUILD_SNOWMAN ||
				reason == SpawnReason.BUILD_IRONGOLEM ||
				reason == SpawnReason.VILLAGE_DEFENSE ||
				reason == SpawnReason.BREEDING);
				
	}
	
	/** For debugging purposes */
	@EventHandler
	public void showDamageInfo (EntityDamageByEntityEvent event)
	{
		if (!AGCraftPlugin.plugin.enableDebugMode)
		{
			return;
		}
		
		if (event.getDamager() instanceof Player)
		{
			Player p = (Player) event.getDamager();
			LivingEntity e = (LivingEntity) event.getEntity();
			p.sendMessage("Dealt " + event.getDamage() + " damage.");
			p.sendMessage("The enemy has " + (e.getHealth() - event.getFinalDamage()) + " health remaining");
		}
		
		if (event.getDamager() instanceof Arrow)
		{
			Arrow arr = (Arrow) event.getDamager();
			if (arr.getShooter() instanceof Player && event.getEntity() instanceof LivingEntity)
			{
				Player p = (Player) arr.getShooter();
				LivingEntity e = (LivingEntity) event.getEntity();
				p.sendMessage("Dealt " + event.getDamage() + " damage.");
				p.sendMessage("The enemy has " + (e.getHealth() - event.getFinalDamage()) + " health remaining");
			}
		}
		
		if (event.getDamager() instanceof Wolf)
		{
			ArrayList <Player> players = ScanEntities.ScanNearbyPlayers((LivingEntity) event.getDamager(), 50);
			for (Player p : players)
			{
				p.sendMessage(event.getDamager().getName() + " dealt " + event.getDamage() + " damage");
			}
		}
		
		if (event.getEntity() instanceof Player)
		{
			Player p = (Player) event.getEntity();
			p.sendMessage("Taken " + event.getDamage() + " damage.");
		}
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
				this.handleUHCMobSpawns(type, reason, entity, biome);
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
				this.handleJulyFourthSpawns(type, reason, entity, roll);
				break;
			default:
				break;
		}
		
		// If the entity is a wandering trader, transform him
		if (type.equals(EntityType.WANDERING_TRADER))
		{
			this.transformWanderingTrader(entity, biome);
			return;
		}
		
		// Switch to raider handler if the spawn reason is RAID
		if (reason.equals(SpawnReason.RAID))
		{
			this.makeRaiderNameVisible(entity, type);
			return;
		}
		
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
	
	/** Handles UHC-specific Mob Spawns */
	public void handleUHCMobSpawns (EntityType type, SpawnReason reason, LivingEntity entity, Biome biome)
	{
		// Filter out mobs that does not satisfy our list of allowed spawn reasons
		if (!reasonFilter (reason))
		{
			return;
		}
		
		MobEquipment mobEquipment;
		switch (type)
		{
			case GHAST:
				mobEquipment = this.uhcGhastTypes.getRandomEquipment(biome);
				break;
			default:
				return;
		}
		
		EquipmentTools.equipEntity(entity, mobEquipment);
	}
	
	/** Handles 4th of july mob spawns */
	public void handleJulyFourthSpawns (EntityType type, SpawnReason reason, LivingEntity entity, double roll)
	{
		// Insta kill phantoms and let them explode
		if (type.equals(EntityType.PHANTOM))
		{
			this.transformFireworkPhantom(entity);
			return;
		}
		
		// Summon a new patriotic zombie when roll is between a certain range
		if (!reason.equals(SpawnReason.RAID) && roll >= 0.30 && roll <= 0.50)
		{
			MobEquipment mobEquipment;
			switch (type)
			{
				case ZOMBIE:
					mobEquipment = new PatrioticZombie();
					break;
				case HUSK:
					mobEquipment = new PatrioticZombie();
					break;
				case SKELETON:
					mobEquipment = new PatrioticSkeleton();
					break;
				case PILLAGER:
					mobEquipment = new PatrioticPillager();
					break;
				default:
					return;
 			}
			EquipmentTools.equipEntity(entity, mobEquipment);
			return;
		}
	}
	
	/** Transforms the phantom into a firework phantom */
	public void transformFireworkPhantom (LivingEntity entity)
	{
		EquipmentTools.equipEntity(entity, new FireworkPhantom());
		Firework firework = (Firework) entity.getWorld().spawnEntity(entity.getLocation(), EntityType.FIREWORK);
		ItemStack fwItem = fwTypes.getRandomFirework(1, 0);
		firework.setFireworkMeta((FireworkMeta)fwItem.getItemMeta());
		firework.detonate();
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
		trader.setRecipes(equipment.getRecipes());
		EquipmentTools.equipEntity(trader, (MobEquipment) equipment);
	}
}
