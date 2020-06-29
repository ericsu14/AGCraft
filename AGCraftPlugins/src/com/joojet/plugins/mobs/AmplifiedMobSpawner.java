package com.joojet.plugins.mobs;

import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Damageable;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.event.entity.CreatureSpawnEvent.SpawnReason;
import org.bukkit.inventory.EntityEquipment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;
import org.bukkit.potion.PotionEffect;

import com.joojet.plugins.mobs.enums.ZombieTypes;
import com.joojet.plugins.mobs.interfaces.MobEquipment;

public class AmplifiedMobSpawner implements Listener 
{
	// Chance of spawning a super monster
	private final double chance = 0.2;
	
	private Random rand = new Random ();
	private ZombieTypes zombieTypes = new ZombieTypes ();
	
	public void onEnable ()
	{
		Bukkit.getPluginManager().registerEvents(this, (Plugin) this);
	}
	
	@EventHandler
	public void onEntitySpawn (CreatureSpawnEvent event)
	{
		EntityType type = event.getEntityType();
		SpawnReason reason = event.getSpawnReason();
		LivingEntity entity = event.getEntity();
		
		// Do not alter any mob that isn't spawned into the world naturally or dice roll fails
		if (!reason.equals(SpawnReason.NATURAL) || rand.nextDouble() > chance)
		{
			return;
		}
		
		System.out.println ("Captured custom mob spawn event");
		
		MobEquipment mobEquipment;
		switch (type)
		{
			case ZOMBIE:
				mobEquipment = zombieTypes.getRandomEquipment();
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
		}
		
		// Chestplate
		if (items[1] != null)
		{
			equipment.setChestplate(items[1]);
		}
		
		// Leggings
		if (items[2] != null)
		{
			equipment.setLeggings(items[2]);
		}
		
		// Boots
		if (items[3] != null)
		{
			equipment.setBoots(items[3]);
		}

		// Weapon
		if (items[4] != null)
		{
			equipment.setItemInMainHand(items[4]);
		}
		
		// Offhand
		if (items[5] != null)
		{
			equipment.setItemInOffHand(items[5]);
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
			entity.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(mobEquipment.getHealth());
			Damageable dmg = (Damageable) entity;
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
		
		System.out.println ("Changed " + entity.getName() + " propetries to " + mobEquipment.getName());
	}
}
