package com.joojet.plugins.agcraft.main;

import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import com.joojet.biblefetcher.database.CreateDatabase;
import com.joojet.biblefetcher.interpreter.CommandInterpreter;
import com.joojet.plugins.agcraft.enums.CommandType;
import com.joojet.plugins.agcraft.enums.PermissionType;
import com.joojet.plugins.agcraft.enums.ServerMode;
import com.joojet.plugins.agcraft.interfaces.AGCommandExecutor;
import com.joojet.plugins.agcraft.interfaces.AGTabCompleter;
import com.joojet.plugins.agcraft.interfaces.PlayerCommand;
import com.joojet.plugins.biblefetcher.commands.*;
import com.joojet.plugins.biblefetcher.commands.tabcompleter.BibleTabCompleter;
import com.joojet.plugins.consequences.ConsequenceManager;
import com.joojet.plugins.consequences.commands.*;
import com.joojet.plugins.coordinates.commands.GetCoordinates;
import com.joojet.plugins.deathcounter.DeathCounter;
import com.joojet.plugins.mobs.AmplifiedMobSpawner;
import com.joojet.plugins.rewards.RewardManager;
import com.joojet.plugins.rewards.commands.*;
import com.joojet.plugins.rewards.database.CreateRewardsDatabase;
import com.joojet.plugins.rewards.interpreter.EventTypeInterpreter;
import com.joojet.plugins.rewards.interpreter.RewardTypeInterpreter;
import com.joojet.plugins.utility.commands.*;
import com.joojet.plugins.utility.commands.tabcompleter.ClearJunkTabCompleter;
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
	// Stores the server mode, which enables or disables commands and listeners depending on what mode the server is ran in
	public static ServerMode serverMode = ServerMode.NORMAL;
	// A list containing all known commands
	private HashMap <CommandType, PlayerCommand> playerCommands;
	
	public AGCraftPlugin ()
	{
		super ();
		this.playerCommands = new HashMap <CommandType, PlayerCommand> ();
	}
	
	@Override
	public void onEnable ()
	{
		plugin = this;
		// Attempts to create a database
		CreateDatabase.createNewDatabase();
		CreateLocationDatabase.createDatabase();
		CreateRewardsDatabase.createDatabase();
		
		// Loads in all commands
		this.initCommands();
		this.loadCommands();
		this.setCommandPermissions();
		
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
	
	
	/** Initializes all commands */
	public void initCommands ()
	{
		// Commands
		this.addPlayerCommand (new Bible ());
		this.addPlayerCommand (new ClearBibles ());
		this.addPlayerCommand (new ForgivePlayer ());
		this.addPlayerCommand (new PunishPlayer ());
		this.addPlayerCommand (new GetCoordinates ());
		this.addPlayerCommand (new OpenRewards ());
		this.addPlayerCommand (new RewardPlayer ());
		this.addPlayerCommand (new AutoSmelt ());
		this.addPlayerCommand (new ClearJunk ());
		this.addPlayerCommand (new ToggleDebugMode ());
		this.addPlayerCommand (new GetLocations ());
		this.addPlayerCommand (new GiveRespawnTicket ());
		this.addPlayerCommand (new RemoveLocation ());
		this.addPlayerCommand (new SetLocation ());
		this.addPlayerCommand (new Warp ());
		this.addPlayerCommand (new RemoveOldNetherLocations());
		
		// Tab Completers
		this.addTabCompleter(new BibleTabCompleter ());
		this.addTabCompleter(new ClearJunkTabCompleter());
		this.addTabCompleter(new GetLocationsTabCompleter ());
		this.addTabCompleter(new RemoveLocationTabCompleter ());
		this.addTabCompleter(new SetLocationTabCompleter ());
		this.addTabCompleter(new WarpTabCompleter ());
	}
	
	/** Loads in all known commands into the plugin */
	public void loadCommands ()
	{
		String commandName;
		for (PlayerCommand pCommand : playerCommands.values())
		{
			commandName = pCommand.getCommandType().toString().toLowerCase();
			this.getCommand(commandName).setExecutor(pCommand.getExecutor());
			if (pCommand.getTabCompleter() != null)
			{
				this.getCommand(commandName).setTabCompleter(pCommand.getTabCompleter());
			}
			System.out.println ("Inserted " + commandName);
		}
	}
	
	/** Changes permissions for all known server commands based on the current server mode */
	public void setCommandPermissions ()
	{
		String commandName;
		for (PlayerCommand pCommand : playerCommands.values ())
		{
			commandName = pCommand.getCommandType().toString().toLowerCase();
			// Only change permission of commands whose permission type is PLAYER
			if (pCommand.getPermissionType() == PermissionType.PLAYER)
			{
				PermissionType curr = serverMode == ServerMode.NORMAL ? PermissionType.PLAYER : PermissionType.ADMIN;
				this.getCommand(commandName).setPermission(curr.getPermission());
				System.out.println ("Changed permission of " + commandName  + " to " + curr.getPermission());
			}
		}
	}
	
	/** Switches the server's mode to a new value
	 * 		@param mode - The new mode the server is being switched to */
	public void switchServerMode (ServerMode mode)
	{
		serverMode = mode;
		setCommandPermissions ();
	}
	
	/** Adds in a new player command without a tab completer
	 * 		@param commandType - Type of command the command executor is being attached to
	 * 		@param executor - A reference to the command executor instance */
	private void addPlayerCommand (AGCommandExecutor executor)
	{
		playerCommands.put(executor.getCommandType(), new PlayerCommand (executor.getCommandType(), executor));
	}
	
	/** Adds a tab completer into this command list
	 * 		@param commandType - The type of command the tab completer instance is being attached to
	 * 		@param tabCompleter - A reference to the tab completer instance being attached */
	private void addTabCompleter (AGTabCompleter tabCompleter)
	{
		playerCommands.get(tabCompleter.getCommandType()).setTabCompleter(tabCompleter);
	}
	
}
