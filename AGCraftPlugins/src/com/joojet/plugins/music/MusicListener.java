package com.joojet.plugins.music;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

import com.joojet.plugins.music.player.SoundPlayer;

public class MusicListener implements Listener
{
	/** Stores an instance to the sound player managed by the Music Listener */
	public static SoundPlayer soundPlayer;
	
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
}
