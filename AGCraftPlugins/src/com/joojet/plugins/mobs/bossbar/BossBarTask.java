package com.joojet.plugins.mobs.bossbar;

import java.util.UUID;

import org.bukkit.attribute.Attribute;
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
		if (this.bossBarNode.entity != null && !this.bossBarNode.entity.isDead())
		{
			double entityHealth = (this.bossBarNode.entity.getHealth() + this.bossBarNode.entity.getAbsorptionAmount()) /
					this.bossBarNode.entity.getAttribute(Attribute.GENERIC_MAX_HEALTH).getValue();
			this.bossBarNode.bossBar.setProgress(entityHealth);
		}
		else
		{
			this.bossBarNode.bossBar.removeAll();
			this.bossBarNode.bossBar.setVisible(false);
			BossBarAPI.activeBossBars.remove(bossUUID);
			this.cancel();
		}
	}

}
