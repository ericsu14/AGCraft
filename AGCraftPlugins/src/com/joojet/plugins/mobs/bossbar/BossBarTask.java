package com.joojet.plugins.mobs.bossbar;

import java.util.UUID;

import org.bukkit.attribute.Attribute;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BossBar;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffectType;

import com.joojet.plugins.music.MusicListener;
import com.joojet.plugins.music.enums.MusicType;

public class BossBarTask
{
	/** Boss bar instance */
	protected BossBar bossBar;
	/** Boss entity  */
	protected LivingEntity bossEntity;
	/** Boss's custom music theme */
	protected MusicType bossTheme;
	/** True if the underlying entity this task is managing is still active in the game */
	protected boolean isActive;
	/** UUID of the Boss Bar entry */
	protected UUID bossUUID;
	/** Stores a reference to the music listener used to enable and disable
	 *  music cues for different boss fight events */
	protected MusicListener musicListener;
	/** Keeps track of the max. absorption health this monster has */
	protected double maxAbsorptionHealth;
	/** Tracks if this entity has absorption health */
	protected boolean hasAbsorption;
	
	/** Constructs a new BossBarTask containing data needed to manage a BossEntity's boss bar
	 *  @param bossBar BossBar instance
	 *  @param bossEntity Underlying LivingEntity used to update the entity's own BossBar
	 *  @param bossTheme Boss entity's boss theme
	 *  @param musicListener A reference to the MusicListener instance used to stop boss music */
	public BossBarTask (BossBar bossBar, LivingEntity bossEntity, MusicType bossTheme, MusicListener musicListener)
	{
		this.bossEntity = bossEntity;
		this.bossUUID = bossEntity.getUniqueId();
		this.bossBar = bossBar;
		this.musicListener = musicListener;
		this.maxAbsorptionHealth = 0.0;
		this.hasAbsorption = false;
		this.isActive = true;
	}
	
	/** Update method called on each tick */
	public void update() 
	{
		
		if (this.bossEntity != null && !this.bossEntity.isDead())
		{
			this.hasAbsorption = bossEntity.hasPotionEffect(PotionEffectType.ABSORPTION);
			// Scales boss bar's progress to the entity's currently health + any absorption bonuses
			double currentHealth = bossEntity.getHealth() + bossEntity.getAbsorptionAmount();
			double totalHealth = bossEntity.getAttribute(Attribute.GENERIC_MAX_HEALTH).getValue() + this.maxAbsorptionHealth;
			// If the entity has absorption and its combined health exceeds its generic max health, scale the total health with the max absorption health
			if (this.hasAbsorption && currentHealth > totalHealth)
			{
				this.maxAbsorptionHealth = Math.max(this.maxAbsorptionHealth, this.bossEntity.getAbsorptionAmount());
			}
			double entityHealth = currentHealth / totalHealth;
			// Caps entityHealth at 1.00
			entityHealth = (entityHealth > 1.00) ? 1.00 : entityHealth;
			bossBar.setColor(this.hasAbsorption ? BarColor.YELLOW : BarColor.RED);
			bossBar.setProgress(entityHealth);
		}
		else
		{
			this.isActive = false;
		}
	}
	
	/** Remaps the boss entity this task is managing to a new LivingEntity instance */
	public void setBossEntity (LivingEntity bossEntity)
	{
		this.bossEntity = bossEntity;
		this.isActive = true;
	}
	
	/** Gets the LivingEntity governed by this BossBarTask */
	public LivingEntity getBossEntity ()
	{
		return this.bossEntity;
	}
	
	/** Returns the UUID associated with the boss entity */
	public UUID getBossUUID ()
	{
		return this.bossUUID;
	}
	
	/** Adds a player to the boss entity's BossBar instance, allowing the BossBar
	 *  to be seen by that player
	 *  @param player Player to be added into the boss entity's boss bar */
	public void addPlayerToBossBar (Player player)
	{
		this.bossBar.addPlayer(player);
	}
	
	/** Removes a player to the boss entity's BossBar instance, therefore disallowing
	 *  the player from seeing the boss entity's boss bar
	 *  @param player Player to be removed from the boss entity's boss bar */
	public void removePlayerFromBossBar (Player player)
	{
		this.bossBar.removePlayer(player);
	}
	
	/** Disables this task's active boss bar */
	public void cleanup ()
	{
		BossBar bossBar = this.bossBar;
		// Removes all active boss sounds from each player registered in the boss bar.
		if (this.bossTheme != null)
		{
			for (Player player : bossBar.getPlayers())
			{
				if (player != null)
				{
					this.musicListener.soundPlayer.stopCurrentlyPlayingBossMusicOnPlayer(this.bossTheme, player, bossUUID);
				}
			}
		}
		bossBar.removeAll();
		bossBar.setVisible(false);
	}
	
	/** Gets the BossBarTask's current status, indicating if the underlying boss entity is active
	 *  or inactive and should be marked for removal. */
	public boolean isActive ()
	{
		return this.isActive;
	}

}
