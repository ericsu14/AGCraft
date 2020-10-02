package com.joojet.plugins.mobs.bossbar;

import java.util.UUID;

import org.bukkit.attribute.Attribute;
import org.bukkit.boss.BossBar;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import com.joojet.plugins.music.MusicListener;

public class BossBarTask extends BukkitRunnable 
{
	/** UUID of the Boss Bar entry */
	protected UUID bossUUID;
	/** Instance of the Boss Bar entry attached to this task */
	protected BossBarNode bossBarNode;
	/** An instance to the boss bar controller */
	protected BossBarController bossBarController;
	/** Stores a reference to the music listener used to enable and disable
	 *  music cues for different boss fight events */
	protected MusicListener musicListener;
	
	public BossBarTask (BossBarNode bossBarNode, BossBarController bossBarController, MusicListener musicListener)
	{
		this.bossBarNode = bossBarNode;
		this.bossUUID = this.bossBarNode.uuid;
		this.bossBarNode.setTask(this);
		this.bossBarController = bossBarController;
		this.musicListener = musicListener;
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
			this.bossBarController.activeBossBars.remove(bossUUID);
			this.cancel();
		}
	}
	
	/** Disables this task's active boss bar */
	public void cleanup ()
	{
		BossBar bossBar = this.bossBarNode.bossBar;
		// Removes all active boss sounds from each player registered in the boss bar.
		if (this.bossBarNode.bossTheme != null)
		{
			for (Player player : bossBar.getPlayers())
			{
				if (player != null)
				{
					this.musicListener.soundPlayer.stopCurrentlyPlayingSoundOnPlayer(this.bossBarNode.bossTheme, player);
				}
			}
		}
		bossBar.removeAll();
		bossBar.setVisible(false);
	}

}
