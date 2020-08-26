package com.joojet.plugins.mobs;

import org.bukkit.Bukkit;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.event.entity.EntityRegainHealthEvent;
import org.bukkit.event.world.ChunkLoadEvent;
import org.bukkit.event.world.ChunkUnloadEvent;
import org.bukkit.plugin.Plugin;

import com.joojet.plugins.agcraft.enums.ServerMode;
import com.joojet.plugins.agcraft.main.AGCraftPlugin;
import com.joojet.plugins.mobs.damage.DamageDisplayManager;
import com.joojet.plugins.mobs.damage.enums.DamageType;
import com.joojet.plugins.mobs.enums.MonsterType;
import com.joojet.plugins.mobs.monsters.MobEquipment;

public class DamageDisplayListener implements Listener 
{
	protected DamageDisplayManager damageDisplayManager;
	
	public DamageDisplayListener ()
	{
		damageDisplayManager = new DamageDisplayManager ();
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
		if (AGCraftPlugin.plugin.serverMode != ServerMode.NORMAL)
		{
			return;
		}
		
		DamageType damageType = DamageType.NORMAL;
		
		// Checks for mob hits dealt by player inflicted critical hits
		if (event.getDamager().getType() == EntityType.PLAYER)
		{
			damageType = this.damageDisplayManager.checkCriticalHit((Player) event.getDamager()) ? DamageType.CRITICAL : DamageType.NORMAL;
		}
		// Checks for mob hits dealt by allies
		else if (event.getDamager() instanceof LivingEntity)
		{
			MobEquipment equipment = AmplifiedMobSpawner.getMobEquipmentFromEntity((LivingEntity) event.getDamager());
			if (equipment != null && equipment.getIgnoreList().contains(EntityType.PLAYER))
			{
				damageType = DamageType.ALLIED;
			}
		}
		else if (event.getCause() == DamageCause.ENTITY_EXPLOSION)
		{
			damageType = DamageType.EXPLOSION;
		}
		else if (event.getCause() == DamageCause.MAGIC)
		{
			damageType = DamageType.MAGIC;
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
				|| AGCraftPlugin.plugin.serverMode != ServerMode.NORMAL)
		{
			return;
		}
		
		DamageType damageType = DamageType.NORMAL;
		switch (event.getCause())
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
			case DROWNING:
				damageType = DamageType.DROWNING;
				break;
			case WITHER:
				damageType = DamageType.WITHER;
				break;
			case BLOCK_EXPLOSION:
				damageType = DamageType.EXPLOSION;
			default:
				break;
		}
		this.damageDisplayManager.createDamageDisplayonEntity(event.getEntity(), damageType, event.getFinalDamage());
	}
	
	@EventHandler
	public void onEntityHealEvent (EntityRegainHealthEvent event)
	{
		// Do not run if the amount is negative or the server mode is running in Minigame mode (as this might give people's positions away in UHC)
		if (event.getAmount() < 0.0 || AGCraftPlugin.plugin.serverMode != ServerMode.NORMAL)
		{
			return;
		}
		
		DamageType damageType = DamageType.HEALING;
		this.damageDisplayManager.createDamageDisplayonEntity(event.getEntity(), damageType, event.getAmount());
	}
	
	/** Removes any damage display entity still persistent upon chunk loads */
	@EventHandler
	public void onChunkLoad (ChunkLoadEvent event)
	{
		this.removeDamageDisplayEntities(event.getChunk().getEntities());
	}
	
	/** Removes any damage display entities still persistent upon chunk unloads */
	@EventHandler
	public void onChunkUnload (ChunkUnloadEvent event)
	{
		this.removeDamageDisplayEntities(event.getChunk().getEntities());
	}
	
	private void removeDamageDisplayEntities (Entity[] entityList)
	{
		for (Entity entity : entityList)
		{
			if (entity.getType() == EntityType.ARMOR_STAND)
			{
				MobEquipment equipment = AmplifiedMobSpawner.getMobEquipmentFromEntity((LivingEntity) entity);
				if (equipment.getMonsterType() == MonsterType.DAMAGE_DISPLAY_ENTITY)
				{
					this.damageDisplayManager.removeDamageDisplayEntity(entity.getUniqueId());
				}
			}
		}
	}
}
