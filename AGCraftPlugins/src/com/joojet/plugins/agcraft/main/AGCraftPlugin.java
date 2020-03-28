package com.joojet.plugins.agcraft.main;

import org.bukkit.plugin.java.JavaPlugin;
import com.joojet.biblefetcher.database.CreateDatabase;
import com.joojet.biblefetcher.interpreter.CommandInterpreter;
import com.joojet.plugins.biblefetcher.commands.Bible;
import com.joojet.plugins.biblefetcher.commands.ClearBibles;
import com.joojet.plugins.coordinates.commands.GetCoordinates;
public class AGCraftPlugin extends JavaPlugin 
{
	public static CommandInterpreter interpreter = new CommandInterpreter ();
	
	@Override
	public void onEnable ()
	{
		// Attempts to create a database
		CreateDatabase.createNewDatabase();
		
		this.getCommand("bible").setExecutor(new Bible ());
		this.getCommand("clearBibles").setExecutor(new ClearBibles());
		this.getCommand("getCoordinates").setExecutor(new GetCoordinates());
	}
	
	@Override
	public void onDisable ()
	{
		
	}
}
