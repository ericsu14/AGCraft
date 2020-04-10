package com.joojet.plugins.agcraft.main;

import org.bukkit.plugin.java.JavaPlugin;
import com.joojet.biblefetcher.database.CreateDatabase;
import com.joojet.biblefetcher.interpreter.CommandInterpreter;
import com.joojet.plugins.biblefetcher.commands.Bible;
import com.joojet.plugins.biblefetcher.commands.BibleTabCompleter;
import com.joojet.plugins.biblefetcher.commands.ClearBibles;
import com.joojet.plugins.coordinates.commands.GetCoordinates;
import com.joojet.plugins.utility.commands.AutoSmelt;
import com.joojet.plugins.utility.commands.ClearJunk;
import com.joojet.plugins.utility.commands.ClearJunkTabCompleter;
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
		
		//Bible
		this.getCommand("bible").setExecutor(new Bible ());
		this.getCommand("bible").setTabCompleter(new BibleTabCompleter());
		// Clear Bible
		this.getCommand("clearBibles").setExecutor(new ClearBibles());
		
		// Get Coordinates
		this.getCommand("getCoordinates").setExecutor(new GetCoordinates());
		
		// Clear Junk
		this.getCommand("clearJunk").setExecutor(new ClearJunk());
		this.getCommand("clearJunk").setTabCompleter(new ClearJunkTabCompleter());
		
		// AutoSmelt
		this.getCommand("autosmelt").setExecutor(new AutoSmelt());
		
		// Warp
		this.getCommand("warp").setExecutor(new Warp());
		this.getCommand("warp").setTabCompleter(new WarpTabCompleter());
		
		// Set Location
		this.getCommand("setlocation").setExecutor(new SetLocation());
		
		// Remove Location
		this.getCommand("removelocation").setExecutor(new RemoveLocation());
		
		// Get Locations
		this.getCommand("getlocations").setExecutor(new GetLocations());
	}
	
	@Override
	public void onDisable ()
	{
		
	}
}
