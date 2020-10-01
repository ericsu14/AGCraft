package com.joojet.plugins.agcraft.main;

import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import com.joojet.biblefetcher.database.CreateDatabase;
import com.joojet.biblefetcher.interpreter.BibleCommandInterpreter;
import com.joojet.plugins.agcraft.config.ServerConfigFile;
import com.joojet.plugins.agcraft.enums.CommandType;
import com.joojet.plugins.agcraft.enums.PermissionType;
import com.joojet.plugins.agcraft.enums.ServerMode;
import com.joojet.plugins.agcraft.interfaces.AGCommandExecutor;
import com.joojet.plugins.agcraft.interfaces.AGTabCompleter;
import com.joojet.plugins.agcraft.interfaces.AbstractInterpreter;
import com.joojet.plugins.agcraft.interfaces.PlayerCommand;
import com.joojet.plugins.biblefetcher.commands.*;
import com.joojet.plugins.biblefetcher.commands.tabcompleter.BibleTabCompleter;
import com.joojet.plugins.consequences.ConsequenceManager;
import com.joojet.plugins.consequences.commands.*;
import com.joojet.plugins.consequences.commands.tabcompleter.PunishPlayerTabCompleter;
import com.joojet.plugins.coordinates.commands.GetCoordinates;
import com.joojet.plugins.deathcounter.DeathCounter;
import com.joojet.plugins.mobs.AmplifiedMobSpawner;
import com.joojet.plugins.mobs.BossBarEventListener;
import com.joojet.plugins.mobs.DamageDisplayListener;
import com.joojet.plugins.mobs.PathfindTargetingEventListener;
import com.joojet.plugins.mobs.SoulBoundListener;
import com.joojet.plugins.mobs.SummoningScrollListener;
import com.joojet.plugins.mobs.bossbar.BossBarAPI;
import com.joojet.plugins.mobs.enums.ThemedServerEvent;
import com.joojet.plugins.mobs.interpreter.ThemedServerEventInterpreter;
import com.joojet.plugins.music.MusicListener;
import com.joojet.plugins.rewards.RewardManager;
import com.joojet.plugins.rewards.commands.*;
import com.joojet.plugins.rewards.commands.tabcompleter.RewardPlayerTabCompleter;
import com.joojet.plugins.rewards.database.CreateRewardsDatabase;
import com.joojet.plugins.rewards.enums.MinigameRewardType;
import com.joojet.plugins.rewards.interpreter.MinigameRewardTypeInterpreter;
import com.joojet.plugins.rewards.interpreter.RewardTypeInterpreter;
import com.joojet.plugins.utility.commands.*;
import com.joojet.plugins.utility.commands.tabcompleter.*;
import com.joojet.plugins.utility.interpreter.ServerModeInterpreter;
import com.joojet.plugins.warp.commands.*;
import com.joojet.plugins.warp.commands.tabcompleter.*;
import com.joojet.plugins.warp.database.CreateLocationDatabase;

public class AGCraftPlugin extends JavaPlugin 
{
	// Stores an instance of the plugin itself
	public static AGCraftPlugin plugin;
	// Stores a reference to the death counter object
	public static DeathCounter deathCounter;
	// Config file manager
	public ServerConfigFile serverConfigFile;
	// A list containing all known commands
	private HashMap <CommandType, PlayerCommand> playerCommands;
	// Stores a reference to the junk command since that command manages its own config files
	private ClearJunk clearJunk;
	
	/** Search term interpreters */
	// Stores the command interpreter used for reward types
	public static RewardTypeInterpreter rewardInterpreter;
	// Stores the command interpreter used for minigame reward types
	public static MinigameRewardTypeInterpreter minigameRewardTypeInterpreter;
	// Stores the command interpreter used for server event types
	public static ThemedServerEventInterpreter serverEventInterpreter;
	// Stores the command interpreter used for server mode types
	public static ServerModeInterpreter serverModeInterpreter;
	// Stores the command interpreter used for the bible plugin
	public static BibleCommandInterpreter bibleInterpreter;
	
	/** Config file values */
	// Stores the server mode, which enables or disables commands and listeners depending on what mode the server is ran in
	public ServerMode serverMode = ServerMode.NORMAL;
	// Stores the server-wide event mode, which may add custom themed mobs or events into the normal game world
	public ThemedServerEvent serverEventMode = ThemedServerEvent.DEFAULT;
	// Stores the chance of custom mobs spawning into the game
	public double customMobSpawnChance = 0.15;
	// Determines if debug mode is enabled for the amplified mob spawner plugin
	public boolean enableDebugMode = false;
	// Stores type of minigame reward type currently active on minigame nights
	public MinigameRewardType minigameEventType = MinigameRewardType.GIFT;
	
	/** Used to display entity damage information to the player */
	public DamageDisplayListener damageListener;
	/** Used to enforce soulbounded item-drop events */
	public SoulBoundListener soulBoundListener;
	
	
	public AGCraftPlugin ()
	{
		super ();
		this.playerCommands = new HashMap <CommandType, PlayerCommand> ();
		this.serverConfigFile = null;
		bibleInterpreter = new BibleCommandInterpreter();
		rewardInterpreter = new RewardTypeInterpreter ();
		minigameRewardTypeInterpreter = new MinigameRewardTypeInterpreter ();
		serverEventInterpreter = new ThemedServerEventInterpreter ();
		serverModeInterpreter = new ServerModeInterpreter ();
		this.serverConfigFile = new ServerConfigFile ();
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
		
		// Loads in the server config file and initializes its values
		this.loadServerConfigFile();
		
		// Death counter
		deathCounter = new DeathCounter();
		
		// Amplified mob spawner
		Bukkit.getPluginManager().registerEvents(new AmplifiedMobSpawner(), this);
		
		// Summoning Scroll listener
		Bukkit.getPluginManager().registerEvents (new SummoningScrollListener(), this);
		
		// Player login handler
		Bukkit.getPluginManager().registerEvents(new RewardManager(), this);
		
		// Player consequence handler
		Bukkit.getPluginManager().registerEvents (new ConsequenceManager(), this);
		
		// Damage Display Listener
		this.damageListener = new DamageDisplayListener ();
		Bukkit.getPluginManager().registerEvents(this.damageListener, this);
		
		// Boss Bar event listener
		Bukkit.getPluginManager().registerEvents(new BossBarEventListener(), this);
		
		// Pathfind Targeting event listener
		Bukkit.getPluginManager().registerEvents(new PathfindTargetingEventListener(), this);
		
		// Soulbounded items event listener
		this.soulBoundListener = new SoulBoundListener ();
		this.soulBoundListener.onEnable();
		
		// Music controller event listener
		Bukkit.getPluginManager().registerEvents(new MusicListener(), this);
	}
	
	@Override
	public void onDisable ()
	{
		// Removes all active boss bars
		BossBarAPI.cleanup();
		
		// Cleans up all damage displays
		this.damageListener.onDisable();
		
		// Attempts to serialized unrecovered soulbounded items into a file
		this.soulBoundListener.onDisable();
	}
	
	/** Loads in the server config file and initializes its variables to the plugin */
	public void loadServerConfigFile ()
	{
		// Loads in the config file's contents
		this.serverConfigFile.reload();
		
		// Spawn chance
		this.customMobSpawnChance = (double) this.serverConfigFile.getValue(AmplifiedMobSpawner.spawnChanceKey);
		// Debug mode
		this.enableDebugMode = (boolean) this.serverConfigFile.getValue(AmplifiedMobSpawner.debugModeKey);
		// Minigame event type
		this.minigameEventType = this.searchElementFromInterpreter(minigameRewardTypeInterpreter,
				MinigameRewardType.getKey(), MinigameRewardType.GIFT);
		// Server event mode
		this.serverEventMode = this.searchElementFromInterpreter(serverEventInterpreter,
				ThemedServerEvent.getKey(), ThemedServerEvent.DEFAULT);
		// Server mode
		this.switchServerMode(this.searchElementFromInterpreter (serverModeInterpreter,
				ServerMode.getKey(), ServerMode.NORMAL));
		// Prints out other values
		System.out.println ("Set amplified mob spawn chance to " + this.customMobSpawnChance);
		System.out.println ("Debug Mode: " + this.enableDebugMode);
		
		// Music volume
		MusicListener.setMusicVolume((double) this.serverConfigFile.getValue(MusicListener.musicVolumeTag));
		
		// Reloads the clearjunk file
		this.clearJunk.reloadConfigFile();
	}

	/** Initializes all commands */
	public void initCommands ()
	{
		// Commands
		this.clearJunk = new ClearJunk();
		
		this.addPlayerCommand (new Bible ());
		this.addPlayerCommand (new ClearBibles ());
		this.addPlayerCommand (new ForgivePlayer ());
		this.addPlayerCommand (new PunishPlayer ());
		this.addPlayerCommand (new GetCoordinates ());
		this.addPlayerCommand (new OpenRewards ());
		this.addPlayerCommand (new RewardPlayer ());
		this.addPlayerCommand (new AutoSmelt ());
		this.addPlayerCommand (this.clearJunk);
		this.addPlayerCommand (new ToggleDebugMode ());
		this.addPlayerCommand (new GetLocations ());
		this.addPlayerCommand (new GiveRespawnTicket ());
		this.addPlayerCommand (new RemoveLocation ());
		this.addPlayerCommand (new SetLocation ());
		this.addPlayerCommand (new Warp ());
		this.addPlayerCommand (new RemoveOldNetherLocations());
		this.addPlayerCommand (new ChangeServerMode ());
		this.addPlayerCommand (new ReloadConfigFile ());
		this.addPlayerCommand (new FireworksCommand());
		
		// Tab Completer
		this.addTabCompleter(new BibleTabCompleter ());
		this.addTabCompleter(new ClearJunkTabCompleter());
		this.addTabCompleter(new GetLocationsTabCompleter ());
		this.addTabCompleter(new RemoveLocationTabCompleter ());
		this.addTabCompleter(new SetLocationTabCompleter ());
		this.addTabCompleter(new WarpTabCompleter ());
		this.addTabCompleter(new ChangeServerModeTabCompleter ());
		this.addTabCompleter(new RewardPlayerTabCompleter());
		this.addTabCompleter(new PunishPlayerTabCompleter());
		this.addTabCompleter(new FireworkCommandTabCompleter());
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
			}
		}
	}
	
	/** Switches the server's mode to a new value
	 * 		@param mode - The new mode the server is being switched to */
	public void switchServerMode (ServerMode mode)
	{
		this.serverMode = mode;
		setCommandPermissions ();
	}
	
	/** Toggles debug mode on or off */
	public void toggleDebugMode ()
	{
		enableDebugMode = !(enableDebugMode);
		System.out.println ("Debug mode " + ((enableDebugMode) ? "activated" : "disabled") + ".");
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
	
	/** Searches a search trie for a value based on a key. If not found, throw an error and use a
	 *  default.
	 * @param <E>
	 *  @param interpreter - Instance of a search term interpreter to be searched
	 *  @param key - Key used in the interpreter to search for the value
	 *  @param defaultValue - Default value to be used in the event of failure */
	private <E> E searchElementFromInterpreter (AbstractInterpreter<E> interpreter, String key, E defaultValue)
	{
		E result = interpreter.searchTrie(this.serverConfigFile.getValue(key).toString());
		if (result == null)
		{
			System.err.println ("Error: Cannot find value for " + key + ". Using default value " + defaultValue.toString() + " instead...");
			result = defaultValue;
		}
		else
		{
			System.out.println ("Loaded variable " + result.toString() + " for " + key + "!");
		}
		return result;
	}
	
}
