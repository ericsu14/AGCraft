package com.joojet.plugins.music.player;

import java.util.HashMap;
import java.util.UUID;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import com.joojet.plugins.agcraft.main.AGCraftPlugin;
import com.joojet.plugins.music.MusicListener;
import com.joojet.plugins.music.enums.MusicType;
import com.joojet.plugins.music.enums.SoundPlayerState;
import com.joojet.plugins.music.task.PlayCustomSoundTask;

public class SoundPlayer 
{
	/** Stores a reference to the music listener class */
	protected MusicListener musicListener;
	/** Keeps track of all active sounds being played to each player in the server. */
	protected HashMap <UUID, PlayCustomSoundTask> activePlayerSoundTable;
	
	public SoundPlayer (MusicListener musicListener)
	{
		this.musicListener = musicListener;
		this.activePlayerSoundTable = new HashMap <UUID, PlayCustomSoundTask> ();
	}
	
	/** Attempts to play custom music referenced by MusicType to the player.
	 *  This will not overwrite any music already playing by previous calls to this command.
	 *  No music will be played when the player does not have the listed resource pack.
	 *  @param type - Type of custom music to be played that is specified in the server resource pack
	 *  @param player - Player we are playing the music to.
	 *  */
	public void playCustomMusicNearPlayer(MusicType type, Player player, float musicVolume)
	{
		if (!this.activePlayerSoundTable.containsKey(player.getUniqueId()))
		{
			player.playSound(player.getLocation(), type.getNamespace(), musicVolume, 1.0F);
			PlayCustomSoundTask soundTask = new PlayCustomSoundTask (player.getUniqueId(), type, this);
			this.activePlayerSoundTable.put(player.getUniqueId(), soundTask);
			soundTask.runTaskLater(AGCraftPlugin.plugin, type.duration().getTicks());
		}
	}
	
	/** Plays custom music to all players near a location.
	 *  @param type - Type of custom music to be played that is specified in the server resource pack
	 *  @param location - Location in the world we are playing the music at
	 *  @param radius - Total radius (in blocks) the music will project before fading out  */
	public void playCustomMusicAtLocation (MusicType type, Location location, int radius)
	{
		float convertedRadius = Math.max(1.0f, radius / 15.0f);
		location.getWorld().playSound(location, type.getNamespace(), convertedRadius, 1.0F);
	}
	
	/** Stops the sound currently being played to a player and removes it from the internal 
	 *  active song table so that a new song can be played to that player again.
	 *  
	 *  When a song is successfully stopped, an ending theme is also played for that song
	 *  to add a nice transition.
	 *  @param type - Song being stopped
	 *  @param player - The player the song is being stopped */
	public void stopCurrentlyPlayingSoundOnPlayer (MusicType type, Player player)
	{
		UUID playerUUID = player.getUniqueId();
		if (this.activePlayerSoundTable.containsKey(playerUUID)
				&& this.activePlayerSoundTable.get(playerUUID).getMusicType() == type)
		{
			player.stopSound(type.getNamespace());
			// Attempts to play the music type's ending theme if one exists
			if (type.hasEndingTheme())
			{
				PlayCustomSoundTask task = this.activePlayerSoundTable.get(playerUUID);
				if (task.getSoundPlayerState() == SoundPlayerState.RUNNING)
				{
					task.setSoundPlayerState(SoundPlayerState.ENDING);
					player.playSound(player.getLocation(), type.getEndTheme().getNamespace(), this.musicListener.musicVolume, 1.0F);
					if (!task.isCancelled())
					{
						task.cancel();
						task.runTaskLater(AGCraftPlugin.plugin, type.getEndTheme().duration().getTicks());
					}
				}
			}
			else
			{
				this.removeSoundTaskFromTable(playerUUID);
			}
		}
	}
	
	/** Stops all sounds currently being played to a player and removes it from the internal
	 *  active song table.
	 *  @param - player - The player whose songs are being stopped */
	public void stopAllSoundsNearPlayer (Player player)
	{
		UUID playerUUID = player.getUniqueId();
		if (this.activePlayerSoundTable.containsKey(playerUUID))
		{
			for (MusicType type : MusicType.values())
			{
				player.stopSound(type.getNamespace());
			}
			this.removeSoundTaskFromTable(playerUUID);
		}
	}
	
	/** Removes the sound task entry from the sound player table
	 *  and cancels the task.
	 *  @param uuid - UUID associated with the player who we are removing the active sound from */
	public void removeSoundTaskFromTable (UUID uuid)
	{
		if (this.activePlayerSoundTable != null && this.activePlayerSoundTable.containsKey(uuid))
		{
			PlayCustomSoundTask removedTask = this.activePlayerSoundTable.remove(uuid);
			if (removedTask != null && !removedTask.isCancelled())
			{
				removedTask.cancel();
			}
		}
	}
	
}
