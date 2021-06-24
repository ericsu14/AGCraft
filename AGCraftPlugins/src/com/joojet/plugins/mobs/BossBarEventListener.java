package com.joojet.plugins.mobs;

import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Monster;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.EntityResurrectEvent;

import com.joojet.plugins.agcraft.enums.ServerMode;
import com.joojet.plugins.agcraft.main.AGCraftPlugin;
import com.joojet.plugins.mobs.bossbar.BossBarController;
import com.joojet.plugins.mobs.chunk.interfaces.ChunkUnloadHandler;
import com.joojet.plugins.mobs.interpreter.MonsterTypeInterpreter;
import com.joojet.plugins.mobs.monsters.MobEquipment;

public class BossBarEventListener implements Listener, ChunkUnloadHandler
{
	/** A reference to the boss bar controller defined in main */
	protected BossBarController bossBarController;
	/** Search trie used to lookup custom monsters by name */
	protected MonsterTypeInterpreter monsterTypeInterpreter;
	
	public BossBarEventListener (MonsterTypeInterpreter monsterTypeInterpreter, BossBarController bossBarController)
	{
		this.monsterTypeInterpreter = monsterTypeInterpreter;
		this.bossBarController = bossBarController;
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
			this.bossBarController.addPlayerToBossBar((Player)event.getDamager(), entity); 
		}
		
		// Do the same as above for player projectiles as well
		else if (event.getDamager() instanceof Projectile)
		{
			Projectile projectile = (Projectile) event.getDamager();
			if (projectile.getShooter() instanceof Player)
			{
				Player shooter = (Player) projectile.getShooter();
				this.bossBarController.addPlayerToBossBar(shooter, entity);
				// Causes the entity to hunt him down if attacked by player arrow
				if (entity instanceof Monster && AGCraftPlugin.plugin.serverMode == ServerMode.NORMAL)
				{
					Monster mob = (Monster) entity;
					MobEquipment mobEquipment = this.monsterTypeInterpreter.getMobEquipmentFromEntity(entity);
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
			this.bossBarController.createBossBar((LivingEntity) event.getEntity());
		}
	}
	
	/** Removes an entity's boss bar upon entity death */
	@EventHandler
	public void onEntityDeath (EntityDeathEvent event)
	{
		if (event.getEntity() instanceof LivingEntity)
		{
			LivingEntity entity = (LivingEntity) event.getEntity();
			this.bossBarController.removeBossBar(entity);
		}
	}
	
	/** Removes active custom boss bars on captured chunk unload events
	 * 	@param entity Entity being checked for boss bars */
	@Override
	public void processEntityOnChunkUnload(Entity entity) 
	{
		if (entity instanceof LivingEntity)
		{
			LivingEntity livingEntity = (LivingEntity) entity;
			this.bossBarController.removeBossBar(livingEntity);
		}
	}
}
