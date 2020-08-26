package com.joojet.plugins.mobs;

import org.bukkit.Bukkit;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.world.ChunkLoadEvent;
import org.bukkit.event.world.ChunkUnloadEvent;
import org.bukkit.plugin.Plugin;

import com.joojet.plugins.mobs.damage.DamageDisplayManager;
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
	public void onEntityDamageEvent (EntityDamageByEntityEvent event)
	{
		this.damageDisplayManager.createDamageDisplayonEntity(event);
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
