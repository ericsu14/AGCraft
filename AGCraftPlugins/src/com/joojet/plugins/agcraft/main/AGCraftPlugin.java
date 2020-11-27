package com.joojet.plugins.agcraft.main;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;

import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginLogger;
import org.bukkit.plugin.java.JavaPlugin;
import com.joojet.biblefetcher.database.CreateDatabase;
import com.joojet.biblefetcher.interpreter.BibleCommandInterpreter;
import com.joojet.plugins.agcraft.config.ServerConfigFile;
import com.joojet.plugins.agcraft.enums.CommandType;
import com.joojet.plugins.agcraft.enums.PermissionType;
import com.joojet.plugins.agcraft.enums.ServerMode;
import com.joojet.plugins.agcraft.interfaces.AGCommandExecutor;
import com.joojet.plugins.agcraft.interfaces.AGListener;
import com.joojet.plugins.agcraft.interfaces.AGTabCompleter;
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
import com.joojet.plugins.mobs.CustomSkillsListener;
import com.joojet.plugins.mobs.DamageDisplayListener;
import com.joojet.plugins.mobs.PathfindTargetingEventListener;
import com.joojet.plugins.mobs.SoulBoundListener;
import com.joojet.plugins.mobs.SummoningScrollListener;
import com.joojet.plugins.mobs.bossbar.BossBarController;
import com.joojet.plugins.mobs.commands.SummonEntity;
import com.joojet.plugins.mobs.commands.tabcompleter.SummonEntityTabCompleter;
import com.joojet.plugins.mobs.interpreter.MonsterTypeInterpreter;
import com.joojet.plugins.mobs.interpreter.SummoningScrollInterpreter;
import com.joojet.plugins.music.MusicListener;
import com.joojet.plugins.rewards.RewardManager;
import com.joojet.plugins.rewards.commands.*;
import com.joojet.plugins.rewards.commands.tabcompleter.RewardPlayerTabCompleter;
import com.joojet.plugins.rewards.database.CreateRewardsDatabase;
import com.joojet.plugins.utility.commands.*;
import com.joojet.plugins.utility.commands.tabcompleter.*;
import com.joojet.plugins.utility.interpreter.ServerModeInterpreter;
import com.joojet.plugins.warp.commands.*;
import com.joojet.plugins.warp.commands.tabcompleter.*;
import com.joojet.plugins.warp.database.CreateLocationDatabase;

public class AGCraftPlugin extends JavaPlugin 
{
	/** Key used to reference the amplified mob spawner's debug mode */
	public final static String DEBUG_MODE_KEY = "debug-mode";
	/** Stores the logger instance used by this plugin */
	public static PluginLogger logger;
	// Stores an instance of the plugin itself
	public static AGCraftPlugin plugin;
	// Config file manager
	protected ServerConfigFile serverConfigFile;
	/** Config file values */
	// Stores the server mode, which enables or disables commands and listeners depending on what mode the server is ran in
	public ServerMode serverMode = ServerMode.NORMAL;
	// Determines if debug mode is enabled for the amplified mob spawner plugin
	public boolean enableDebugMode = false;
	
	// Stores a reference to the death counter object
	protected DeathCounter deathCounter;
	// A list containing all known commands
	protected HashMap <CommandType, PlayerCommand> playerCommands;
	// Stores an instance to the BossBar controller
	protected BossBarController bossBarController;
	
	
	/** Search term interpreters */
	// Stores the command interpreter used for server mode types
	protected ServerModeInterpreter serverModeInterpreter;
	// Stores the command interpreter used for the bible plugin
	protected BibleCommandInterpreter bibleInterpreter;
	/** Search trie used to lookup custom monsters by name */
	protected MonsterTypeInterpreter monsterTypeInterpreter;
	/** Search trie used to initialize custom summoning scroll instances */
	protected SummoningScrollInterpreter summonTypeInterpreter;
	
	/** Stores a set of active listener instances */
	protected ArrayList <AGListener> activeEventListeners;
	/** Music listener */
	protected MusicListener musicListener;
	// Stores a reference to the damage display listener
	protected DamageDisplayListener damageDisplayListener;
	
	public AGCraftPlugin ()
	{
		super ();
		this.playerCommands = new HashMap <CommandType, PlayerCommand> ();
		this.activeEventListeners = new ArrayList <AGListener> ();
		this.serverConfigFile = null;
		this.bibleInterpreter = new BibleCommandInterpreter();
		this.serverModeInterpreter = new ServerModeInterpreter ();
		this.serverConfigFile = new ServerConfigFile ();
		this.monsterTypeInterpreter = new MonsterTypeInterpreter ();
		this.musicListener = new MusicListener();
		this.damageDisplayListener = null;
		this.bossBarController = new BossBarController(this.monsterTypeInterpreter, this.musicListener);
		logger = new PluginLogger (this);
		logger.setLevel(Level.ALL);
	}
	
	@Override
	public void onEnable ()
	{
		plugin = this;
		
		// Attempts to create a database
		CreateDatabase.createNewDatabase();
		CreateLocationDatabase.createDatabase();
		CreateRewardsDatabase.createDatabase();
		
		this.summonTypeInterpreter = new SummoningScrollInterpreter ();
		
		// Loads in all commands
		this.initCommands();
		this.loadCommands();
		
		// Death counter
		deathCounter = new DeathCounter();
		
		// Amplified mob spawner
		this.registerEventListener(new AmplifiedMobSpawner (this.monsterTypeInterpreter, this.summonTypeInterpreter, this.bossBarController));
		
		// Summoning Scroll listener
		this.registerEventListener (new SummoningScrollListener(this.summonTypeInterpreter, this.bossBarController));
		
		// Player login handler
		this.registerEventListener(new RewardManager());
		
		// Player consequence handler
		this.registerEventListener (new ConsequenceManager());
		
		// Damage Display Listener
		this.damageDisplayListener = new DamageDisplayListener (this.monsterTypeInterpreter, this.bossBarController);
		this.registerEventListener(this.damageDisplayListener);
		
		// Boss Bar event listener
		this.registerEventListener(new BossBarEventListener(this.monsterTypeInterpreter, this.bossBarController));
		
		// Pathfind Targeting event listener
		this.registerEventListener(new PathfindTargetingEventListener(this.monsterTypeInterpreter, this.bossBarController));
		
		// Custom skills listener
		this.registerEventListener(new CustomSkillsListener (this.monsterTypeInterpreter, this.damageDisplayListener));
		
		// Soulbounded items event listener
		this.registerEventListener(new SoulBoundListener ());
		
		// Music controller event listener
		this.registerEventListener(this.musicListener);
		
		// Loads in the server config file and initializes its values
		this.loadServerConfigFile();
	}
	
	@Override
	public void onDisable ()
	{
		// Removes all active boss bars
		this.bossBarController.cleanup();
		
		// Invokes the onDisable routine for all event listeners
		for (AGListener listener: this.activeEventListeners)
		{
			listener.onDisable();
		}
	}
	
	/** Loads in the server config file and initializes its variables to the plugin */
	public void loadServerConfigFile ()
	{
		// Loads in the config file's contents
		this.serverConfigFile.reload();
		
		// Debug mode
		this.enableDebugMode = this.serverConfigFile.getValueAsBoolean(DEBUG_MODE_KEY);
		// Server mode
		switchServerMode(this.serverConfigFile.searchElementFromInterpreter (this.serverModeInterpreter,
				ServerMode.getKey(), ServerMode.NORMAL));
		
		// Invokes config file loader function for all event listeners
		for (AGListener listener : this.activeEventListeners)
		{
			listener.loadConfigVariables(this.serverConfigFile);
		}
		
		// Invokes config file loader function for all player commands
		for (PlayerCommand command : this.playerCommands.values())
		{
			command.getExecutor().loadConfigVariables(this.serverConfigFile);
		}
	}

	/** Initializes all commands */
	public void initCommands ()
	{		
		this.addPlayerCommand (new Bible (this.bibleInterpreter));
		this.addPlayerCommand (new ClearBibles (this.bibleInterpreter));
		this.addPlayerCommand (new ForgivePlayer ());
		this.addPlayerCommand (new PunishPlayer ());
		this.addPlayerCommand (new GetCoordinates ());
		this.addPlayerCommand (new OpenRewards ());
		this.addPlayerCommand (new RewardPlayer ());
		this.addPlayerCommand (new AutoSmelt ());
		this.addPlayerCommand (new ClearJunk());
		this.addPlayerCommand (new ToggleDebugMode ());
		this.addPlayerCommand (new GetLocations ());
		this.addPlayerCommand (new GiveRespawnTicket ());
		this.addPlayerCommand (new RemoveLocation ());
		this.addPlayerCommand (new SetLocation ());
		this.addPlayerCommand (new Warp (this.monsterTypeInterpreter));
		this.addPlayerCommand (new RemoveOldNetherLocations());
		this.addPlayerCommand (new ChangeServerMode (this.serverModeInterpreter));
		this.addPlayerCommand (new ReloadConfigFile ());
		this.addPlayerCommand (new FireworksCommand(this.musicListener));
		this.addPlayerCommand (new SummonEntity (this.summonTypeInterpreter, this.bossBarController));
		
		// Tab Completer
		this.addTabCompleter(new BibleTabCompleter (this.bibleInterpreter));
		this.addTabCompleter(new ClearJunkTabCompleter());
		this.addTabCompleter(new GetLocationsTabCompleter ());
		this.addTabCompleter(new RemoveLocationTabCompleter ());
		this.addTabCompleter(new SetLocationTabCompleter ());
		this.addTabCompleter(new WarpTabCompleter ());
		this.addTabCompleter(new ChangeServerModeTabCompleter ());
		this.addTabCompleter(new RewardPlayerTabCompleter());
		this.addTabCompleter(new PunishPlayerTabCompleter());
		this.addTabCompleter(new FireworkCommandTabCompleter());
		this.addTabCompleter(new SummonEntityTabCompleter());
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
		logger.info ("Debug mode " + ((enableDebugMode) ? "activated" : "disabled") + ".");
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
	
	/** Registers an event listener into Bukkit
	 *  @param listener - A reference to the listener itself */
	private void registerEventListener (AGListener listener)
	{
		this.activeEventListeners.add(listener);
		Bukkit.getPluginManager().registerEvents(listener, this);
		listener.onEnable();
	}
	
}
