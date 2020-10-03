package com.joojet.plugins.mobs;

import java.util.EnumSet;

import org.bukkit.Bukkit;
import org.bukkit.entity.AbstractArrow;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.event.entity.EntityRegainHealthEvent;
import org.bukkit.event.entity.EntityRegainHealthEvent.RegainReason;
import org.bukkit.plugin.Plugin;
import org.bukkit.potion.PotionEffectType;

import com.joojet.plugins.agcraft.config.ServerConfigFile;
import com.joojet.plugins.agcraft.enums.ServerMode;
import com.joojet.plugins.agcraft.interfaces.AGListener;
import com.joojet.plugins.agcraft.main.AGCraftPlugin;
import com.joojet.plugins.mobs.bossbar.BossBarController;
import com.joojet.plugins.mobs.damage.DamageDisplayManager;
import com.joojet.plugins.mobs.damage.enums.DamageType;
import com.joojet.plugins.mobs.enums.MobFlag;
import com.joojet.plugins.mobs.enums.MonsterType;
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
	
	public DamageDisplayListener (MonsterTypeInterpreter monsterTypeInterpreter, BossBarController bossBarController)
	{
		this.monsterTypeInterpreter = monsterTypeInterpreter;
		this.bossBarController = bossBarController;
		this.damageDisplayManager = new DamageDisplayManager (this.bossBarController);
		this.allowedRegainReasons = EnumSet.noneOf(RegainReason.class);
		this.allowedRegainReasons.add(RegainReason.CUSTOM);
		this.allowedRegainReasons.add(RegainReason.MAGIC);
		this.allowedRegainReasons.add(RegainReason.MAGIC_REGEN);
	}
	
	public void onEnable ()
	{
		Bukkit.getPluginManager().registerEvents(this, (Plugin) this);
	}
	
	public void onDisable ()
	{
		this.damageDisplayManager.cleanup();
	}
	
	/** Listens to entity damage events and creates a damage display entity on
	 *  the event entity's location over a small offset. */
	@EventHandler
	public void onEntityDamageByEntityEvent (EntityDamageByEntityEvent event)
	{
		if (AGCraftPlugin.plugin.serverMode != ServerMode.NORMAL
				|| !(event.getEntity() instanceof LivingEntity))
		{
			return;
		}
		
		DamageType damageType = DamageType.NORMAL;
		
		// Checks for monster hits directed to players
		if (event.getEntity().getType() == EntityType.PLAYER
				&& event.getDamager().getType() != EntityType.PLAYER)
		{
			damageType = DamageType.PLAYER;
		}
		// Checks for mob hits dealt by player inflicted critical hits
		else if (event.getDamager().getType() == EntityType.PLAYER
					&& event.getCause() != DamageCause.THORNS)
		{
			damageType = this.damageDisplayManager.checkCriticalHit((Player) event.getDamager()) ? DamageType.CRITICAL : DamageType.NORMAL;
		}
		// Checks for mob hits dealt by allies
		else if (event.getDamager() instanceof LivingEntity)
		{
			MobEquipment equipment = this.monsterTypeInterpreter.getMobEquipmentFromEntity((LivingEntity) event.getDamager());
			if (equipment != null && equipment.getIgnoreList().contains(EntityType.PLAYER))
			{
				damageType = DamageType.ALLIED;
			}
		}
		// Checks for damage inflicted by arrows
		else if (event.getDamager() instanceof AbstractArrow)
		{
			AbstractArrow projectile = (AbstractArrow) event.getDamager();
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
						damageType = (projectile.isCritical()) ? DamageType.PROJECTILE_CRITICAL : DamageType.PROJECTILE;
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
		
		this.damageDisplayManager.createDamageDisplayonEntity(event.getEntity(), damageType, event.getFinalDamage());
	}
	
	/** Listens to entity damage events that are not caused by other living entites
	 *  and displays damage information on that entity */
	@EventHandler
	public void onEntityDamageEvent (EntityDamageEvent event)
	{
		// Prevent duplicate damage displays
		if (event instanceof EntityDamageByEntityEvent || event.getEntity() == null || event.getFinalDamage() < 0.0
				|| !(event.getEntity() instanceof LivingEntity)
				|| AGCraftPlugin.plugin.serverMode != ServerMode.NORMAL)
		{
			return;
		}
		
		// Cancels damage event if the damage cause is MELTING and the entity has fire resistance
		// This prevents snow golems from dying
		if (event.getEntity() instanceof LivingEntity)
		{
			LivingEntity ent = (LivingEntity) event.getEntity();
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
		this.damageDisplayManager.createDamageDisplayonEntity(event.getEntity(), damageType, event.getFinalDamage());
	}
	
	@EventHandler
	public void onEntityHealEvent (EntityRegainHealthEvent event)
	{
		// Do not run if the amount is negative or the server mode is running in Minigame mode (as this might give people's positions away in UHC)
		if (event.getAmount() < 0.0 || AGCraftPlugin.plugin.serverMode != ServerMode.NORMAL ||
				!this.allowedRegainReasons.contains(event.getRegainReason()))
		{
			return;
		}
		
		DamageType damageType = DamageType.HEALING;
		this.damageDisplayManager.createDamageDisplayonEntity(event.getEntity(), damageType, event.getAmount());
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
				if (equipment.getMonsterType() == MonsterType.DAMAGE_DISPLAY_ENTITY)
				{
					this.damageDisplayManager.removeDamageDisplayEntity(entity.getUniqueId());
				}
			}
		}
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
	public void loadConfigVarialbes(ServerConfigFile config) 
	{
		
	}
}
