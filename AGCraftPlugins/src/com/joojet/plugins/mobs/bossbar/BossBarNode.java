package com.joojet.plugins.mobs.bossbar;

import java.util.UUID;

import org.bukkit.boss.BossBar;
import org.bukkit.entity.LivingEntity;

import com.joojet.plugins.music.enums.MusicType;

public class BossBarNode 
{
	/** UUID for this Boss Bar Entry */
	public UUID uuid;
	/** Boss bar instance */
	public BossBar bossBar;
	/** The Living Entity the boss bar is tied to */
	public LivingEntity entity;
	/** The active runnable that is keeping track of the Boss Bar's health */
	public BossBarTask task;
	/** The entity's boss theme */
	public MusicType bossTheme;
	
	public BossBarNode (BossBar bossBar, LivingEntity livingEntity, UUID bossUUID, MusicType bossTheme)
	{
		this.bossBar = bossBar;
		this.entity = livingEntity;
		this.uuid = bossUUID;
		this.bossTheme = bossTheme;
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
