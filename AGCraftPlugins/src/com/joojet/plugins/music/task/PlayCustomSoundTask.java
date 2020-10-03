package com.joojet.plugins.music.task;

import java.util.HashSet;
import java.util.UUID;

import org.bukkit.scheduler.BukkitRunnable;

import com.joojet.plugins.music.enums.MusicType;
import com.joojet.plugins.music.enums.SoundPlayerState;
import com.joojet.plugins.music.player.SoundPlayer;

public class PlayCustomSoundTask extends BukkitRunnable 
{
	/** The player's UUID */
	protected UUID playerUUID;
	/** The type of music currently being played to the player */
	protected MusicType musicType;
	/** A reference to the soundplayer instance */
	protected SoundPlayer musicPlayer;
	/** Current state of the sound player */
	protected SoundPlayerState state;
	/** Set of entities attached to the currently playing boss music */
	protected HashSet <UUID> attachedBossEntities;
	
	public PlayCustomSoundTask (UUID playerUUID, MusicType musicType, SoundPlayer musicPlayer, SoundPlayerState state)
	{
		this.playerUUID = playerUUID;
		this.musicType = musicType;
		this.musicPlayer = musicPlayer;
		this.state = state;
		this.attachedBossEntities = new HashSet <UUID> ();
	}
	
	/** This task will run after a set delay which spans for the entire duration of the music.
	 *  Once this task is ran (after the song stops playing), this task will remove itself from
	 *  the passed musicplayer's active songs table. */
	@Override
	public void run() 
	{
		this.musicPlayer.removeSoundTaskFromTable(this.playerUUID);
	}
	
	public MusicType getMusicType ()
	{
		return this.musicType;
	}
	
	/** Returns the current state of this sound player task */
	public SoundPlayerState getSoundPlayerState ()
	{
		return this.state;
	}
	
	/** Sets the task's sound player state to a new state */
	public void setSoundPlayerState (SoundPlayerState state)
	{
		this.state = state;
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
