package com.joojet.plugins.mobs;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Monster;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.EntityShootBowEvent;
import org.bukkit.event.entity.EntitySpawnEvent;
import org.bukkit.event.world.ChunkLoadEvent;
import org.bukkit.event.world.ChunkUnloadEvent;

import com.joojet.plugins.agcraft.config.ServerConfigFile;
import com.joojet.plugins.agcraft.interfaces.AGListener;
import com.joojet.plugins.agcraft.main.AGCraftPlugin;
import com.joojet.plugins.mobs.bossbar.BossBarController;
import com.joojet.plugins.mobs.event.CreatedCustomMonsterEvent;
import com.joojet.plugins.mobs.interpreter.MonsterTypeInterpreter;
import com.joojet.plugins.mobs.monsters.MobEquipment;
import com.joojet.plugins.mobs.skills.AbstractSkill;
import com.joojet.plugins.mobs.skills.passive.interfaces.PassiveAttack;
import com.joojet.plugins.mobs.skills.passive.interfaces.PassiveProjectile;
import com.joojet.plugins.mobs.skills.runnable.MobSkillTask;
import com.joojet.plugins.mobs.skills.runnable.MobSkillRunner;

public class CustomSkillsListener extends AGListener 
{
	/** A reference to the monster type interpreter defined in the main plugin class */
	protected MonsterTypeInterpreter monsterInterpreter;
	/** A reference to the damage display manager defined in the main class
	 *  used to present visual information to the players when a mob uses a skill */
	protected DamageDisplayListener damageDisplayListener;
	/** A reference to the plugin's boss bar controller used to initialize custom boss bar events */
	protected BossBarController bossBarController;
	/** Stores all active skill runnables in the server */
	/** Random number generator used for generating chance rolls */
	protected Random rand;
	/** Stores a registry for all active mob skill instances in the server
	 *  and runs them each tick */
	protected MobSkillRunner mobSkillRunner;
	
	public CustomSkillsListener (MonsterTypeInterpreter monsterInterpreter, DamageDisplayListener damageDisplayListener,
			BossBarController bossBarController)
	{
		this.monsterInterpreter = monsterInterpreter;
		this.damageDisplayListener = damageDisplayListener;
		this.bossBarController = bossBarController;
		this.mobSkillRunner = new MobSkillRunner ();
		this.rand = new Random ();
	}
	
	@Override
	public void loadConfigVariables(ServerConfigFile config) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onEnable() 
	{
		this.mobSkillRunner.runTaskTimer(AGCraftPlugin.plugin, 20, 20);
	}

	@Override
	public void onDisable() 
	{
		this.mobSkillRunner.cancel();
	}
	
	@EventHandler(priority = EventPriority.LOW)
	public void onEntitySpawn (EntitySpawnEvent spawnEvent)
	{
		this.loadCustomSkillsOntoEntity(spawnEvent.getEntity());
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
	
	/** Listens to custom mob creation events */
	@EventHandler
	public void onCustomMobCreation (CreatedCustomMonsterEvent customMobEvent)
	{
		this.loadCustomSkillsOntoEntity(customMobEvent.getEntity());
	}
	
	@EventHandler (priority = EventPriority.LOW)
	public void onEntityDeath (EntityDeathEvent entityDeathEvent)
	{
		Entity deathEntity = entityDeathEvent.getEntity();
		if (deathEntity != null && deathEntity instanceof LivingEntity)
		{
			this.mobSkillRunner.removeSkillFromEntity((LivingEntity) deathEntity);
		}
	}
	
	@EventHandler (priority = EventPriority.LOW)
	public void onChunkUnload (ChunkUnloadEvent chunkUnloadEvent)
	{
		Entity[] entities = chunkUnloadEvent.getChunk().getEntities();
		
		for (Entity entity : entities)
		{
			if (entity != null && entity instanceof LivingEntity)
			{
				this.mobSkillRunner.removeSkillFromEntity((LivingEntity) entity);
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
		
		skill.useSkill(caster, allies, enemies, this.damageDisplayListener, this.monsterInterpreter, this.bossBarController);
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
	
	/** Applies passive arrow skills to any projectile captured by the Entity Shoot Bow event
	 *  if the shooter has any passive projectile skills. */
	@EventHandler
	public void modifyCustomArrows (EntityShootBowEvent event)
	{
		if (event.getProjectile() == null
				|| !(event.getProjectile() instanceof Projectile)
				|| !(event.getEntity() instanceof LivingEntity))
		{
			return;
		}
		
		Projectile projectile = (Projectile) event.getProjectile();
		LivingEntity entity = (LivingEntity) event.getEntity();
		MobEquipment equipment = this.monsterInterpreter.getMobEquipmentFromEntity (entity);
		
		if (equipment != null && this.mobSkillRunner.containsSkill(entity))
		{
			for (AbstractSkill skill : this.mobSkillRunner.getSkillTask(entity.getUniqueId()).getSkillList())
			{
				if (skill instanceof PassiveProjectile)
				{
					((PassiveProjectile) skill).modifyProjectile(entity, projectile, equipment);
				}
			}
		}
	}
	
	/** Applies passive attack skills to any captured entity damage by entity events,
	 *  which amplifies the damage output dealt by monsters that have certain Passive attack skills */
	@EventHandler(priority = EventPriority.NORMAL)
	public void modifyEntityDamageEvent (EntityDamageByEntityEvent event)
	{
		if (event.getEntity() == null || event.getDamager() == null || !(event.getEntity() instanceof LivingEntity))
		{
			return;
		}
		
		/** Retrieves the damager entity from the projectile source if the damaging entity is a projectile */
		Entity damagerEnt;
		if (event.getDamager() instanceof Projectile)
		{
			damagerEnt = (Entity) ((Projectile) event.getDamager()).getShooter();
		}
		else
		{
			damagerEnt = event.getDamager();
		}
		
		if (damagerEnt == null || !(damagerEnt instanceof LivingEntity))
		{
			return;
		}
		
		LivingEntity damager = (LivingEntity) damagerEnt;
		LivingEntity target = (LivingEntity) event.getEntity();
		
		MobEquipment damagerEquipment = this.monsterInterpreter.getMobEquipmentFromEntity(damager);
		MobEquipment targetEquipment = this.monsterInterpreter.getMobEquipmentFromEntity(target);
		
		double totalBonusDamage = 0.0;
		// Handles outgoing damage events if the damager is a custom mob with PassiveAttack skills
		if (damagerEquipment != null && this.mobSkillRunner.containsSkill(damager))
		{
			for (AbstractSkill skill : this.mobSkillRunner.getSkillTask(damager.getUniqueId()).getSkillList())
			{
				if (skill instanceof PassiveAttack)
				{
					totalBonusDamage += ((PassiveAttack) skill).modifyOutgoingDamageEvent(event.getDamage(), damager, target, damagerEquipment);
				}
			}
		}
		// Handles incoming damage events if the target is a custom mob with PassiveAttack skills
		if (targetEquipment != null && this.mobSkillRunner.containsSkill(target))
		{
			for (AbstractSkill skill : this.mobSkillRunner.getSkillTask(target.getUniqueId()).getSkillList())
			{
				if (skill instanceof PassiveAttack)
				{
					totalBonusDamage += ((PassiveAttack) skill).modifyIncomingDamageEvent(event.getDamage(), damager, target, targetEquipment);
				}
			}
		}
		event.setDamage(event.getDamage() + totalBonusDamage);
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
		
		List <Entity> surroundingEntities = caster.getNearbyEntities(range / 2.0, range / 2.0, range / 2.0);
		
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
						(entityEquipment != null && entityEquipment.getIgnoreList().contains(EntityType.PLAYER)) ||
						entityEquipment == null && !(livingEntity instanceof Monster));
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
		if (entity instanceof LivingEntity)
		{
			LivingEntity livingEntity = (LivingEntity) entity;
			MobEquipment equipment = this.monsterInterpreter.getMobEquipmentFromEntity(livingEntity);
			
			if (equipment != null)
			{
				MobSkillTask task = new MobSkillTask (livingEntity, equipment, this);
				if (task.getSkillSize() > 0)
				{
					this.mobSkillRunner.attachSkillToEntity(livingEntity, task);
				}
			}
		}
	}

}
