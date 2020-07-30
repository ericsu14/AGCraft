package com.joojet.plugins.agcraft.config;

import java.util.HashMap;

import com.joojet.plugins.agcraft.enums.ServerMode;
import com.joojet.plugins.mobs.AmplifiedMobSpawner;
import com.joojet.plugins.mobs.enums.ServerEvent;
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
		data.put(ServerEvent.getKey(), ServerEvent.DEFAULT.toString());
		data.put(MinigameRewardType.getKey(), MinigameRewardType.GIFT.toString());
		data.put(AmplifiedMobSpawner.spawnChanceKey, 0.15);
		data.put(AmplifiedMobSpawner.debugModeKey, false);
		return data;
	}
}
