package com.joojet.plugins.agcraft.config;

import java.util.HashMap;

import com.joojet.plugins.agcraft.enums.ServerMode;
import com.joojet.plugins.agcraft.main.AGCraftPlugin;
import com.joojet.plugins.mobs.commands.SummonEntity;
import com.joojet.plugins.mobs.enums.DamageDisplayMode;
import com.joojet.plugins.mobs.enums.ThemedServerEvent;
import com.joojet.plugins.mobs.interpreter.DamageDisplayModeInterpreter;
import com.joojet.plugins.mobs.spawnhandlers.AmplifiedMobHandler;
import com.joojet.plugins.mobs.spawnhandlers.BeatTheBruinsHandler;
import com.joojet.plugins.mobs.spawnhandlers.HungerGamesHandler;
import com.joojet.plugins.mobs.spawnhandlers.JulyFourthHandler;
import com.joojet.plugins.mobs.spawnhandlers.UHCHandler;
import com.joojet.plugins.mobs.spawning.FairSpawnController;
import com.joojet.plugins.music.MusicListener;
import com.joojet.plugins.rewards.RewardManager;
import com.joojet.plugins.rewards.enums.MinigameRewardType;
import com.joojet.plugins.utility.commands.FireworksCommand;
import com.joojet.plugins.warp.commands.Warp;

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
		data.put(FireworksCommand.COOLDOWN_TIMER_TAG, 3);
		data.put(FireworksCommand.FIREWORK_LIMIT, 300);
		data.put(FireworksCommand.FIREWORK_POWER_LIMIT, 4);
		data.put(FireworksCommand.FIREWORK_SPREAD_LIMIT, 48);
		data.put(FireworksCommand.MIN_FIREWORK_COUNT, 30);
		data.put(Warp.WARP_RADIUS_TAG, 6);
		data.put (Warp.WARP_HEALTH_THRESHOLD_TAG, 6);
		data.put(SummonEntity.MAX_SUMMONED_ENTITIES_TAG, 10);
		data.put(FairSpawnController.CUSTOM_MOB_SCAN_RADIUS, 128);
		data.put(HungerGamesHandler.HUNGER_GAMES_SPAWN_HANDLER_KEY, 1.00);
		data.put(DamageDisplayModeInterpreter.DAMAGE_DISPLAY_MODE_KEY, DamageDisplayMode.AUTO.toString());
		return data;
	}
	
}
