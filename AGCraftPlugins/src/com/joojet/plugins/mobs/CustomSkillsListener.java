package com.joojet.plugins.mobs;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Random;

import org.bukkit.GameMode;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.EvokerFangs;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Monster;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.entity.EntityChangeBlockEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.EntityRegainHealthEvent;
import org.bukkit.event.entity.EntitySpawnEvent;
import org.bukkit.event.entity.ProjectileLaunchEvent;
import org.bukkit.event.world.ChunkLoadEvent;
import org.bukkit.event.world.ChunkUnloadEvent;
import org.bukkit.event.world.WorldLoadEvent;

import com.joojet.plugins.agcraft.config.ServerConfigFile;
import com.joojet.plugins.agcraft.interfaces.AGListener;
import com.joojet.plugins.agcraft.main.AGCraftPlugin;
import com.joojet.plugins.mobs.bossbar.BossBarController;
import com.joojet.plugins.mobs.event.CreatedCustomMonsterEvent;
import com.joojet.plugins.mobs.interpreter.MonsterTypeInterpreter;
import com.joojet.plugins.mobs.monsters.MobEquipment;
import com.joojet.plugins.mobs.skills.AbstractSkill;
import com.joojet.plugins.mobs.skills.attack.anvil.AnvilDropSkill;
import com.joojet.plugins.mobs.skills.passive.interfaces.PassiveAttack;
import com.joojet.plugins.mobs.skills.passive.interfaces.PassiveEnvironmental;
import com.joojet.plugins.mobs.skills.passive.interfaces.PassiveProjectile;
import com.joojet.plugins.mobs.skills.passive.interfaces.PassiveRegeneration;
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
			BossBarController bossBarController, MobSkillRunner mobSkillRunner)
	{
		this.monsterInterpreter = monsterInterpreter;
		this.damageDisplayListener = damageDisplayListener;
		this.bossBarController = bossBarController;
		this.mobSkillRunner = mobSkillRunner;
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
	
	/** Sets custom skills for amplified mobs upon world loads */
	@EventHandler (priority = EventPriority.LOW)
	public void onWorldLoad (WorldLoadEvent worldLoadEvent)
	{
		List <Entity> entities = worldLoadEvent.getWorld().getEntities();
		
		for (Entity ent : entities)
		{
			this.loadCustomSkillsOntoEntity(ent);
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
	public void modifyCustomProjectile (ProjectileLaunchEvent event)
	{
		if (event.getEntity() == null || !(event.getEntity() instanceof Projectile))
		{
			return;
		}
		
		Projectile projectile = (Projectile) event.getEntity();
		LivingEntity entity = this.getLivingEntity(projectile);
		
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
	
	/** Handles environmental damage modifiers */
	@EventHandler (priority = EventPriority.NORMAL)
	public void modifyEntityEnvironmentalDamageEvent (EntityDamageEvent event)
	{
		if (event.getEntity() == null)
		{
			return;
		}
		
		LivingEntity target = this.getLivingEntity(event.getEntity());
		
		if (target == null)
		{
			return;
		}
		
		MobEquipment targetEquipment = this.monsterInterpreter.getMobEquipmentFromEntity(target);
		
		double totalBonusDamage = 0.0;
		double currDamage = 0.0;
		if (targetEquipment != null && this.mobSkillRunner.containsSkill(target))
		{
			for (AbstractSkill skill : this.mobSkillRunner.getSkillTask(target.getUniqueId()).getSkillList())
			{
				if (skill instanceof PassiveEnvironmental)
				{
					currDamage = ((PassiveEnvironmental) skill).modifyIncomingEnvironmentalDamageEvent(event.getDamage(), event.getCause(), target, targetEquipment);
					if (currDamage == Double.MIN_VALUE)
					{
						event.setCancelled(true);
						break;
					}
					totalBonusDamage += currDamage;
				}
			}
		}
		
		// Cancels damage event and clears the damager's current target if canceled.
		if (event.isCancelled())
		{
			return;
		}
		event.setDamage(event.getDamage() + totalBonusDamage);
	}
	
	/** Handles PassiveRegeneration skills */
	@EventHandler(priority = EventPriority.NORMAL)
	public void modifyEntityRegenEvent (EntityRegainHealthEvent event)
	{
		if (event.getEntity() == null)
		{
			return;
		}
		
		LivingEntity target = this.getLivingEntity(event.getEntity());
		
		if (target == null)
		{
			return;
		}
		
		MobEquipment targetEquipment = this.monsterInterpreter.getMobEquipmentFromEntity(target);
		
		double totalBonusDamage = 0.0;
		double currDamage = 0.0;
		if (targetEquipment != null && this.mobSkillRunner.containsSkill(target))
		{
			for (AbstractSkill skill : this.mobSkillRunner.getSkillTask(target.getUniqueId()).getSkillList())
			{
				if (skill instanceof PassiveRegeneration)
				{
					currDamage = ((PassiveRegeneration) skill).modifyRegenerationEvent(event.getAmount(), event.getRegainReason(), target, targetEquipment);
					if (currDamage == Double.MIN_VALUE)
					{
						event.setCancelled(true);
						break;
					}
					totalBonusDamage += currDamage;
				}
			}
		}
		
		// Cancels damage event and clears the damager's current target if canceled.
		if (event.isCancelled())
		{
			return;
		}
		event.setAmount(event.getAmount() + totalBonusDamage);
	}
	
	/** Activates passive attack skills to any captured entity damage by entity events,
	 *  which amplifies the damage output dealt by monsters that have certain Passive attack skills */
	@EventHandler(priority = EventPriority.NORMAL)
	public void modifyEntityDamageEvent (EntityDamageByEntityEvent event)
	{
		if (event.getEntity() == null || event.getDamager() == null)
		{
			return;
		}
		
		LivingEntity damager = this.getLivingEntity(event.getDamager());
		LivingEntity target = this.getLivingEntity(event.getEntity());
		
		if (damager == null || target == null)
		{
			return;
		}
		
		MobEquipment damagerEquipment = this.monsterInterpreter.getMobEquipmentFromEntity(damager);
		MobEquipment targetEquipment = this.monsterInterpreter.getMobEquipmentFromEntity(target);
		
		double totalBonusDamage = 0.0;
		double currDamage = 0.0;
		// Handles outgoing damage events if the damager is a custom mob with PassiveAttack skills
		if (damagerEquipment != null && this.mobSkillRunner.containsSkill(damager))
		{
			for (AbstractSkill skill : this.mobSkillRunner.getSkillTask(damager.getUniqueId()).getSkillList())
			{
				if (skill instanceof PassiveAttack)
				{
					currDamage = ((PassiveAttack) skill).modifyOutgoingDamageEvent(event.getDamage(), event.getDamager(), damager, target,
							damagerEquipment, targetEquipment);
					// When Double.MIN_VALUE is returned then the damage event is canceled.
					if (currDamage == Double.MIN_VALUE)
					{
						event.setCancelled(true);
						break;
					}
					totalBonusDamage += currDamage;
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
					currDamage = ((PassiveAttack) skill).modifyIncomingDamageEvent(event.getDamage(), event.getDamager(), damager, target, damagerEquipment, targetEquipment);
					if (currDamage == Double.MIN_VALUE)
					{
						event.setCancelled(true);
						break;
					}
					totalBonusDamage += currDamage;
				}
			}
		}
		
		// Cancels damage event and clears the damager's current target if canceled.
		if (event.isCancelled())
		{
			return;
		}
		event.setDamage(event.getDamage() + totalBonusDamage);
	}
	
	/** Prevents anvils from being formed by falling anvils spawned-in by anvil skills */
	@EventHandler
	public void cancelFallingBlockEvent (EntityChangeBlockEvent changeBlockEvent)
	{
		Entity context = changeBlockEvent.getEntity();
		if (context.hasMetadata(AnvilDropSkill.FALLING_ANVIL_ID))
		{
			context.remove();
			changeBlockEvent.setCancelled(true);
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
		
		Collection <Entity> surroundingEntities = caster.getWorld().getNearbyEntities(caster.getLocation(), range / 2.0, range / 2.0, range / 2.0);
		
		boolean isAlly = false;
		for (Entity entity : surroundingEntities)
		{
			isAlly = false;
			if (!(entity instanceof LivingEntity) || 
					entity.getType() == EntityType.ARMOR_STAND ||
					entity.getType() == EntityType.ITEM_FRAME ||
					entity.getType() == EntityType.DROPPED_ITEM)
			{
				continue;
			}
			
			// Ignores players who are either in spectator / creative mode or allowed to fly
			if (entity instanceof Player)
			{
				Player player = (Player) entity;
				
				if (player.getGameMode() == GameMode.SPECTATOR
						|| player.getAllowFlight())
				{
					continue;
				}
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
				if (casterEquipment != null)
				{
					isAlly = casterEquipment.isAlliesOf(caster, livingEntity, entityEquipment);
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
	
	/** Casts an entity into a living entity if applicable
	 *  @param source - The source entity */
	protected LivingEntity getLivingEntity (Entity source)
	{
		Entity entity = source;
		if (source instanceof Projectile)
		{
			Projectile projectile = (Projectile) source;
			if (projectile.getShooter() != null && projectile.getShooter() instanceof Entity)
			{
				entity = (Entity) projectile.getShooter();
			}
		}
		
		if (source instanceof EvokerFangs)
		{
			EvokerFangs fangs = (EvokerFangs) source;
			if (fangs.getOwner() != null && fangs.getOwner() instanceof Entity)
			{
				entity = (Entity) fangs.getOwner();
			}
		}
		
		return (entity instanceof LivingEntity) ? (LivingEntity) entity : null;
	}
	
	/** Loads custom skills onto the entity in the next second */
	private void loadCustomSkillsOntoEntity (Entity entity)
	{
		if (entity instanceof LivingEntity)
		{
			LivingEntity livingEntity = (LivingEntity) entity;
			MobEquipment equipment = this.monsterInterpreter.getMobEquipmentFromEntity(livingEntity);
			
			if (equipment != null && !this.mobSkillRunner.containsSkill(livingEntity))
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
