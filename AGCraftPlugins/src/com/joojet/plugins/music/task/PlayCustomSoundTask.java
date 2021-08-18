package com.joojet.plugins.music.task;

import java.util.HashSet;
import java.util.UUID;

import org.bukkit.entity.Player;

import com.joojet.plugins.music.enums.MusicType;
import com.joojet.plugins.music.enums.SoundPlayerState;
import com.joojet.plugins.music.player.SoundPlayer;

public class PlayCustomSoundTask
{
	/** The player */
	protected Player player;
	/** Stores the UUID of the player */
	protected UUID playerUUID;
	/** The type of music currently being played to the player */
	protected MusicType musicType;
	/** A reference to the soundplayer instance */
	protected SoundPlayer musicPlayer;
	/** Current state of the sound player */
	protected SoundPlayerState state;
	/** Set of entities attached to the currently playing boss music */
	protected HashSet <UUID> attachedBossEntities;
	/** Internal timer keeping track of the total amount of seconds left for the musicType's duration */
	protected int timer;
	
	public PlayCustomSoundTask (Player player, MusicType musicType, SoundPlayer musicPlayer, SoundPlayerState state)
	{
		this.player = player;
		this.playerUUID = player.getUniqueId();
		this.musicType = musicType;
		this.musicPlayer = musicPlayer;
		this.state = state;
		this.attachedBossEntities = new HashSet <UUID> ();
		this.timer = musicType.duration().getTicks() / 20;
	}
	
	/** This task will run after a set delay which spans for the entire duration of the music.
	 *  Once this task is ran (after the song stops playing), this task will remove itself from
	 *  the passed musicplayer's active songs table. */
	public void update () 
	{
		if (this.timer >= 0)
		{
			--this.timer;
		}
	}
	
	public Player getPlayer ()
	{
		return this.player;
	}
	
	public UUID getPlayerUUID ()
	{
		return this.playerUUID;
	}
	
	public boolean isFinished ()
	{
		return this.timer <= 0;
	}
	
	public MusicType getMusicType ()
	{
		return this.musicType;
	}
	
	/** Returns the current time remaining for the music being played (in seconds) */
	public int getTimer ()
	{
		return this.timer;
	}
	
	/** Returns the current state of this sound player task */
	public SoundPlayerState getSoundPlayerState ()
	{
		return this.state;
	}
	
	/** Sets the task's sound player state to a new state.
	 *  This also updates the task's internal timer. */
	public void setSoundPlayerState (SoundPlayerState state)
	{
		this.state = state;
		this.resetTimer();
	}
	
	public void resetTimer ()
	{
		// Updates internal timer to ending music if one exists
		if (this.state == SoundPlayerState.ENDING)
		{
			this.timer = this.musicType.getEndTheme().duration().getTicks() / 20;
		}
		else
		{
			this.timer = this.musicType.duration().getTicks() / 20;
		}
	}
	
	public void attachBossEntity (UUID bossUUID)
	{
		this.attachedBossEntities.add(bossUUID);
	}
	
	public void removeAttachedBossEntity (UUID bossUUID)
	{
		this.attachedBossEntities.remove(bossUUID);
	}
	
	public int getAttachedBossEntityCount()
	{
		return this.attachedBossEntities.size();
	}
}
