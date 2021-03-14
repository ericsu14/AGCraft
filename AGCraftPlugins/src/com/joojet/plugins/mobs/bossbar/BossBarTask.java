package com.joojet.plugins.mobs.bossbar;

import java.util.UUID;

import org.bukkit.attribute.Attribute;
import org.bukkit.boss.BarColor;
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
	/** Keeps track of the max. absorption health this monster has */
	private double maxAbsorptionHealth;
	/** Tracks if this entity has absorption health */
	private boolean hasAbsorption;
	
	public BossBarTask (BossBarNode bossBarNode, BossBarController bossBarController, MusicListener musicListener)
	{
		this.bossBarNode = bossBarNode;
		this.bossUUID = this.bossBarNode.uuid;
		this.bossBarNode.setTask(this);
		this.bossBarController = bossBarController;
		this.musicListener = musicListener;
		this.maxAbsorptionHealth = 0.0;
		this.hasAbsorption = false;
	}
	
	@Override
	public void run() 
	{
		BossBar bossBar = this.bossBarNode.bossBar;
		LivingEntity entity = this.bossBarNode.entity;
		
		if (entity != null && !entity.isDead())
		{
			this.hasAbsorption = entity.getAbsorptionAmount() > 0.0;
			// Scales boss bar's progress to the entity's currently health + any absorption bonuses
			double currentHealth = entity.getHealth() + entity.getAbsorptionAmount();
			double totalHealth = entity.getAttribute(Attribute.GENERIC_MAX_HEALTH).getValue() + this.maxAbsorptionHealth;
			// If the entity has absorption and its combined health exceeds its generic max health, scale the total health with the max absorption health
			if (this.hasAbsorption && currentHealth > totalHealth)
			{
				this.maxAbsorptionHealth = Math.max(this.maxAbsorptionHealth, entity.getAbsorptionAmount());
			}
			double entityHealth = currentHealth / totalHealth;
			// Caps entityHealth at 1.00
			entityHealth = (entityHealth > 1.00) ? 1.00 : entityHealth;
			bossBar.setColor(this.hasAbsorption ? BarColor.YELLOW : BarColor.RED);
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
					this.musicListener.soundPlayer.stopCurrentlyPlayingBossMusicOnPlayer(this.bossBarNode.bossTheme, player, bossUUID);
				}
			}
		}
		bossBar.removeAll();
		bossBar.setVisible(false);
	}

}
