package com.joojet.plugins.mobs.bossbar;

import java.util.UUID;

import org.bukkit.attribute.Attribute;
import org.bukkit.boss.BossBar;
import org.bukkit.entity.LivingEntity;
import org.bukkit.scheduler.BukkitRunnable;

public class BossBarTask extends BukkitRunnable 
{
	/** UUID of the Boss Bar entry */
	protected UUID bossUUID;
	/** Instance of the Boss Bar entry attached to this task */
	protected BossBarNode bossBarNode;
	
	public BossBarTask (BossBarNode bossBarNode)
	{
		this.bossBarNode = bossBarNode;
		this.bossUUID = this.bossBarNode.uuid;
		this.bossBarNode.setTask(this);
	}
	
	@Override
	public void run() 
	{
		BossBar bossBar = this.bossBarNode.bossBar;
		LivingEntity entity = this.bossBarNode.entity;
		
		if (entity != null && !entity.isDead())
		{
			// Scales boss bar's progress to the entity's currently health + any absorption bonuses
			double entityHealth = (entity.getHealth() + entity.getAbsorptionAmount()) /
					entity.getAttribute(Attribute.GENERIC_MAX_HEALTH).getValue();
			bossBar.setProgress(entityHealth);
		}
		else
		{
			this.cleanup();
			BossBarAPI.activeBossBars.remove(bossUUID);
			this.cancel();
		}
	}
	
	/** Disables this task's active boss bar */
	public void cleanup ()
	{
		BossBar bossBar = this.bossBarNode.bossBar;
		bossBar.removeAll();
		bossBar.setVisible(false);
	}

}
