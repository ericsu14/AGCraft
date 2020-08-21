package com.joojet.plugins.mobs.bossbar;

import java.util.UUID;

import org.bukkit.boss.BossBar;
import org.bukkit.entity.LivingEntity;

public class BossBarNode 
{
	public UUID uuid;
	public BossBar bossBar;
	public LivingEntity entity;
	public BossBarTask task;
	
	public BossBarNode (BossBar bossBar, LivingEntity livingEntity, UUID bossUUID)
	{
		this.bossBar = bossBar;
		this.entity = livingEntity;
		this.uuid = bossUUID;
		this.task = null;
	}
	
	public void setTask (BossBarTask task)
	{
		this.task = task;
	}
	
	public boolean hasActiveTask ()
	{
		return this.task != null && this.task.isCancelled() == false;
	}
}
