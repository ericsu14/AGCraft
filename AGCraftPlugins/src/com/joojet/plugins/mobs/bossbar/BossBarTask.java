package com.joojet.plugins.mobs.bossbar;

import java.util.UUID;

import org.bukkit.attribute.Attribute;
import org.bukkit.boss.BossBar;
import org.bukkit.entity.LivingEntity;
import org.bukkit.scheduler.BukkitRunnable;

public class BossBarTask extends BukkitRunnable 
{
	public UUID bossUUID;
	public BossBarNode bossBarNode;
	public LivingEntity entity;
	protected BossBar bossBar;
	
	public BossBarTask (BossBarNode bossBarNode)
	{
		this.bossBarNode = bossBarNode;
		this.bossUUID = this.bossBarNode.uuid;
		this.entity = this.bossBarNode.entity;
		this.bossBar = this.bossBarNode.bossBar;
		this.bossBarNode.setTask(this);
	}
	
	@Override
	public void run() 
	{
		if (!entity.isDead())
		{
			double entityHealth = this.bossBarNode.entity.getHealth() / this.bossBarNode.entity.getAttribute(Attribute.GENERIC_MAX_HEALTH).getValue();
			this.bossBarNode.bossBar.setProgress(entityHealth);
		}
		else
		{
			this.bossBarNode.bossBar.removeAll();
			this.bossBarNode.bossBar.setVisible(false);
			BossBarAPI.activeBossBars.remove(bossUUID);
			this.cancel();
			System.out.println ("Active entries: " + BossBarAPI.activeBossBars.size());
		}
	}

}
