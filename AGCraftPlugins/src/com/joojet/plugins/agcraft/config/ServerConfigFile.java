package com.joojet.plugins.agcraft.config;

import java.util.HashMap;

import com.joojet.plugins.agcraft.enums.ServerMode;
import com.joojet.plugins.agcraft.main.AGCraftPlugin;
import com.joojet.plugins.mobs.enums.ThemedServerEvent;
import com.joojet.plugins.mobs.spawnhandlers.AmplifiedMobHandler;
import com.joojet.plugins.mobs.spawnhandlers.BeatTheBruinsHandler;
import com.joojet.plugins.mobs.spawnhandlers.JulyFourthHandler;
import com.joojet.plugins.mobs.spawnhandlers.UHCHandler;
import com.joojet.plugins.music.MusicListener;
import com.joojet.plugins.rewards.RewardManager;
import com.joojet.plugins.rewards.enums.MinigameRewardType;

public class ServerConfigFile extends AbstractConfigFile
{
	public ServerConfigFile ()
	{
		super ("server-config.yaml");
	}

	/** Creates a new server config file with server default values */
	@Override
	protected HashMap <String, Object> createConfigFileContents() 
	{
		HashMap <String, Object> data = new HashMap <String, Object> ();
		data.put(ServerMode.getKey(), ServerMode.NORMAL.toString());
		data.put(ThemedServerEvent.getKey(), ThemedServerEvent.DEFAULT.toString());
		data.put(MinigameRewardType.getKey(), MinigameRewardType.GIFT.toString());
		data.put(MusicListener.MUSIC_VOLUME_TAG, 0.60f);
		data.put(MusicListener.FIREWORKS_MUSIC_VOLUME_TAG, 0.50f);
		data.put(AmplifiedMobHandler.AMPLIFIED_MOB_HANDLER_KEY, 0.15);
		data.put(BeatTheBruinsHandler.BEAT_THE_BRUINS_HANDLER_KEY, 0.20);
		data.put(JulyFourthHandler.JULY_FOURTH_HANDLER_KEY, 0.20);
		data.put(UHCHandler.UHC_HANDLER_KEY, 1.00);
		data.put(RewardManager.MOB_IGNORES_PLAYERS_KEY, 15);
		data.put(AGCraftPlugin.DEBUG_MODE_KEY, false);
		return data;
	}
	
}
