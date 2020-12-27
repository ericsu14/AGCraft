package com.joojet.plugins.mobs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import java.util.UUID;

import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.EntityShootBowEvent;
import org.bukkit.event.entity.EntitySpawnEvent;
import org.bukkit.event.world.ChunkLoadEvent;
import org.bukkit.event.world.ChunkUnloadEvent;
import org.bukkit.scheduler.BukkitRunnable;

import com.joojet.plugins.agcraft.config.ServerConfigFile;
import com.joojet.plugins.agcraft.interfaces.AGListener;
import com.joojet.plugins.agcraft.main.AGCraftPlugin;
import com.joojet.plugins.mobs.enums.MonsterStat;
import com.joojet.plugins.mobs.interpreter.MonsterTypeInterpreter;
import com.joojet.plugins.mobs.monsters.MobEquipment;
import com.joojet.plugins.mobs.skills.AbstractSkill;
import com.joojet.plugins.mobs.skills.runnable.MobSkillRunnable;
import com.joojet.plugins.mobs.util.LocationOffset;

public class CustomSkillsListener extends AGListener {
	/** A reference to the monster type interpreter defined in the main plugin class */
	protected MonsterTypeInterpreter monsterInterpreter;
	/** A reference to the damage display manager defined in the main class
	 *  used to present visual information to the players when a mob uses a skill */
	protected DamageDisplayListener damageDisplayListener;
	/** Stores all active skill runnables in the server */
	protected HashMap <UUID, MobSkillRunnable> mobSkillRunnableTable;
	/** Random number generator used for generating chance rolls */
	protected Random rand;
	
	public CustomSkillsListener (MonsterTypeInterpreter monsterInterpreter, DamageDisplayListener damageDisplayListener)
	{
		this.monsterInterpreter = monsterInterpreter;
		this.damageDisplayListener = damageDisplayListener;
		this.mobSkillRunnableTable = new HashMap <UUID, MobSkillRunnable> ();
		this.rand = new Random ();
	}
	
	@Override
	public void loadConfigVariables(ServerConfigFile config) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onEnable() {
		// TODO Auto-generated method stub

	}

	@Override
	public void onDisable() {
		for (MobSkillRunnable runnable : this.mobSkillRunnableTable.values())
		{
			if (!runnable.isCancelled())
			{
				runnable.cancel();
			}
		}
		
		this.mobSkillRunnableTable.clear();
	}
	
	@EventHandler(priority = EventPriority.LOW)
	public void onEntitySpawn (EntitySpawnEvent spawnEvent)
	{
		this.loadCustomSkillsOntoEntity(spawnEvent.getEntity());
	}
	
	/** Returns true if there already exists a skill runnable instance for an entity */
	public boolean containsSkillRunnable (Entity entity)
	{
		return this.mobSkillRunnableTable.containsKey(entity.getUniqueId());
	}
	
	/** Associates a new skill runnable instance with an entity */
	public void attachRunnableToEntity (Entity entity, MobSkillRunnable runnable)
	{
		if (!this.containsSkillRunnable(entity))
		{
			this.mobSkillRunnableTable.put(entity.getUniqueId(), runnable);
		}
	}
	
	/** Removes a skill runnable instance from an entity */
	public void removeRunnableFromEntity (UUID entityUUID)
	{
		if (this.mobSkillRunnableTable.containsKey(entityUUID))
		{
			MobSkillRunnable runnable = this.mobSkillRunnableTable.remove(entityUUID);
			if (!runnable.isCancelled())
			{
				runnable.cancel();
			}
		}
	}
	
	@EventHandler (priority = EventPriority.LOW)
	public void onChunkLoad (ChunkLoadEvent chunkLoadEvent)
	{
		Entity[] entities = chunkLoadEvent.getChunk().getEntities();
		
		for (Entity ent : entities)
		{
			this.loadCustomSkillsOntoEntity(ent);
		}
	}
	
	@EventHandler (priority = EventPriority.LOW)
	public void onEntityDeath (EntityDeathEvent entityDeathEvent)
	{
		if (entityDeathEvent.getEntity() != null)
		{
			this.removeRunnableFromEntity(entityDeathEvent.getEntity().getUniqueId());
		}
	}
	
	@EventHandler (priority = EventPriority.LOW)
	public void onChunkUnload (ChunkUnloadEvent chunkUnloadEvent)
	{
		Entity[] entities = chunkUnloadEvent.getChunk().getEntities();
		
		for (Entity entity : entities)
		{
			if (entity != null)
			{
				this.removeRunnableFromEntity(entity.getUniqueId());
			}
		}
	}
	
	/** Allows an entity to use a custom skill. This function also contains logic to filter out the caster's surrounding living entities
	 *  into allies and enemies.
	 *  @param caster - The Living Entity using the custom skill
	 *  @param skill - The skill to be used */
	public void useCustomSkill (LivingEntity caster, AbstractSkill skill)
	{
		ArrayList <LivingEntity> allies = new ArrayList <LivingEntity> ();
		ArrayList <LivingEntity> enemies = new ArrayList <LivingEntity> ();
		this.filterGoodAndBadEntities(caster, skill.getRange(), allies, enemies);
		
		skill.useSkill(caster, allies, enemies, this.damageDisplayListener);
	}
	
	/** Prevents dropped itemstack entities from being destroyed by explosions */
	@EventHandler(priority = EventPriority.HIGHEST)
	public void preventItemDropDestructionOnExplosionEvent (EntityDamageEvent event)
	{
		if (event.getCause() == DamageCause.ENTITY_EXPLOSION || 
				event.getCause() == DamageCause.BLOCK_EXPLOSION ||
				event.getCause() == DamageCause.LIGHTNING)
		{
			if (event.getEntity().getType() == EntityType.DROPPED_ITEM)
			{
				event.setCancelled(true);
			}
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
		MobEquipment equipment = this.monsterInterpreter.getMobEquipmentFromEntity (entity);
		
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
	
	/** Filter's a skill caster's surrounding entities using the passed range into two categories,
	 *  allies and enemies, based on the properties set in their respective MobEquipments or types.
	 *  This function modifies the allies and entities lists that are passed by reference.
	 * 	@param caster - LivingEntity using the skill
	 *  @param range - Max. search range radius set by the skill
	 *  @param allies - A list of allied entities surrounding the skill caster
	 *  @param enemies - A list of enemies surrounding the skill caster*/
	public void filterGoodAndBadEntities (LivingEntity caster, int range, List <LivingEntity> allies, List <LivingEntity> enemies)
	{
		if (range <= 0)
		{
			return;
		}
		
		List <Entity> surroundingEntities = caster.getNearbyEntities((range / 2.0), (range / 4.0), (range / 2.0));
		
		boolean isAlly = false;
		for (Entity entity : surroundingEntities)
		{
			isAlly = false;
			if (!(entity instanceof LivingEntity))
			{
				continue;
			}
			
			LivingEntity livingEntity = (LivingEntity) entity;
			MobEquipment entityEquipment = this.monsterInterpreter.getMobEquipmentFromEntity(livingEntity);
			
			// Now we need to check if the entity is an ally or an enemy to the caster
			// If the caster is a player, then the entity is an ally if it is also a player or
			// has it in its ignore list
			if (caster instanceof Player)
			{
				isAlly = ((livingEntity instanceof Player) || 
						(entityEquipment != null && entityEquipment.getIgnoreList().contains(EntityType.PLAYER)));
			}
			// Otherwise, if the caster is any other living entity, the entity is an ally if:
			// 1. The caster is in the entity's ignore list
			// 2. The entity is in the caster's ignore list
			// 3. The entity is not in any faction and the caster is in a faction that is not friendly to the player (allows factioned mobs to be allies with normal mobs)
			// 4. The entity is not in the caster's list of rivaling factions
			else
			{
				MobEquipment casterEquipment = this.monsterInterpreter.getMobEquipmentFromEntity(caster);
				
				isAlly = ((entityEquipment != null && entityEquipment.getIgnoreList().contains(caster.getType())) ||
						  (casterEquipment != null && casterEquipment.getIgnoreList().contains(livingEntity.getType())) ||
						  ((casterEquipment != null && entityEquipment != null) && (
								  !casterEquipment.getIgnoreList().contains(EntityType.PLAYER)
								  && !casterEquipment.isRivalsOf(entityEquipment))));
				
				// Check for cases where the entity is not a custom mob and the entity is still not marked an ally
				// from the previous checks. If so, the entity is an ally if it is not in the caster's hit list.
				// This allows regular zombies and skeletons to be treated like allies, but not players, as the player
				// must be in the caster's ignore list to be treated like an ally at this point.
				if (!isAlly && casterEquipment != null && 
						((entityEquipment == null && livingEntity.getType() != EntityType.PLAYER) ||
						(casterEquipment.getIgnoreList().contains(EntityType.PLAYER))))
				{
					isAlly = !(casterEquipment.getHitList().contains(livingEntity.getType()));
				}
			}
			
			if (isAlly)
			{
				allies.add(livingEntity);
			}
			else
			{
				enemies.add(livingEntity);
			}
		}
	}
	
	/** Loads custom skills onto the entity in the next second */
	private void loadCustomSkillsOntoEntity (Entity entity)
	{
		if (!(entity instanceof LivingEntity) || this.containsSkillRunnable(entity))
		{
			return;
		}
		
		LivingEntity livingEntity = (LivingEntity) entity;
		CustomSkillsListener listener = this;
		new BukkitRunnable () {
			@Override
			public void run()
			{
				MobEquipment equipment = monsterInterpreter.getMobEquipmentFromEntity(livingEntity);
				if (equipment != null && !containsSkillRunnable (entity))
				{
					MobSkillRunnable runnable = new MobSkillRunnable (livingEntity, equipment, listener);
					attachRunnableToEntity (entity, runnable);
					runnable.runTaskTimer(AGCraftPlugin.plugin, 0, 20);
				}
			}
		}.runTaskLater(AGCraftPlugin.plugin, 20);
	}

}
