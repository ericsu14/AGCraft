package com.joojet.plugins.mobs;

import org.bukkit.Bukkit;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Monster;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.EntityResurrectEvent;
import org.bukkit.event.world.ChunkLoadEvent;
import org.bukkit.event.world.ChunkUnloadEvent;
import org.bukkit.plugin.Plugin;

import com.joojet.plugins.agcraft.enums.ServerMode;
import com.joojet.plugins.agcraft.main.AGCraftPlugin;
import com.joojet.plugins.mobs.bossbar.BossBarAPI;
import com.joojet.plugins.mobs.enums.MobFlag;
import com.joojet.plugins.mobs.monsters.MobEquipment;

public class BossBarEventListener implements Listener 
{
	public void onEnable ()
	{
		Bukkit.getPluginManager().registerEvents(this, (Plugin) this);
	}
	
	/** Captures entity damage events and adds the Player to the enemy's boss bar if it has one */
	@EventHandler
	public void addPlayerToBossBarOnAttack (EntityDamageByEntityEvent event)
	{
		if (!(event.getEntity() instanceof LivingEntity))
		{
			return;
		}
		LivingEntity entity = (LivingEntity) event.getEntity();
		
		// Adds a player to an entity's boss bar if it has boss metadata
		if (event.getDamager() instanceof Player)
		{
			BossBarAPI.addPlayerToBossBar((Player)event.getDamager(), entity); 
		}
		
		// Do the same as above for player projectiles as well
		else if (event.getDamager() instanceof Projectile)
		{
			Projectile projectile = (Projectile) event.getDamager();
			if (projectile.getShooter() instanceof Player)
			{
				Player shooter = (Player) projectile.getShooter();
				BossBarAPI.addPlayerToBossBar(shooter, entity);
				// Causes the entity to hunt him down if attacked by player arrow
				if (entity instanceof Monster && AGCraftPlugin.plugin.serverMode == ServerMode.NORMAL)
				{
					Monster mob = (Monster) entity;
					MobEquipment mobEquipment = AmplifiedMobSpawner.getMobEquipmentFromEntity(entity);
					if (mobEquipment == null || !mobEquipment.getIgnoreList().contains(shooter.getType()))
					{
						mob.setTarget(shooter);
					}
				}
			}
		}
	}
	
	/** Captures entity resurrect events and attempt to set its Boss Bar instance 
	 *  to the resurrected entity if it exists */
	@EventHandler
	public void recreateBossBarOnResurrectEvent (EntityResurrectEvent event)
	{
		if (event.getEntity() instanceof LivingEntity && !event.isCancelled())
		{
			BossBarAPI.createBossBar((LivingEntity) event.getEntity());
		}
	}
	
	/** Recreates custom boss bars upon chunk load events */
	@EventHandler
	public void createBossBarsOnChunkLoad (ChunkLoadEvent event)
	{
		Entity[] chunkEntities = event.getChunk().getEntities();
		LivingEntity livingEntity;
		
		for (Entity ent : chunkEntities)
		{
			if (ent instanceof LivingEntity && ent.getType() != EntityType.PLAYER)
			{
				livingEntity = (LivingEntity) ent;
				MobEquipment equipment = AmplifiedMobSpawner.getMobEquipmentFromEntity(livingEntity);
				if (equipment != null && equipment.getMobFlags().contains(MobFlag.BOSS_BAR))
				{
					BossBarAPI.createBossBar(livingEntity);
				}
			}
		}
	}
	
	/** Removes active custom boss bars on captured chunk unload events */
	@EventHandler 
	public void removeBossBarsOnChunkUnload (ChunkUnloadEvent event)
	{
		Entity[] chunkEntities = event.getChunk().getEntities();
		
		LivingEntity livingEntity;
		
		for (Entity ent : chunkEntities)
		{
			if (ent instanceof LivingEntity)
			{
				livingEntity = (LivingEntity) ent;
				BossBarAPI.removeBossBar(livingEntity);
			}
		}
	}
	
	/** Removes an entity's boss bar upon entity death */
	@EventHandler
	public void onEntityDeath (EntityDeathEvent event)
	{
		if (event.getEntity() instanceof LivingEntity)
		{
			LivingEntity entity = (LivingEntity) event.getEntity();
			BossBarAPI.removeBossBar(entity);
		}
	}
}
