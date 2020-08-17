package com.joojet.plugins.mobs.util;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Ageable;
import org.bukkit.entity.Damageable;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Monster;
import org.bukkit.entity.Piglin;
import org.bukkit.entity.Player;
import org.bukkit.entity.Wolf;
import org.bukkit.inventory.EntityEquipment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.potion.PotionEffect;

import com.joojet.plugins.mobs.enums.MobFlag;
import com.joojet.plugins.mobs.metadata.FactionMetadata;
import com.joojet.plugins.mobs.metadata.MonsterTypeMetadata;
import com.joojet.plugins.mobs.monsters.MobEquipment;
import com.joojet.plugins.warp.scantools.ScanEntities;

import net.minecraft.server.v1_16_R2.EntityMonster;
import net.minecraft.server.v1_16_R2.PathfinderGoalNearestAttackableTarget;

import org.bukkit.craftbukkit.v1_16_R2.entity.CraftMonster;

public class EquipmentTools 
{	
	/** Equips a living entity with the items stored in a MobEquipment object
	 * 	@param entity - Entity we are equipping custom armor to
	 *  @param mobEquipment - Object containing custom mob equipment */
	public static void equipEntity (LivingEntity entity, MobEquipment mobEquipment)
	{
		Random rand = new Random ();
		
		// NULL check
		if (entity == null || mobEquipment == null)
		{
			return;
		}
		
		// Prevents baby entities from spawning
		if (entity instanceof Ageable)
		{
			Ageable ageableEntity = (Ageable) entity;
			ageableEntity.setAdult();
		}
		
		// Forces Piglins to always hunt
		if (entity instanceof Piglin)
		{
			Piglin piglin = (Piglin) entity;
			piglin.setIsAbleToHunt(true);
		}
		
		// Changes color of wolf's collar if this entity is a wolf
		if (entity instanceof Wolf)
		{
			Wolf wolf = (Wolf) entity;
			wolf.setCollarColor(mobEquipment.getDyeColor());
		}
		
		// Sets up entity's custom metadata values
		setCustomMetadata (entity, mobEquipment);
		
		EntityEquipment equipment = entity.getEquipment();
		ItemStack[] items = mobEquipment.getEquipment();
		float[] dropRates = mobEquipment.getDropRates();
		HashSet <MobFlag> mobFlags = mobEquipment.getMobFlags();
		
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
			entity.setCustomNameVisible(mobFlags.contains(MobFlag.SHOW_NAME));
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
		if (mobFlags.contains(MobFlag.ON_FIRE))
		{
			entity.setFireTicks(Integer.MAX_VALUE);
		}
		
		// Spawns a lightning bolt on the mob's current location if enabled. This should scare the **** out of unsuspecting players
		if (mobFlags.contains(MobFlag.SPAWN_LIGHTNING))
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
		if (mobFlags.contains(MobFlag.HUNT_ON_SPAWN))
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
		
		// Initialize custom pathfinding targets
		modifyPathfindingTargets (entity, mobEquipment);
	}
	
	/** Sets custom metadata provided in mobEquipment onto the passed living entity.
	 * 	@param entity - The Living Entity we are adding custom metadata to
	 * 	@param mobEquipment - Class containing custom metadata information */
	public static void setCustomMetadata (LivingEntity entity, MobEquipment mobEquipment)
	{
		// NULL check
		if (entity == null || mobEquipment == null)
		{
			return;
		}
		
		PersistentDataContainer metadata = entity.getPersistentDataContainer();
		metadata.set(MonsterTypeMetadata.generateGenericNamespacedKey(), PersistentDataType.STRING, mobEquipment.getMonsterType().toString());
		ArrayList <FactionMetadata> factions = mobEquipment.generateFactionMetadata();
		for (FactionMetadata faction : factions)
		{
			metadata.set(faction.generateNamespacedKey(), PersistentDataType.STRING, faction.asString());
		}
	}
	
	/** Modifies an entity's pathfinding properties based on what is stored in
	 *  its custom mob equipment instance. The Entity must be an instance of
	 *  a monster in order for this to work.
	 *   @param entity - The Living Entity we are modifying its pathfinding propetries for
	 *   @param mobEquipment - Object containing custom mob equipment */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static void modifyPathfindingTargets (LivingEntity entity, MobEquipment mobEquipment)
	{
		// NULL check
		if (entity == null || mobEquipment == null)
		{
			return;
		}
		
		// If the entity is not a monster, do nothing.
		if (!(entity instanceof Monster))
		{
			return;
		}
		
		// Cast this into a NMS entity monster
		EntityMonster nmsMob = ((CraftMonster) entity).getHandle();
		
		// Retrieves the monster's hitlist
		ArrayList <EntityType> hitlist = mobEquipment.getHitList();
		
		// Add target entity goals based on the values stored in the mob equipment's hitlist.
		for (EntityType victim : hitlist)
		{
			Class <?> mobClass = ConvertEntity.getNMSEntity(victim);
			if (mobClass != null)
			{
				nmsMob.targetSelector.a (5, new PathfinderGoalNearestAttackableTarget (nmsMob, mobClass, true));
			}
		}
	}

}
