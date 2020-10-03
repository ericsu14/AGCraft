package com.joojet.plugins.agcraft.interfaces;

import org.bukkit.event.Listener;

import com.joojet.plugins.agcraft.config.ServerConfigFile;

public abstract class AGListener implements Listener
{
	/** Allows the listener to load in its configurable variables specified in the main
	 *  server's configuration file.
	 *  @param config - A reference to the main server configuration file */
	public abstract void loadConfigVariables (ServerConfigFile config);
	
	/** Allows the listener to implement their own setup routines upon enabling */
	public abstract void onEnable ();
	
	/** Allows the listener to implement their own cleanup code upon disabling */
	public abstract void onDisable ();
}
