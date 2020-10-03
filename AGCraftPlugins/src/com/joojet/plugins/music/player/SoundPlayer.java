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
	
	/** A wrapper for our custom music playing function that registers a boss entity to the task's internal list of
	 *  attached boss entities if the type is the same as the active music task.
	 *  
	 *  This ensures that any active boss music does not prematurely stop if there are multiple boss entities with the same
	 *  boss theme.*/
	public void playBossMusicNearPlayer (MusicType type, Player player, UUID bossEntityUUID, float musicVolume)
	{
		this.playCustomMusicNearPlayer(type, player, SoundPlayerState.RUNNING, musicVolume);
		
		PlayCustomSoundTask task = this.activePlayerSoundTable.get(player.getUniqueId());
		if (task.getMusicType() == type)
		{
			task.attachBossEntity(bossEntityUUID);
		}
	}
	
	/** Attempts to play custom music referenced by MusicType to the player.
	 *  This will not overwrite any music already playing by previous calls to this command.
	 *  No music will be played when the player does not have the listed resource pack.
	 *  @param type - Type of custom music to be played that is specified in the server resource pack
	 *  @param player - Player we are playing the music to.
	 *  @param state - Initial state the sound task should be in
	 *  @param musicVolume - Controls how loud the music should be played to the player */
	public void playCustomMusicNearPlayer(MusicType type, Player player, SoundPlayerState state, float musicVolume)
	{
		if (!this.activePlayerSoundTable.containsKey(player.getUniqueId()))
		{
			player.playSound(player.getLocation(), type.getNamespace(), musicVolume, 1.0F);
			PlayCustomSoundTask soundTask = new PlayCustomSoundTask (player.getUniqueId(), type, this, state);
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
	
	
	/** Attempts to stop the currently playing boss music on player if there are no active boss entities remaining
	 *  that are tied to the activly playing boss music. If so, the boss music's ending theme will be played.
	 *  @param type - Song being stopped
	 *  @param player - The player the song is being stopped
	 *  @param bossUUID - Boss we are removing from the music task */
	public void stopCurrentlyPlayingBossMusicOnPlayer (MusicType type, Player player, UUID bossUUID)
	{
		UUID playerUUID = player.getUniqueId();
		
		if (this.activePlayerSoundTable.containsKey(playerUUID))
		{
			PlayCustomSoundTask task = this.activePlayerSoundTable.get(playerUUID);
			
			if (task.getMusicType() != type || task.getSoundPlayerState() != SoundPlayerState.RUNNING)
			{
				return;
			}
			
			// Removes the boss entity from the task's internal list of attached boss entities
			task.removeAttachedBossEntity(bossUUID);
			
			// If there are no more boss entities attached to this task, play the boss theme's ending music
			if (task.getAttachedBossEntityCount() == 0)
			{
				player.stopSound(type.getNamespace());
				if (type.hasEndingTheme())
				{
					task.setSoundPlayerState(SoundPlayerState.ENDING);
					player.playSound(player.getLocation(), type.getEndTheme().getNamespace(), this.musicListener.musicVolume, 1.0F);
					task.cancel();
					new BukkitRunnable () {
						@Override
						public void run ()
						{
							removeSoundTaskFromTable (playerUUID);
						}
					}.runTaskLater(AGCraftPlugin.plugin, type.getEndTheme().duration().getTicks());
				}
			}
		}
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
			this.removeSoundTaskFromTable(playerUUID);
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
