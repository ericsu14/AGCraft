package com.joojet.plugins.music.task;

import java.util.UUID;

import org.bukkit.scheduler.BukkitRunnable;

import com.joojet.plugins.music.enums.MusicType;
import com.joojet.plugins.music.player.SoundPlayer;

public class PlayCustomSoundTask extends BukkitRunnable 
{
	protected UUID playerUUID;
	
	protected MusicType musicType;
	
	protected SoundPlayer musicPlayer;
	
	public PlayCustomSoundTask (UUID playerUUID, MusicType musicType, SoundPlayer musicPlayer)
	{
		this.playerUUID = playerUUID;
		this.musicType = musicType;
		this.musicPlayer = musicPlayer;
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

}
