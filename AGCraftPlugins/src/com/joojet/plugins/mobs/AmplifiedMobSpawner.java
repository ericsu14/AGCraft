package com.joojet.plugins.mobs;

import java.util.ArrayList;
import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.attribute.Attribute;
import org.bukkit.block.Biome;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Damageable;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Firework;
import org.bukkit.entity.IronGolem;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Monster;
import org.bukkit.entity.Piglin;
import org.bukkit.entity.Player;
import org.bukkit.entity.WanderingTrader;
import org.bukkit.entity.Wolf;
import org.bukkit.entity.Zombie;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.event.entity.CreatureSpawnEvent.SpawnReason;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EntityEquipment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.FireworkMeta;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.Plugin;
import org.bukkit.potion.PotionEffect;

import com.joojet.plugins.mobs.allies.golem.GolemTypes;
import com.joojet.plugins.mobs.allies.snowman.SnowmanTypes;
import com.joojet.plugins.mobs.allies.wolf.WolfTypes;
import com.joojet.plugins.mobs.enums.ServerEvent;
import com.joojet.plugins.mobs.enums.SummonTypes;
import com.joojet.plugins.mobs.fireworks.FireworkTypes;
import com.joojet.plugins.mobs.interpreter.SummoningScrollInterpreter;
import com.joojet.plugins.mobs.monsters.MobEquipment;
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
import com.joojet.plugins.mobs.scrolls.SummoningScroll;
import com.joojet.plugins.mobs.villager.VillagerEquipment;
import com.joojet.plugins.mobs.villager.wandering.WanderingVillagerTypes;
import com.joojet.plugins.warp.scantools.ScanEntities;

import net.md_5.bungee.api.ChatColor;

public class AmplifiedMobSpawner implements Listener 
{
	// Chance of spawning a elite monster
	private final double chance = 0.15;
	
	// Show debug info if set to true
	public static boolean debug = false;
	
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
	
	// Type of server event that is happening right now
	private ServerEvent serverEvent = ServerEvent.DEFAULT;
	
	// Interpreter to search for used summoning scrolls
	private SummoningScrollInterpreter summonInterpreter;
	
	// Used to generate random fireworks
	private FireworkTypes fwTypes;
	
	public AmplifiedMobSpawner ()
	{
		this.zombieTypes = new ZombieTypes();
		this.skeletonTypes = new SkeletonTypes();
		this.spiderTypes = new SpiderTypes();
		this.golemTypes = new GolemTypes();
		this.snowmanTypes = new SnowmanTypes();
		this.huskTypes = new HuskTypes();
		this.wanderingTypes = new WanderingVillagerTypes();
		this.summonInterpreter = new SummoningScrollInterpreter();
		this.wolfTypes = new WolfTypes ();
		this.fwTypes = new FireworkTypes();
		this.witherSkeletonTypes = new WitherSkeletonTypes();
		this.zombiePigmenTypes = new ZombiePigmenTypes();
		this.piglinTypes = new PiglinTypes();
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
	public void onPlayerAttack (EntityDamageByEntityEvent event)
	{
		if (!debug)
		{
			return;
		}
		
		if (event.getDamager() instanceof Player)
		{
			Player p = (Player) event.getDamager();
			LivingEntity e = (LivingEntity) event.getEntity();
			p.sendMessage("Dealt " + event.getDamage() + " damage.");
			p.sendMessage("The enemy has " + (e.getHealth() - event.getDamage()) + " health remaining");
		}
		
		if (event.getDamager() instanceof Arrow)
		{
			Arrow arr = (Arrow) event.getDamager();
			if (arr.getShooter() instanceof Player)
			{
				Player p = (Player) arr.getShooter();
				LivingEntity e = (LivingEntity) event.getEntity();
				p.sendMessage("Dealt " + event.getDamage() + " damage.");
				p.sendMessage("The enemy has " + (e.getHealth() - event.getDamage()) + " health remaining");
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
	
	/** Checks for use of a summoning scroll */
	@EventHandler
	public void useSummoningScroll (PlayerInteractEvent event)
	{
		Player p = event.getPlayer();
		if (event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK
				&& event.getItem() != null)
		{
			ItemStack item = event.getItem();
			ItemMeta itemMeta = item.getItemMeta();
			if (SummoningScroll.isSummoningScroll(item))
			{
				SummonTypes scrollType = this.summonInterpreter.searchTrie(itemMeta.getLocalizedName());
				if (scrollType != null)
				{
					SummoningScroll scroll = scrollType.getSummon();
					
					// Gets player's current location
					Location spawnLocation = p.getEyeLocation();
		
					// Spawns the entity into the world in front of the player
					LivingEntity entity = (LivingEntity) p.getWorld().spawnEntity(spawnLocation, scroll.getMobType());
					
					// If the spawned entity is a golem, make him player built
					if (entity instanceof IronGolem)
					{
						IronGolem golem = (IronGolem) entity;
						golem.setPlayerCreated(true);
					}
					
					// If the spawned entity is a wolf, autotame him
					if (entity instanceof Wolf)
					{
						Wolf wolf = (Wolf) entity;
						wolf.setAdult();
						wolf.setTamed(true);
						wolf.setOwner(p);
						wolf.setCollarColor(scroll.getMob().getDyeColor());
					}
					
					this.equipEntity(entity, scroll.getMob());
					p.sendMessage(ChatColor.AQUA + "Sucessfully summoned " + scroll.getMob().getChatColor() + scroll.getName() + ChatColor.AQUA + "!");
					p.playSound(spawnLocation, Sound.ENTITY_EVOKER_PREPARE_WOLOLO, 1.0f, 1.0f);
					p.playSound(spawnLocation, Sound.ENTITY_EVOKER_CAST_SPELL, 1.0f, 1.0f);
					p.spawnParticle(Particle.SPELL_INSTANT, spawnLocation, 10, 1.0, 1.0, 0.0, 0.1, null);
					p.spawnParticle(Particle.SPELL_INSTANT, spawnLocation, 15, 1.0, 1.0, 0.0, 0.1, null);
					int numScrolls = item.getAmount();
					
					// Dec. or wither away summoning scroll
					if (numScrolls > 1)
					{
						item.setAmount(numScrolls - 1); 
					}
					else
					{
						// Marks the scroll as used (since removing it has a chance of crashing the world)
						item.setItemMeta(SummoningScroll.markUsed(itemMeta));
					}
				}
			}
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
		
		// Handles server wide event mob spawns
		switch (this.serverEvent)
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
		if ((!reasonFilter(reason) || roll > chance) && !debug)
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
		
		this.equipEntity(entity, mobEquipment);
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
			this.equipEntity(entity, mobEquipment);
			return;
		}
	}
	
	/** Transforms the phantom into a firework phantom */
	public void transformFireworkPhantom (LivingEntity entity)
	{
		this.equipEntity(entity, new FireworkPhantom());
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
		this.equipEntity(trader, (MobEquipment) equipment);
	}
	
	/** Equips a living entity with the items stored in a MobEquipment object
	 * 	@param entity - Entity we are equipping custom armor to
	 *  @param mobEquipment - Object containing custom mob equipment */
	public void equipEntity (LivingEntity entity, MobEquipment mobEquipment)
	{
		// NULL check
		if (entity == null || mobEquipment == null)
		{
			return;
		}
		
		// Prevents baby entities from spawning
		if (entity instanceof Zombie)
		{
			Zombie zombie = (Zombie) entity;
			zombie.setBaby(false);
		}
		
		// Prevents baby piglins from spawning
		if (entity instanceof Piglin)
		{
			Piglin piglin = (Piglin) entity;
			piglin.setBaby(false);
			piglin.setIsAbleToHunt(true);
		}
		
		// Changes color of wolf's collar if this entity is a wolf
		if (entity instanceof Wolf)
		{
			Wolf wolf = (Wolf) entity;
			wolf.setCollarColor(mobEquipment.getDyeColor());
		}
		
		EntityEquipment equipment = entity.getEquipment();
		ItemStack[] items = mobEquipment.getEquipment();
		float[] dropRates = mobEquipment.getDropRates();
		
		// Helmet
		if (items[0] != null)
		{
			equipment.setHelmet(items[0]);
			equipment.setHelmetDropChance(dropRates[0]);
		}
		
		// Chestplate
		if (items[1] != null)
		{
			equipment.setChestplate(items[1]);
			equipment.setChestplateDropChance(dropRates[1]);
		}
		
		// Leggings
		if (items[2] != null)
		{
			equipment.setLeggings(items[2]);
			equipment.setLeggingsDropChance(dropRates[2]);
		}
		
		// Boots
		if (items[3] != null)
		{
			equipment.setBoots(items[3]);
			equipment.setBootsDropChance(dropRates[3]);
		}

		// Weapon
		if (items[4] != null)
		{
			equipment.setItemInMainHand(items[4]);
			equipment.setItemInMainHandDropChance(dropRates[4]);
		}
		
		// Offhand
		if (items[5] != null)
		{
			equipment.setItemInOffHand(items[5]);
			equipment.setItemInOffHandDropChance(dropRates[5]);
		}
		
		// Name
		if (!mobEquipment.getName().equals(""))
		{
			entity.setCustomName(mobEquipment.getChatColor() + "" + mobEquipment.getName());
			entity.setCustomNameVisible(mobEquipment.showName());
		}
		
		// Custom health
		if (mobEquipment.getHealth() > 0)
		{
			Damageable dmg = (Damageable) entity;
			entity.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(mobEquipment.getHealth());
			dmg.setHealth(mobEquipment.getHealth());
		}
		
		// Custom attack damage
		if (mobEquipment.getBaseAttackDamage() > 0)
		{
			entity.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE).setBaseValue(mobEquipment.getBaseAttackDamage());
		}
		
		// Potion effects
		if (!mobEquipment.getEffects().isEmpty())
		{
			for (PotionEffect effect : mobEquipment.getEffects())
			{
				entity.addPotionEffect(effect);
			}
		}
		
		// Forever ablaze
		if (mobEquipment.onFire())
		{
			entity.setFireTicks(9999999);
		}
		
		// Spawns a lightning bolt on the mob's current location if enabled. This should scare the **** out of unsuspecting players
		if (mobEquipment.spawnLightning())
		{
			Location loc = entity.getLocation();
			entity.getWorld().strikeLightningEffect(loc);
			// Also alerts the player of the monster's presence
			ArrayList <Player> nearbyPlayers = ScanEntities.ScanNearbyPlayers(entity, (int) (mobEquipment.getHuntOnSpawnRaduis() * 1.25));
			
			for (Player p : nearbyPlayers)
			{
				p.sendMessage(ChatColor.GOLD + "You feel a great disturbance in the force...");
			}
		}
		
		// Automatically sets the mob's target to a random nearby player if huntOnSpawn is set to true
		if (mobEquipment.huntOnSpawn())
		{
			if (entity instanceof Monster)
			{
				ArrayList <Player> nearbyPlayers = ScanEntities.ScanNearbyPlayers(entity, mobEquipment.getHuntOnSpawnRaduis());
				Monster mob = (Monster) entity;
				int n = nearbyPlayers.size();
				if (!nearbyPlayers.isEmpty())
				{
					Player p = nearbyPlayers.get(rand.nextInt(n));
					mob.setTarget(p);
					p.sendMessage(ChatColor.DARK_RED + "You are being hunted...");
				}
			}
		}
	}
	
	/** Toggles debug mode on or off */
	public static void toggleDebug ()
	{
		debug = !(debug);
		System.out.println ("Debug mode " + ((debug) ? "activated" : "disabled") + ".");
	}
}
