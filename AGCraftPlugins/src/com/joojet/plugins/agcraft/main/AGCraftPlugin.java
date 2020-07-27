package com.joojet.plugins.agcraft.main;

import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.TabCompleter;
import org.bukkit.plugin.java.JavaPlugin;
import com.joojet.biblefetcher.database.CreateDatabase;
import com.joojet.biblefetcher.interpreter.CommandInterpreter;
import com.joojet.plugins.agcraft.enums.CommandType;
import com.joojet.plugins.agcraft.enums.ServerMode;
import com.joojet.plugins.agcraft.interfaces.PlayerCommand;
import com.joojet.plugins.consequences.ConsequenceManager;
import com.joojet.plugins.deathcounter.DeathCounter;
import com.joojet.plugins.mobs.AmplifiedMobSpawner;
import com.joojet.plugins.rewards.RewardManager;
import com.joojet.plugins.rewards.database.CreateRewardsDatabase;
import com.joojet.plugins.rewards.interpreter.EventTypeInterpreter;
import com.joojet.plugins.rewards.interpreter.RewardTypeInterpreter;
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
	// Stores the server mode, which enables or disables commands and listeners depending on what mode the server is ran in
	public static ServerMode serverMode = ServerMode.NORMAL;
	// A list containing all known commands
	private static HashMap <CommandType, PlayerCommand> playerCommands = new HashMap <CommandType, PlayerCommand> ();
	
	public AGCraftPlugin ()
	{
		super ();
	}
	
	@Override
	public void onEnable ()
	{
		plugin = this;
		// Attempts to create a database
		CreateDatabase.createNewDatabase();
		CreateLocationDatabase.createDatabase();
		CreateRewardsDatabase.createDatabase();
		
		this.loadCommands();
		
		// Death counter
		deathCounter = new DeathCounter();
		
		// Amplified mob spawner
		Bukkit.getPluginManager().registerEvents(new AmplifiedMobSpawner(), this);
		
		// Player login handler
		Bukkit.getPluginManager().registerEvents(new RewardManager(), this);
		
		// Player consequence handler
		Bukkit.getPluginManager().registerEvents (new ConsequenceManager(), this);
	}
	
	@Override
	public void onDisable ()
	{

	}
	
	/** Loads in all known commands into the plugin */
	public void loadCommands ()
	{
		String commandName;
		for (PlayerCommand pCommand : playerCommands.values())
		{
			commandName = pCommand.getClass().toString().toLowerCase();
			this.getCommand(commandName).setExecutor(pCommand.getExecutor());
			if (pCommand.getTabCompleter() != null)
			{
				this.getCommand(commandName).setTabCompleter(pCommand.getTabCompleter());
			}
		}
		this.setCommandPermissions();
	}
	
	/** Changes permissions for all known server commands based on the current server mode */
	public void setCommandPermissions ()
	{
		
	}
	
	/** Adds in a new player command without a tab completer */
	public static void addPlayerCommand (CommandType commandType, CommandExecutor executor)
	{
		playerCommands.put(commandType, new PlayerCommand (commandType, executor));
	}
	
	/** Adds a tab completer into this command list */
	public static void addTabCompleter (CommandType commandType, TabCompleter tabCompleter)
	{
		playerCommands.get(commandType).setTabCompleter(tabCompleter);
	}
}
