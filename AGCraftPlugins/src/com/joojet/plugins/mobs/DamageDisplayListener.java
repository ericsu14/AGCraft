package com.joojet.plugins.mobs;

import java.util.EnumSet;

import org.bukkit.entity.AbstractArrow;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.event.entity.EntityRegainHealthEvent;
import org.bukkit.event.entity.EntityRegainHealthEvent.RegainReason;
import org.bukkit.event.world.ChunkLoadEvent;
import org.bukkit.event.world.ChunkUnloadEvent;
import org.bukkit.potion.PotionEffectType;

import com.joojet.plugins.agcraft.config.ServerConfigFile;
import com.joojet.plugins.agcraft.enums.ServerMode;
import com.joojet.plugins.agcraft.interfaces.AGListener;
import com.joojet.plugins.agcraft.main.AGCraftPlugin;
import com.joojet.plugins.mobs.bossbar.BossBarController;
import com.joojet.plugins.mobs.damage.DamageDisplayManager;
import com.joojet.plugins.mobs.damage.enums.DamageType;
import com.joojet.plugins.mobs.enums.DamageDisplayMode;
import com.joojet.plugins.mobs.enums.MobFlag;
import com.joojet.plugins.mobs.enums.MonsterType;
import com.joojet.plugins.mobs.interpreter.DamageDisplayModeInterpreter;
import com.joojet.plugins.mobs.interpreter.MonsterTypeInterpreter;
import com.joojet.plugins.mobs.monsters.MobEquipment;

public class DamageDisplayListener extends AGListener 
{
	/** Stores a reference to the damage display manager used to spawn in armorstand entities */
	protected DamageDisplayManager damageDisplayManager;
	/** A set of allowed regain reasons for displaying healing-based damage displays. */
	protected EnumSet <RegainReason> allowedRegainReasons;
	/** Search trie used to lookup custom monsters by name */
	protected MonsterTypeInterpreter monsterTypeInterpreter;
	/** A reference to the boss bar controller defined in main */
	protected BossBarController bossBarController;
	/** Interpreter for damage display mode */
	protected DamageDisplayModeInterpreter damageDisplayModeInterpreter;
	/** Damage display mode */
	protected DamageDisplayMode damageDisplayMode;
	
	public DamageDisplayListener (MonsterTypeInterpreter monsterTypeInterpreter, BossBarController bossBarController)
	{
		this.monsterTypeInterpreter = monsterTypeInterpreter;
		this.bossBarController = bossBarController;
		this.damageDisplayManager = new DamageDisplayManager (this.bossBarController);
		this.allowedRegainReasons = EnumSet.of(RegainReason.CUSTOM, RegainReason.MAGIC, RegainReason.MAGIC_REGEN);
		this.damageDisplayModeInterpreter = new DamageDisplayModeInterpreter ();
	}
	
	@Override
	public void onEnable ()
	{

	}
	
	@Override
	public void onDisable ()
	{
		this.damageDisplayManager.cleanup();
	}
	
	/** Displays a temporary nametag above an entity
	 *  @param entity - Entity in which nametag is displayed to
	 *  @param str - The nametag's string content */
	public void displayStringAboveEntity (Entity entity, String str)
	{
		this.damageDisplayManager.createDamageDisplayonEntity(entity, str);
	}
	
	/** Listens to entity damage events and creates a damage display entity on
	 *  the event entity's location over a small offset. */
	@EventHandler(priority = EventPriority.HIGH)
	public void onEntityDamageByEntityEvent (EntityDamageByEntityEvent event)
	{
		if (!this.checkDamageDisplayModuleIsEnabled()
				|| !(event.getEntity() instanceof LivingEntity)
				|| event.isCancelled())
		{
			return;
		}
		
		DamageType damageType = DamageType.NORMAL;
		LivingEntity eventEntity = (LivingEntity) event.getEntity();
		Entity eventDamager = event.getDamager();
		
		// Checks for monster hits directed to players
		if (eventEntity.getType() == EntityType.PLAYER
				&& eventDamager.getType() != EntityType.PLAYER)
		{
			damageType = DamageType.PLAYER;
		}
		// Checks for mob hits dealt by player inflicted critical hits
		else if (eventDamager.getType() == EntityType.PLAYER
					&& event.getCause() != DamageCause.THORNS)
		{
			damageType = this.damageDisplayManager.checkCriticalHit((Player) eventDamager) ? DamageType.CRITICAL : DamageType.NORMAL;
		}
		// Checks for mob hits dealt by allies
		else if (eventDamager instanceof LivingEntity)
		{
			MobEquipment equipment = this.monsterTypeInterpreter.getMobEquipmentFromEntity((LivingEntity) eventDamager);
			if (equipment != null && equipment.getIgnoreList().contains(EntityType.PLAYER))
			{
				damageType = DamageType.ALLIED;
			}
		}
		// Checks for damage inflicted by projectiles
		else if (eventDamager instanceof Projectile)
		{
			Projectile projectile = (Projectile) eventDamager;
			if (projectile.getShooter() != null && projectile.getShooter() instanceof LivingEntity)
			{
				MobEquipment equipment = this.monsterTypeInterpreter.getMobEquipmentFromEntity((LivingEntity) projectile.getShooter());
				if (equipment != null && equipment.getIgnoreList().contains(EntityType.PLAYER))
				{
					damageType = DamageType.ALLIED;
				}
			}
			if (damageType == DamageType.NORMAL)
			{
				switch (projectile.getType())
				{
					case TRIDENT:
						damageType = DamageType.TRIDENT;
						break;
					case ARROW:
						damageType = (((AbstractArrow) projectile).isCritical()) ? DamageType.PROJECTILE_CRITICAL : DamageType.PROJECTILE;
						break;
					default:
						break;
				}
			}
		}
		// Otherwise, convert the event's damage cause to a damage type
		if (damageType == DamageType.NORMAL)
		{
			damageType = this.getDamageTypeFromCause(event.getCause());
		}
		
		this.damageDisplayManager.createDamageDisplayonEntity(eventEntity, damageType, event.getFinalDamage());
	}
	
	/** Listens to entity damage events that are not caused by other living entites
	 *  and displays damage information on that entity */
	@EventHandler(priority = EventPriority.HIGH)
	public void onEntityDamageEvent (EntityDamageEvent event)
	{
		// Prevent duplicate damage displays
		Entity eventEntity = event.getEntity();
		if (event instanceof EntityDamageByEntityEvent || eventEntity == null || event.getFinalDamage() < 0.0
				|| !(eventEntity instanceof LivingEntity)
				|| !this.checkDamageDisplayModuleIsEnabled())
		{
			return;
		}
		
		// Cancels damage event if the damage cause is MELTING and the entity has fire resistance
		// This prevents snow golems from dying
		if (eventEntity instanceof LivingEntity)
		{
			LivingEntity ent = (LivingEntity) eventEntity;
			if (ent.hasPotionEffect(PotionEffectType.FIRE_RESISTANCE) 
					&& (event.getCause() == DamageCause.MELTING))
			{
				event.setCancelled(true);
				return;
			}
			
			if (ent.hasPotionEffect(PotionEffectType.WATER_BREATHING)
					&& (event.getCause() == DamageCause.DROWNING
							|| event.getCause() == DamageCause.DRYOUT))
			{
				event.setCancelled(true);
				return;
			}
			
			// Cancels any suffocation damage if the entity has that flag enabled
			MobEquipment equipment = this.monsterTypeInterpreter.getMobEquipmentFromEntity(ent);
			if (equipment != null && 
					equipment.containsFlag(MobFlag.DISABLE_SUFFOCATION_DAMAGE) &&
					event.getCause() == DamageCause.SUFFOCATION)
			{
				event.setCancelled(true);
				return;
			}
		}
		
		DamageType damageType = this.getDamageTypeFromCause(event.getCause());
		this.damageDisplayManager.createDamageDisplayonEntity(eventEntity, damageType, event.getFinalDamage());
	}
	
	@EventHandler(priority = EventPriority.HIGH)
	public void onEntityHealEvent (EntityRegainHealthEvent event)
	{
		// Do not run if the amount is negative or the server mode is running in Minigame mode (as this might give people's positions away in UHC)
		if (event.getAmount() < 0.0 || !this.checkDamageDisplayModuleIsEnabled() ||
				!this.allowedRegainReasons.contains(event.getRegainReason()))
		{
			return;
		}
		
		this.damageDisplayManager.createDamageDisplayonEntity(event.getEntity(), DamageType.HEALING, event.getAmount());
	}
	
	/** Removes all unremoved damage display entities from the passed list of entities
	 * 	@param entityList - List of entities captured by chunk data. */
	public void removeDamageDisplayEntities (Entity[] entityList)
	{
		for (Entity entity : entityList)
		{
			if (entity != null && entity.getType() == EntityType.ARMOR_STAND)
			{
				MobEquipment equipment = this.monsterTypeInterpreter.getMobEquipmentFromEntity((LivingEntity) entity);
				if (equipment != null && equipment.getMonsterType() == MonsterType.DAMAGE_DISPLAY_ENTITY)
				{
					this.damageDisplayManager.removeDamageDisplayEntity(entity.getUniqueId());
					if (!entity.isDead())
					{
						entity.remove();
					}
				}
			}
		}
	}
	
	/** Attempt to remove all damage display entities upon chunk loading */
	@EventHandler (priority = EventPriority.LOWEST)
	public void onChunkLoad (ChunkLoadEvent chunkLoadEvent)
	{
		this.removeDamageDisplayEntities(chunkLoadEvent.getChunk().getEntities());
	}
	
	/** Attempt to remove all damage display entities upon chunk unloading */
	@EventHandler (priority = EventPriority.LOWEST)
	public void onChunkUnload (ChunkUnloadEvent chunkUnloadEvent)
	{
		this.removeDamageDisplayEntities(chunkUnloadEvent.getChunk().getEntities());
	}
	
	/** Returns true if the damage display module is enabled */
	public boolean checkDamageDisplayModuleIsEnabled ()
	{
		switch (this.damageDisplayMode)
		{
			case ENABLED:
				return true;
			case AUTO:
				return AGCraftPlugin.plugin.serverMode == ServerMode.NORMAL;
			default:
				break;
		}
		return false;
	}
	
	/** Takes in a Bukkit DamageCause enum and converts it into its
	 *  equivant damage type enum in our Plugin.
	 *  @param cause - Damage cause captured by current EntityDamage event */
	private DamageType getDamageTypeFromCause (DamageCause cause)
	{
		DamageType damageType = DamageType.NORMAL;
		switch (cause)
		{
			case MAGIC:
				damageType = DamageType.MAGIC;
				break;
			case LAVA:
				damageType = DamageType.FIRE;
				break;
			case FIRE:
				damageType = DamageType.FIRE;
				break;
			case HOT_FLOOR:
				damageType = DamageType.FIRE;
				break;
			case FIRE_TICK:
				damageType = DamageType.FIRE;
				break;
			case POISON:
				damageType = DamageType.POISON;
				break;
			case MELTING:
				damageType = DamageType.FIRE;
				break;
			case DRYOUT:
				damageType = DamageType.DROWNING;
				break;
			case DROWNING:
				damageType = DamageType.DROWNING;
				break;
			case WITHER:
				damageType = DamageType.WITHER;
				break;
			case BLOCK_EXPLOSION:
				damageType = DamageType.EXPLOSION;
				break;
			case ENTITY_EXPLOSION:
				damageType = DamageType.EXPLOSION;
				break;
			case FALL:
				damageType = DamageType.FALL_DAMAGE;
				break;
			case THORNS:
				damageType = DamageType.THORNS;
				break;
			case CONTACT:
				damageType = DamageType.CONTACT;
				break;
			case LIGHTNING:
				damageType = DamageType.LIGHTNING;
				break;
			case SUICIDE:
				damageType = DamageType.SUICIDE;
				break;
			case VOID:
				damageType = DamageType.SUICIDE;
				break;
			case STARVATION:
				damageType = DamageType.HUNGER;
				break;
			default:
				break;
		}
		return damageType;
	}

	@Override
	public void loadConfigVariables(ServerConfigFile config) 
	{
		this.damageDisplayMode = config.searchElementFromInterpreter(this.damageDisplayModeInterpreter, DamageDisplayModeInterpreter.DAMAGE_DISPLAY_MODE_KEY, 
				DamageDisplayMode.AUTO);
	}
}
