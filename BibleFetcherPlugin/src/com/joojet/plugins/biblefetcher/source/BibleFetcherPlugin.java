package com.joojet.plugins.biblefetcher.source;

import org.bukkit.plugin.java.JavaPlugin;
import com.joojet.biblefetcher.database.CreateDatabase;
import com.joojet.biblefetcher.interpreter.CommandInterpreter;
public class BibleFetcherPlugin extends JavaPlugin 
{
	public static CommandInterpreter interpreter = new CommandInterpreter ();
	
	@Override
	public void onEnable ()
	{
		// Attempts to create a database
		CreateDatabase.createNewDatabase();
		this.getCommand("bible").setExecutor(new CommandBible ());
	}
	
	@Override
	public void onDisable ()
	{
		
	}
}
