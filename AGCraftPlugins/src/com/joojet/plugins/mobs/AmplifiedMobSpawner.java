package com.joojet.plugins.mobs;

import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Damageable;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Zombie;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.event.entity.CreatureSpawnEvent.SpawnReason;
import org.bukkit.inventory.EntityEquipment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;
import org.bukkit.potion.PotionEffect;

import com.joojet.plugins.mobs.allies.golem.GolemTypes;
import com.joojet.plugins.mobs.allies.snowman.SnowmanTypes;
import com.joojet.plugins.mobs.interfaces.MobEquipment;
import com.joojet.plugins.mobs.monsters.skeleton.SkeletonTypes;
import com.joojet.plugins.mobs.monsters.spider.SpiderTypes;
import com.joojet.plugins.mobs.monsters.zombie.ZombieTypes;

public class AmplifiedMobSpawner implements Listener 
{
	// Chance of spawning a super monster
	private final double chance = 0.2;
	
	private Random rand = new Random ();
	
	private ZombieTypes zombieTypes = new ZombieTypes ();
	private SkeletonTypes skeletonTypes = new SkeletonTypes();
	private SpiderTypes spiderTypes = new SpiderTypes();
	private GolemTypes golemTypes = new GolemTypes();
	private SnowmanTypes snowmanTypes = new SnowmanTypes();
	
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
	
	@EventHandler
	public void onEntitySpawn (CreatureSpawnEvent event)
	{
		EntityType type = event.getEntityType();
		SpawnReason reason = event.getSpawnReason();
		LivingEntity entity = event.getEntity();
		
		// Do not alter any mob that isn't spawned into the world naturally or dice roll fails
		if ((!reasonFilter(reason) || rand.nextDouble() > chance))
		{
			return;
		}
		
		System.out.println ("Captured custom mob spawn event");
		
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
			default:
				return;
		}
		
		EntityEquipment equipment = entity.getEquipment();
		ItemStack[] items = mobEquipment.getEquipment();
		
		// Helmet
		if (items[0] != null)
		{
			equipment.setHelmet(items[0]);
			equipment.setHelmetDropChance(0.075f);
		}
		
		// Chestplate
		if (items[1] != null)
		{
			equipment.setChestplate(items[1]);
			equipment.setChestplateDropChance(0.075f);
		}
		
		// Leggings
		if (items[2] != null)
		{
			equipment.setLeggings(items[2]);
			equipment.setLeggingsDropChance(0.075f);
		}
		
		// Boots
		if (items[3] != null)
		{
			equipment.setBoots(items[3]);
			equipment.setBootsDropChance(0.075f);
		}

		// Weapon
		if (items[4] != null)
		{
			equipment.setItemInMainHand(items[4]);
			equipment.setItemInMainHandDropChance(0.025f);
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
			entity.setCustomNameVisible(true);
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
		
		System.out.println ("Changed " + entity.getName() + " propetries to " + mobEquipment.getName());
	}
}
