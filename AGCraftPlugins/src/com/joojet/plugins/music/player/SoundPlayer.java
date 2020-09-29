package com.joojet.plugins.music.player;

import java.util.HashMap;
import java.util.UUID;

import org.bukkit.Location;
import org.bukkit.entity.Player;

import com.joojet.plugins.agcraft.main.AGCraftPlugin;
import com.joojet.plugins.music.enums.MusicType;
import com.joojet.plugins.music.task.PlayCustomSoundTask;

public class SoundPlayer 
{
	protected float range;
	
	protected HashMap <UUID, PlayCustomSoundTask> activePlayerSoundTable;
	
	public SoundPlayer ()
	{
		this.range = 5.0F;
		this.activePlayerSoundTable = new HashMap <UUID, PlayCustomSoundTask> ();
	}
	
	public void playCustomMusicNearPlayer(MusicType type, Player player)
	{
		if (!this.activePlayerSoundTable.containsKey(player.getUniqueId()))
		{
			player.playSound(player.getLocation(), type.getNamespace(), range, 1.0F);
			PlayCustomSoundTask soundTask = new PlayCustomSoundTask (player.getUniqueId(), type, this);
			this.activePlayerSoundTable.put(player.getUniqueId(), soundTask);
			soundTask.runTaskLater(AGCraftPlugin.plugin, type.duration().getTicks());
		}
	}
	
	public void stopSoundNearPlayer (MusicType type, Player player)
	{
		player.stopSound(type.getNamespace());
	}
	
	public void playCustomMusicAtLocation (MusicType type, Location location)
	{
		location.getWorld().playSound(location, type.getNamespace(), range, 1.0F);
	}
	
}
