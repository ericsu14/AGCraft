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
			double entityHealth = entity.getHealth() / entity.getAttribute(Attribute.GENERIC_MAX_HEALTH).getValue();
			bossBar.setProgress(entityHealth);
		}
		else
		{
			bossBar.removeAll();
			bossBar.setVisible(false);
			BossBarAPI.activeBossBars.remove(bossUUID);
			this.cancel();
			System.out.println ("Active entries: " + BossBarAPI.activeBossBars.size());
		}
	}

}
