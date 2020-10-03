package com.joojet.plugins.agcraft.interfaces;

import org.bukkit.event.Listener;

import com.joojet.plugins.agcraft.config.ServerConfigFile;

public abstract class AGListener implements Listener
{
	/** Allows the listener to load in its configurable variables specified in the main
	 *  server's configuration file.
	 *  @param config - A reference to the main server configuration file */
	public abstract void loadConfigVarialbes (ServerConfigFile config);
}
