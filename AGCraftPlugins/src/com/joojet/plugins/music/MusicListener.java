package com.joojet.plugins.music;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

import com.joojet.plugins.music.player.SoundPlayer;

public class MusicListener implements Listener
{
	/** Key used to identify the music volume controller listed in the config file */
	public static final String musicVolumeTag = "music-volume";
	/** Stores an instance to the sound player managed by the Music Listener */
	public static SoundPlayer soundPlayer;
	/** Stores the music volume used for the soundPlayer */
	public static float musicVolume = 0.60f;
	
	public MusicListener ()
	{
		soundPlayer = new SoundPlayer ();
	}
	
	
	/** Listens to player quit events and removes any active songs tied to them */
	@EventHandler
	public void onPlayerLeave (PlayerQuitEvent event)
	{
		soundPlayer.removeSoundTaskFromTable(event.getPlayer().getUniqueId());
	}
	
	/** Sets the played music's volume to a new value
	 * 	@param volume - New volume used for played music */
	public static void setMusicVolume (Double volume)
	{
		musicVolume = volume.floatValue();
	}
}
