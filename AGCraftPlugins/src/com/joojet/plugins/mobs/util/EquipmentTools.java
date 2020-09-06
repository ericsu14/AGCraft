package com.joojet.plugins.mobs.util;

import java.util.ArrayList;
import java.util.Random;

import org.bukkit.ChatColor;
import org.bukkit.FluidCollisionMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Ageable;
import org.bukkit.entity.Damageable;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Horse;
import org.bukkit.entity.Horse.*;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Mob;
import org.bukkit.entity.Monster;
import org.bukkit.entity.Piglin;
import org.bukkit.entity.Player;
import org.bukkit.entity.Wolf;
import org.bukkit.inventory.EntityEquipment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.Merchant;
import org.bukkit.persistence.PersistentDataHolder;
import org.bukkit.potion.PotionEffect;
import org.bukkit.util.RayTraceResult;
import org.bukkit.util.Vector;

import com.joojet.plugins.mobs.bossbar.BossBarAPI;
import com.joojet.plugins.mobs.enums.MobFlag;
import com.joojet.plugins.mobs.enums.MonsterStat;
import com.joojet.plugins.mobs.metadata.FactionMetadata;
import com.joojet.plugins.mobs.metadata.MonsterTypeMetadata;
import com.joojet.plugins.mobs.monsters.MobEquipment;
import com.joojet.plugins.mobs.monsters.MountedMob;
import com.joojet.plugins.mobs.util.customtargets.PathfinderGoalGiantFireball;
import com.joojet.plugins.mobs.villager.VillagerEquipment;
import com.joojet.plugins.warp.scantools.ScanEntities;

import net.minecraft.server.v1_16_R2.EntityCreature;
import net.minecraft.server.v1_16_R2.EntityGiantZombie;
import net.minecraft.server.v1_16_R2.EntityInsentient;
import net.minecraft.server.v1_16_R2.PathfinderGoalFloat;
import net.minecraft.server.v1_16_R2.PathfinderGoalLeapAtTarget;
import net.minecraft.server.v1_16_R2.PathfinderGoalMeleeAttack;
import net.minecraft.server.v1_16_R2.PathfinderGoalNearestAttackableTarget;
import net.minecraft.server.v1_16_R2.PathfinderGoalRandomStrollLand;

import org.bukkit.craftbukkit.v1_16_R2.entity.CraftMob;

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
		
		// Prevents the custom mob from spawning if it doesn't exceed a certain y limit
		// if it is enabled
		if (mobEquipment.containsStat(MonsterStat.Y_LIMIT))
		{
			Location entityLocation = entity.getLocation();
			if (entityLocation.getBlockY() < mobEquipment.getStat(MonsterStat.Y_LIMIT)
					|| !checkSpawnSpace (entity))
			{
				return;
			}
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
		
		// If the spawned entity is an instance of a villager, set up its trades
		if (entity instanceof Merchant && mobEquipment instanceof VillagerEquipment)
		{
			VillagerEquipment villagerEquipment = (VillagerEquipment) mobEquipment;
			Merchant merchant = (Merchant) entity;
			merchant.setRecipes(villagerEquipment.getRecipes());
		}
		
		// Sets up entity's custom metadata values
		setCustomMetadata (entity, mobEquipment);
		
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
			entity.setCustomNameVisible(mobEquipment.containsFlag(MobFlag.SHOW_NAME));
		}
		
		// Custom health
		if (mobEquipment.containsStat(MonsterStat.HEALTH))
		{
			double health = mobEquipment.getStat(MonsterStat.HEALTH);
			Damageable dmg = (Damageable) entity;
			entity.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(health);
			dmg.setHealth(health);
		}
		
		// Custom attack damage
		if (mobEquipment.containsStat(MonsterStat.BASE_ATTACK_DAMAGE))
		{
			entity.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE).setBaseValue(mobEquipment.getStat(MonsterStat.BASE_ATTACK_DAMAGE));
		}
		
		// Custom base speed
		if (mobEquipment.containsStat(MonsterStat.BASE_SPEED))
		{
			entity.getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).setBaseValue(mobEquipment.getStat(MonsterStat.BASE_SPEED));
		}
		
		// Enables entity persistence
		if (mobEquipment.containsFlag(MobFlag.ENABLE_PERSISTENCE))
		{
			entity.setPersistent(true);
		}
		
		// Disables entity persistence
		if (mobEquipment.containsFlag(MobFlag.DISABLE_PERSISTENCE))
		{
			entity.setPersistent(false);
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
		if (mobEquipment.containsFlag(MobFlag.ON_FIRE))
		{
			entity.setFireTicks(Integer.MAX_VALUE);
		}
		
		// Spawns a lightning bolt on the mob's current location if enabled. This should scare the **** out of unsuspecting players
		if (mobEquipment.containsFlag(MobFlag.SPAWN_LIGHTNING))
		{
			Location loc = entity.getLocation();
			entity.getWorld().strikeLightningEffect(loc);
		
			if (mobEquipment.containsStat(MonsterStat.HUNT_ON_SPAWN_RADIUS))
			{
				int huntRadius = mobEquipment.getStat(MonsterStat.HUNT_ON_SPAWN_RADIUS).intValue();
				ArrayList <Player> nearbyPlayers = ScanEntities.ScanNearbyPlayers(entity, (int) (huntRadius * 1.25));
					
				for (Player p : nearbyPlayers)
				{
					p.sendMessage(ChatColor.GOLD + "You feel a great disturbance in the force...");
				}
				
				// Automatically sets the mob's target to a random nearby player if huntOnSpawn is set to true
				if (mobEquipment.containsFlag(MobFlag.HUNT_ON_SPAWN))
				{
					if (entity instanceof Monster)
					{
						ArrayList <Player> nearbyPlayersHunt = ScanEntities.ScanNearbyPlayers(entity, huntRadius);
						Monster mob = (Monster) entity;
						int n = nearbyPlayersHunt.size();
						if (!nearbyPlayersHunt.isEmpty())
						{
							Player p = nearbyPlayersHunt.get(rand.nextInt(n));
							mob.setTarget(p);
							p.sendMessage(ChatColor.DARK_RED + "You are being hunted...");
						}
					}
				}
			}
		}
		
		// Set horse styles and color
		if (entity instanceof Horse)
		{
			Horse horse = (Horse) entity;
			if (mobEquipment.containsStat(MonsterStat.HORSE_COLOR))
			{
				horse.setColor(Color.values()
						[mobEquipment.getStat(MonsterStat.HORSE_COLOR).intValue()]);
			}
			
			if (mobEquipment.containsStat(MonsterStat.HORSE_STYLE))
			{
				horse.setStyle(Style.values()
						[mobEquipment.getStat(MonsterStat.HORSE_STYLE).intValue()]);
			}
			
			if (mobEquipment.containsStat(MonsterStat.HORSE_JUMP_STRENGTH))
			{
				horse.getAttribute(Attribute.HORSE_JUMP_STRENGTH).setBaseValue(mobEquipment.getStat(MonsterStat.HORSE_JUMP_STRENGTH));
			}
			
			// Automatically tames the horse
			horse.setTamed(true);
		}
		
		// Activates a custom boss bar for the entity
		if (mobEquipment.containsFlag(MobFlag.BOSS_BAR))
		{
			BossBarAPI.createBossBar(entity);
		}
		
		// Initialize custom pathfinding targets
		modifyPathfindingTargets (entity, mobEquipment);
		
		// Equip monster mounts
		mountMob (entity, mobEquipment);
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
		
		PersistentDataHolder holder = (PersistentDataHolder) entity;
		new MonsterTypeMetadata(mobEquipment.getMonsterType()).addStringMetadata(holder);
		ArrayList <FactionMetadata> factions = mobEquipment.generateFactionMetadata();
		for (FactionMetadata faction : factions)
		{
			faction.addStringMetadata(holder);
		}
	}
	
	/** Modifies an entity's pathfinding properties based on what is stored in
	 *  its custom mob equipment instance. The Entity must be an instance of
	 *  a mob in order for this to work.
	 *   @param entity - The Living Entity we are modifying its pathfinding properties for
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
		if (!(entity instanceof Mob))
		{
			return;
		}
		
		entity.setAI(true);
		// Cast this into a NMS entity monster
		EntityInsentient nmsMob = ((CraftMob) entity).getHandle();
		
		// Retrieves the monster's hitlist
		ArrayList <EntityType> hitlist = mobEquipment.getHitList();
		
		// Load special pathfinding goals for giants
		if (nmsMob instanceof EntityGiantZombie)
		{
			nmsMob.goalSelector.a(1, new PathfinderGoalFloat((EntityCreature) nmsMob));
			nmsMob.goalSelector.a(1, new PathfinderGoalGiantFireball((EntityGiantZombie) nmsMob, entity));
			nmsMob.goalSelector.a(4, new PathfinderGoalRandomStrollLand ((EntityCreature) nmsMob, 1.0D));
			nmsMob.goalSelector.a(4, new PathfinderGoalLeapAtTarget ((EntityCreature) nmsMob, 0.5F));
			nmsMob.goalSelector.a(4, new PathfinderGoalMeleeAttack ((EntityCreature) nmsMob, 1.0D, true));
		}
		
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
	
	public static void mountMob (LivingEntity entity, MobEquipment mobEquipment)
	{
		// Equip the monster's mounted mob
		if (mobEquipment.hasMountedMob())
		{
			MountedMob mount = mobEquipment.getMountedMob();
			Location currentLocation = entity.getLocation();
			Entity mountEnt = entity.getWorld().spawnEntity(currentLocation, mount.getEntityType());
			
			if (mountEnt instanceof LivingEntity && !mount.getMobEquipment().hasMountedMob())
			{
				equipEntity ((LivingEntity) mountEnt, mount.getMobEquipment());
			}
			mountEnt.addPassenger(entity);
		}
	}
	
	/** Checks if there is enough space for the monster to spawn.
	 *  This does a raytrace cast to check if there is enough air blocks
	 *  based on the variable, monsterHeight, available for the monster to spawn. */
	public static boolean checkSpawnSpace (LivingEntity entity)
	{
		Location entityLocation = entity.getLocation();
		Vector directionVector = new Vector (0.0, 1.0, 0.0);
		RayTraceResult result = entity.getWorld().rayTraceBlocks(entityLocation, directionVector.normalize(), 36.0,
				FluidCollisionMode.ALWAYS, true);
		if (result != null && result.getHitBlock() != null && result.getHitBlock().getType() != Material.AIR)
		{
			return false;
		}
		return true;
	}

}
