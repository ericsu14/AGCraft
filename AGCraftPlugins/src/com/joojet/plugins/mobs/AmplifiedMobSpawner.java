package com.joojet.plugins.mobs;

import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.EntityEffect;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Damageable;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Husk;
import org.bukkit.entity.IronGolem;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.entity.WanderingTrader;
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
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.Plugin;
import org.bukkit.potion.PotionEffect;

import com.joojet.plugins.mobs.allies.golem.GolemTypes;
import com.joojet.plugins.mobs.allies.snowman.SnowmanTypes;
import com.joojet.plugins.mobs.enums.SummonTypes;
import com.joojet.plugins.mobs.interfaces.MobEquipment;
import com.joojet.plugins.mobs.interfaces.SummoningScroll;
import com.joojet.plugins.mobs.interfaces.VillagerEquipment;
import com.joojet.plugins.mobs.interpreter.SummoningScrollInterpreter;
import com.joojet.plugins.mobs.monsters.husk.HuskTypes;
import com.joojet.plugins.mobs.monsters.skeleton.SkeletonTypes;
import com.joojet.plugins.mobs.monsters.spider.SpiderTypes;
import com.joojet.plugins.mobs.monsters.zombie.ZombieTypes;
import com.joojet.plugins.mobs.villager.wandering.WanderingVillagerTypes;

import net.md_5.bungee.api.ChatColor;

public class AmplifiedMobSpawner implements Listener 
{
	// Chance of spawning a elite monster
	private final double chance = 0.15;
	
	// Show debug info if set to true
	private final boolean debug = false;
	
	private Random rand = new Random ();
	
	// Mob equipment factories
	private ZombieTypes zombieTypes;
	private SkeletonTypes skeletonTypes;
	private SpiderTypes spiderTypes;
	private GolemTypes golemTypes;
	private SnowmanTypes snowmanTypes;
	private HuskTypes huskTypes;
	private WanderingVillagerTypes wanderingTypes;
	
	// Interpreter to search for used summoning scrolls
	private SummoningScrollInterpreter summonInterpreter;
	
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
	}
	
	public void onEnable ()
	{
		Bukkit.getPluginManager().registerEvents(this, (Plugin) this);
	}
	
	/** Returns true if the passed spawn reason agrees with the set filters */
	public boolean reasonFilter (SpawnReason reason)
	{
		return (reason.equals (SpawnReason.NATURAL) ||
				reason.equals (SpawnReason.BUILD_SNOWMAN) ||
				reason.equals (SpawnReason.BUILD_IRONGOLEM) ||
				reason.equals (SpawnReason.VILLAGE_DEFENSE));
				
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
			p.sendMessage("Dealt " + event.getDamage() + " damage.");
		}
		
		else if (event.getEntity() instanceof Player)
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
					SummoningScroll scroll = this.summonInterpreter.searchTrie(itemMeta.getLocalizedName()).getSummon();
					
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
					this.equipEntity(entity, scroll.getMob());
					p.sendMessage(ChatColor.AQUA + "Sucessfully summoned " + scroll.getMob().getChatColor() + scroll.getName() + ChatColor.AQUA + "!");
					p.playSound(spawnLocation, Sound.ENTITY_EVOKER_PREPARE_WOLOLO, 1.0f, 1.0f);
					p.playSound(spawnLocation, Sound.ENTITY_EVOKER_CAST_SPELL, 1.0f, 1.0f);
					p.playEffect(EntityEffect.WITCH_MAGIC);
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
		
		// If the entity is a wandering trader, transform him
		if (type.equals(EntityType.WANDERING_TRADER))
		{
			this.transformWanderingTrader(entity);
			return;
		}
		
		// Switch to raider handler if the spawn reason is RAID
		if (reason.equals(SpawnReason.RAID))
		{
			this.makeRaiderNameVisible(entity, type);
			return;
		}
		
		// Do not alter any mob that isn't spawned into the world naturally or dice roll fails
		if ((!reasonFilter(reason) || rand.nextDouble() > chance))
		{
			return;
		}
		
		// System.out.println ("Captured custom mob spawn event");
		
		MobEquipment mobEquipment;
		switch (type)
		{
			case ZOMBIE:
				mobEquipment = zombieTypes.getRandomEquipment();
				// Prevents zombies from being babies
				Zombie zomble = (Zombie) entity;
				zomble.setBaby(false);
				break;
			case SKELETON:
				mobEquipment = skeletonTypes.getRandomEquipment();
				break;
			case SPIDER:
				mobEquipment = spiderTypes.getRandomEquipment();
				break;
			case IRON_GOLEM:
				mobEquipment = golemTypes.getRandomEquipment();
				break;
			case SNOWMAN:
				mobEquipment = snowmanTypes.getRandomEquipment();
				break;
			case HUSK:
				mobEquipment = huskTypes.getRandomEquipment();
				// Prevents baby elite husks from spawning
				Husk husk = (Husk) entity;
				husk.setBaby(false);
				break;
			default:
				return;
		}
		
		this.equipEntity(entity, mobEquipment);
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
	public void transformWanderingTrader (LivingEntity entity)
	{
		WanderingTrader trader = (WanderingTrader) entity;
		VillagerEquipment equipment = (VillagerEquipment) wanderingTypes.getRandomEquipment();
		trader.setRecipes(equipment.getRecipes());
		this.equipEntity(trader, (MobEquipment) equipment);
	}
	
	/** Equips a living entity with the items stored in a MobEquipment object
	 * 	@param entity - Entity we are equipping custom armor to
	 *  @param mobEquipment - Object containing custom mob equipment */
	public void equipEntity (LivingEntity entity, MobEquipment mobEquipment)
	{
		EntityEquipment equipment = entity.getEquipment();
		ItemStack[] items = mobEquipment.getEquipment();
		
		// Helmet
		if (items[0] != null)
		{
			equipment.setHelmet(items[0]);
			equipment.setHelmetDropChance(0.03f);
		}
		
		// Chestplate
		if (items[1] != null)
		{
			equipment.setChestplate(items[1]);
			equipment.setChestplateDropChance(0.03f);
		}
		
		// Leggings
		if (items[2] != null)
		{
			equipment.setLeggings(items[2]);
			equipment.setLeggingsDropChance(0.03f);
		}
		
		// Boots
		if (items[3] != null)
		{
			equipment.setBoots(items[3]);
			equipment.setBootsDropChance(0.03f);
		}

		// Weapon
		if (items[4] != null)
		{
			equipment.setItemInMainHand(items[4]);
			equipment.setItemInMainHandDropChance(0.01f);
		}
		
		// Offhand
		if (items[5] != null)
		{
			equipment.setItemInOffHand(items[5]);
			equipment.setItemInOffHandDropChance(0.05f);
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
	}
}
