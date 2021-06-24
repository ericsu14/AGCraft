package com.joojet.plugins.agcraft.interfaces;

import com.joojet.plugins.agcraft.config.ServerConfigFile;

public interface ServerConfigLoader 
{
	/** Allows the listener to load in its configurable variables specified in the main
	 *  server's configuration file.
	 *  @param config - A reference to the main server configuration file */
	public void loadConfigVariables (ServerConfigFile config);
}
