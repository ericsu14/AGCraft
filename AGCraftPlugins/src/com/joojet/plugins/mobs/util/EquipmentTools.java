package com.joojet.plugins.mobs.util;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.EnumSet;
import java.util.Map.Entry;

import org.bukkit.Bukkit;
import org.bukkit.FluidCollisionMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Ageable;
import org.bukkit.entity.Damageable;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Mob;
import org.bukkit.entity.Piglin;
import org.bukkit.entity.Wolf;
import org.bukkit.inventory.EntityEquipment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.Merchant;
import org.bukkit.persistence.PersistentDataHolder;
import org.bukkit.potion.PotionEffect;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.RayTraceResult;
import org.bukkit.util.Vector;

import com.joojet.plugins.agcraft.main.AGCraftPlugin;
import com.joojet.plugins.mobs.bossbar.BossBarController;
import com.joojet.plugins.mobs.enums.MobFlag;
import com.joojet.plugins.mobs.enums.MonsterStat;
import com.joojet.plugins.mobs.event.CreatedCustomMonsterEvent;
import com.joojet.plugins.mobs.metadata.FactionMetadata;
import com.joojet.plugins.mobs.metadata.MonsterTypeMetadata;
import com.joojet.plugins.mobs.metadata.SummonedMetadata;
import com.joojet.plugins.mobs.monsters.MobEquipment;
import com.joojet.plugins.mobs.monsters.MountedMob;
import com.joojet.plugins.mobs.pathfinding.PathfinderGoalGiantFireball;
import com.joojet.plugins.mobs.villager.VillagerEquipment;

import net.minecraft.server.v1_16_R3.EntityCreature;
import net.minecraft.server.v1_16_R3.EntityGiantZombie;
import net.minecraft.server.v1_16_R3.EntityInsentient;
import net.minecraft.server.v1_16_R3.PathfinderGoalFloat;
import net.minecraft.server.v1_16_R3.PathfinderGoalLeapAtTarget;
import net.minecraft.server.v1_16_R3.PathfinderGoalMeleeAttack;
import net.minecraft.server.v1_16_R3.PathfinderGoalNearestAttackableTarget;
import net.minecraft.server.v1_16_R3.PathfinderGoalRandomStrollLand;

import org.bukkit.craftbukkit.v1_16_R3.entity.CraftMob;

public class EquipmentTools 
{	
	
	/** Total number of AIR blocks that must be above the entity's position
	 *  upon spawning any entity that has a Y_LIMIT stat. */
	public static double openAirRequirement = 36.0;
	
	/** Equips a living entity with the items stored in a MobEquipment object
	 * 	@param entity - Entity we are equipping custom armor to
	 *  @param mobEquipment - Object containing custom mob equipment
	 *  @param bossBarController - A reference to an active boss bar controller instance, which is used to manage
	 *  all custom boss bars */
	public static void equipEntity (LivingEntity entity, MobEquipment mobEquipment, BossBarController bossBarController)
	{
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
					|| !checkSpawnSpace (entity, openAirRequirement))
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
		if (!mobEquipment.getName().isEmpty())
		{
			entity.setCustomName(mobEquipment.getChatColor() + "" + mobEquipment.getName());
		}
		
		// Modifies base stats of the custom mob
		modifyBaseStats (entity, mobEquipment);
		// Applies active mob flag and mob stat attributes to the mob
		applyCustomAttributes (entity, mobEquipment, bossBarController);

		// Potion effects
		if (!mobEquipment.getEffects().isEmpty())
		{
			for (PotionEffect effect : mobEquipment.getEffects())
			{
				entity.addPotionEffect(effect);
			}
		}
		
		// Initialize custom pathfinding targets
		modifyPathfindingTargets (entity, mobEquipment);
		// Equip monster mounts
		mountMob (entity, mobEquipment, bossBarController);
		// Creates a new CreatedCustomMonsterEvent after monster is fully transormed
		Bukkit.getPluginManager().callEvent(new CreatedCustomMonsterEvent (entity, mobEquipment));
	}
	
	/** Modifies base stats of the entity based on the values set in its MobEquipment container
	 * 	@param entity - The Living entity whose stats we are modifying
	 *  @param mobEquipment - Class containing custom mob stat data */
	public static void modifyBaseStats (LivingEntity entity, MobEquipment mobEquipment)
	{
		EnumMap <MonsterStat, Double> mobStats = mobEquipment.getStatContainer();
		
		Attribute attribute;
		for (Entry<MonsterStat, Double> stat : mobStats.entrySet())
		{
			if (stat.getKey().containsAttribute()
					&& entity.getAttribute(stat.getKey().getAttribute()) != null)
			{
				if (stat.getKey() != MonsterStat.HEALTH)
				{
					attribute = stat.getKey().getAttribute();
					entity.getAttribute(attribute).setBaseValue(stat.getValue());
				}
				else
				{
					double health = mobEquipment.getStat(stat.getKey());
					Damageable dmg = (Damageable) entity;
					entity.getAttribute(stat.getKey().getAttribute()).setBaseValue(health);
					dmg.setHealth(health);
				}
			}
		}
	}
	
	/** Iterates through all MonsterStat and MobFlag attributes defined in the mobEquipment instance
	 *  and applys all of its custom attributes to the living entity
	 *  @param entity - Entity we are equipping custom armor to
	 *  @param mobEquipment - Object containing custom mob equipment
	 *  @param bossBarController - A reference to an active boss bar controller instance, which is used to manage */
	public static void applyCustomAttributes (LivingEntity entity, MobEquipment mobEquipment, BossBarController bossBarController)
	{
		// Monster stat
		for (MonsterStat stat : mobEquipment.getStatContainer().keySet())
		{
			stat.applyCustomAttributes(entity, mobEquipment, bossBarController);
		}
		
		// Monster flags
		for (MobFlag flag : mobEquipment.getMobFlags())
		{
			flag.applyCustomAttributes(entity, mobEquipment, bossBarController);
		}
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
		
		// Offloads custom pathfinding target initialization code into the next game tick
		new BukkitRunnable () {
			@Override
			public void run ()
			{
				// Cast this into a NMS entity monster
				EntityInsentient nmsMob = ((CraftMob) entity).getHandle();
				
				// Retrieves the monster's hitlist
				ArrayList <EntityType> hitlist = mobEquipment.getHitList();
				EnumSet <EntityType> ignoreList = mobEquipment.getIgnoreList();
				
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
				
				// Attempt to remove target entity goals that are in the mob's ignore list
				for (EntityType ignored : ignoreList)
				{
					Class <?> mobClass = ConvertEntity.getNMSEntity(ignored);
					if (mobClass != null)
					{
						nmsMob.targetSelector.a(new PathfinderGoalNearestAttackableTarget (nmsMob, mobClass, true));
					}
				}
			}
		}.runTask(AGCraftPlugin.plugin);
	}
	
	public static void mountMob (LivingEntity entity, MobEquipment mobEquipment, BossBarController bossBarController)
	{
		// Equip the monster's mounted mob
		if (mobEquipment.hasMountedMob())
		{
			MountedMob mount = mobEquipment.getMountedMob();
			Location currentLocation = entity.getLocation();
			Entity mountEnt = entity.getWorld().spawnEntity(currentLocation, mount.getEntityType());
			
			if (mountEnt instanceof LivingEntity && !mount.getMobEquipment().hasMountedMob())
			{
				equipEntity ((LivingEntity) mountEnt, mount.getMobEquipment(), bossBarController);
			}
			mountEnt.addPassenger(entity);
			
			// If the entity is summoned by a summoning scroll, make the mounted mob persistent
			// and add the summoned metadata to the mounted monster
			boolean isSummon = new SummonedMetadata().getStringMetadata(entity) != null;
			if (isSummon && mountEnt instanceof LivingEntity)
			{
				new SummonedMetadata (mount.getMobEquipment().getName()).addStringMetadata(mountEnt);
				((LivingEntity) mountEnt).setRemoveWhenFarAway(false);
			}
		}
	}
	
	/** Checks if there is enough space for the monster to spawn.
	 *  This does a raytrace cast to check if there is enough air blocks
	 *  based on the variable, monsterHeight, available for the monster to spawn. */
	public static boolean checkSpawnSpace (LivingEntity entity, double height)
	{
		return checkSpawnSpace (entity.getLocation(), height);
	}
	
	/** Checks if there is enough space for the monster to spawn.
	 *  This does a raytrace cast to check if there is enough air blocks
	 *  based on the variable, monsterHeight, available for the monster to spawn. */
	public static boolean checkSpawnSpace (Location entityLocation, double height)
	{
		Vector directionVector = new Vector (0.0, 1.0, 0.0);
		RayTraceResult result = entityLocation.getWorld().rayTraceBlocks(entityLocation, directionVector.normalize(), 
				height, FluidCollisionMode.ALWAYS, true);
		if (result != null && result.getHitBlock() != null && result.getHitBlock().getType() != Material.AIR)
		{
			return false;
		}
		return true;
	}
}
