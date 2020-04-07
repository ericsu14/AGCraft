package com.joojet.plugins.agcraft.main;

import org.bukkit.plugin.java.JavaPlugin;
import com.joojet.biblefetcher.database.CreateDatabase;
import com.joojet.biblefetcher.interpreter.CommandInterpreter;
import com.joojet.plugins.biblefetcher.commands.Bible;
import com.joojet.plugins.biblefetcher.commands.ClearBibles;
import com.joojet.plugins.coordinates.commands.GetCoordinates;
import com.joojet.plugins.utility.commands.AutoSmelt;
import com.joojet.plugins.utility.commands.ClearJunk;
import com.joojet.plugins.warp.commands.*;
import com.joojet.plugins.warp.database.CreateLocationDatabase;
public class AGCraftPlugin extends JavaPlugin 
{
	public static CommandInterpreter interpreter = new CommandInterpreter ();
	
	@Override
	public void onEnable ()
	{
		// Attempts to create a database
		CreateDatabase.createNewDatabase();
		CreateLocationDatabase.createDataBase();
		
		this.getCommand("bible").setExecutor(new Bible ());
		this.getCommand("clearBibles").setExecutor(new ClearBibles());
		this.getCommand("getCoordinates").setExecutor(new GetCoordinates());
		this.getCommand("clearJunk").setExecutor(new ClearJunk());
		this.getCommand("autosmelt").setExecutor(new AutoSmelt());
		this.getCommand("warp").setExecutor(new Warp());
		this.getCommand("setlocation").setExecutor(new SetLocation());
		this.getCommand("removelocation").setExecutor(new RemoveLocation());
		this.getCommand("getlocations").setExecutor(new GetLocations());
	}
	
	@Override
	public void onDisable ()
	{
		
	}
}
