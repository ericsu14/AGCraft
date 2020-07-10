package com.joojet.plugins.agcraft.main;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import com.joojet.biblefetcher.database.CreateDatabase;
import com.joojet.biblefetcher.interpreter.CommandInterpreter;
import com.joojet.plugins.biblefetcher.commands.Bible;
import com.joojet.plugins.biblefetcher.commands.ClearBibles;
import com.joojet.plugins.biblefetcher.commands.tabcompleter.*;
import com.joojet.plugins.coordinates.commands.GetCoordinates;
import com.joojet.plugins.deathcounter.DeathCounter;
import com.joojet.plugins.mobs.AmplifiedMobSpawner;
import com.joojet.plugins.rewards.RewardManager;
import com.joojet.plugins.rewards.commands.OpenRewards;
import com.joojet.plugins.rewards.commands.RewardPlayer;
import com.joojet.plugins.rewards.database.CreateRewardsDatabase;
import com.joojet.plugins.rewards.interpreter.EventTypeInterpreter;
import com.joojet.plugins.rewards.interpreter.RewardTypeInterpreter;
import com.joojet.plugins.utility.commands.AutoSmelt;
import com.joojet.plugins.utility.commands.ClearJunk;
import com.joojet.plugins.utility.commands.tabcompleter.*;
import com.joojet.plugins.warp.commands.*;
import com.joojet.plugins.warp.commands.tabcompleter.*;
import com.joojet.plugins.warp.database.CreateLocationDatabase;

public class AGCraftPlugin extends JavaPlugin 
{
	// Stores the command interpreter used for the bible plugin
	public static CommandInterpreter interpreter = new CommandInterpreter ();
	// Stores an instance of the plugin itself
	public static AGCraftPlugin plugin;
	// Stores a reference to the death counter object
	public static DeathCounter deathCounter;
	// Stores the command interpreter used for reward types
	public static RewardTypeInterpreter rewardInterpreter = new RewardTypeInterpreter();
	// Stores the command interpreter used for event types
	public static EventTypeInterpreter eventInterpreter = new EventTypeInterpreter();
	
	@Override
	public void onEnable ()
	{
		plugin = this;
		// Attempts to create a database
		CreateDatabase.createNewDatabase();
		CreateLocationDatabase.createDatabase();
		CreateRewardsDatabase.createDatabase();
		
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
		this.getCommand("setlocation").setTabCompleter(new SetLocationTabCompleter());
		
		// Remove Location
		this.getCommand("removelocation").setExecutor(new RemoveLocation());
		this.getCommand("removelocation").setTabCompleter(new RemoveLocationTabCompleter());
		
		// Get Locations
		this.getCommand("getlocations").setExecutor(new GetLocations());
		this.getCommand("getlocations").setTabCompleter(new GetLocationsTabCompleter());
		
		// Respawn ticket
		this.getCommand("giverespawnticket").setExecutor(new GiveRespawnTicket());
		
		// Remove all old nether locations
		this.getCommand("removeoldnetherlocations").setExecutor(new RemoveOldNetherLocations());
		
		// Reward player
		this.getCommand("grantrewards").setExecutor(new RewardPlayer ());
		
		// Open reward gui
		this.getCommand("rewards").setExecutor(new OpenRewards());
		
		// Death counter
		deathCounter = new DeathCounter();
		
		// Amplified mob spawner
		Bukkit.getPluginManager().registerEvents(new AmplifiedMobSpawner(), this);
		
		// Player login handler
		Bukkit.getPluginManager().registerEvents(new RewardManager(), this);
	}
	
	@Override
	public void onDisable ()
	{

	}
}
