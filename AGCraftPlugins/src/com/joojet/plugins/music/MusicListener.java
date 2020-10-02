package com.joojet.plugins.music;

import java.util.ArrayList;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

import com.joojet.plugins.music.enums.MusicType;
import com.joojet.plugins.music.player.SoundPlayer;

public class MusicListener implements Listener
{
	/** Key used to identify the music volume controller listed in the config file */
	public static final String musicVolumeTag = "music-volume";
	/** Key used to identify the fireworks music volume listed in the config file */
	public static final String fireworksMusicVolumeTag = "fireworks-music-volume";
	/** Stores an instance to the sound player managed by the Music Listener */
	public SoundPlayer soundPlayer;
	/** Stores the music volume used for the soundPlayer */
	public float musicVolume = 0.50f;
	/** Stores the firework music volume used for fireworks shows*/
	public float fireworkMusicVolume = 0.60f;
	
	public MusicListener ()
	{
		this.soundPlayer = new SoundPlayer (this);
	}
	
	
	/** Listens to player quit events and removes any active songs tied to them */
	@EventHandler
	public void onPlayerLeave (PlayerQuitEvent event)
	{
		this.soundPlayer.removeSoundTaskFromTable(event.getPlayer().getUniqueId());
	}
	
	/** Sets the boss music's volume to a new value
	 * 	@param volume - New volume used for played music */
	public void setMusicVolume (Double volume)
	{
		this.musicVolume = volume.floatValue();
	}
	
	/** Sets the firework show's music volume to a new value
	 * 	@param volume - New volume used for played music */
	public void setFireworkMusicVolume (Double volume)
	{
		this.fireworkMusicVolume = volume.floatValue();
	}
	
	public static Object[] getFireworkMusicTypes ()
	{
		ArrayList <MusicType> types = new ArrayList <MusicType> ();
		for (MusicType type : MusicType.values())
		{
			if (!type.hasEndingTheme())
			{
				types.add(type);
			}
		}
		
		return types.toArray();
	}
}
