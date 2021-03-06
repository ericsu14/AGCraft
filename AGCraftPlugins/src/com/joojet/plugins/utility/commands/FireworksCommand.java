package com.joojet.plugins.utility.commands;

import java.text.ParseException;
import java.util.Calendar;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.World.Environment;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.joojet.plugins.agcraft.config.ServerConfigFile;
import com.joojet.plugins.agcraft.enums.CommandType;
import com.joojet.plugins.agcraft.interfaces.AGCommandExecutor;
import com.joojet.plugins.agcraft.interfaces.ServerConfigLoader;
import com.joojet.plugins.agcraft.main.AGCraftPlugin;
import com.joojet.plugins.consequences.enums.CalendarField;
import com.joojet.plugins.mobs.fireworks.tasks.SpawnFireworksOnLocationTask;
import com.joojet.plugins.mobs.metadata.FireworkCommandMetadata;
import com.joojet.plugins.music.MusicListener;
import com.joojet.plugins.music.enums.MusicType;
import com.joojet.plugins.music.enums.SoundPlayerState;
import com.joojet.plugins.music.interpreter.MusicTypeInterpreter;
import com.joojet.plugins.warp.scantools.ScanEntities;

public class FireworksCommand extends AGCommandExecutor implements ServerConfigLoader
{
	/** A list of tags used to reference various variables from the config file */
	public static final String COOLDOWN_TIMER_TAG = "firework-cooldown-timer";
	public static final String FIREWORK_LIMIT = "firework-ammo-limit";
	public static final String FIREWORK_SPREAD_LIMIT = "firework-spread-limit";
	public static final String FIREWORK_POWER_LIMIT = "firework-power-limit";
	public static final String MIN_FIREWORK_COUNT = "min-firework-ammo-count";
	
	/** Defines the cooldown timer before a player can launch the next fireworks show in miuntes */
	public int cooldownTimer = 3;
	/** Adds a limit on how many fireworks can be launched */
	public int fireworkLimit = 300;
	/** Adds a limit on the firework spread radius */
	public int fireworkSpreadLimit = 48;
	/** Adds a limit on the firework power limit */
	public int fireworkPowerLimit = 4;
	/** Min amount of fireworks needed to be spawned upon starting a show */
	public int minFireworkCount = 30;
	/** Used to interpret strings into music types */
	protected MusicTypeInterpreter musicInterpreter;
	/** Used to launch play music events when a player specifies a song in this command */
	protected MusicListener musicListener;
	
	public FireworksCommand (MusicListener musicListener)
	{
		super (CommandType.FIREWORKS);
		this.musicInterpreter = new MusicTypeInterpreter();
		this.musicListener = musicListener;
	}
	
	/** Launches a fireworks show!
	 *  Command Usage:
	 *     /fireworks <radius> <power> <ammo-count> <music-type> */
	@Override
	public boolean onCommand(CommandSender sender, Command command, String type, String[] args) 
	{
		if (sender instanceof Player)
		{
			Player player = (Player) sender;
			
			try
			{
				int n = args.length;
				
				if (n < 3)
				{
					player.sendMessage(ChatColor.RED + "Insuffient parameters!");
					return false;
				}
				
				// Prevent the player from launching fireworks in the Nether
				if (player.getWorld().getEnvironment() == Environment.NETHER)
				{
					player.sendMessage(ChatColor.RED + "Sorry, but we are unable to launch fireworks in this dimension.");
					return false;
				}
				
				// Checks if the player's cooldown timer has expired.
				Calendar lastUse = new FireworkCommandMetadata().getCalendarFromHolder(player);
				if (lastUse != null && !AGCraftPlugin.plugin.enableDebugMode)
				{
					Calendar now = Calendar.getInstance();
					if (now.compareTo(lastUse) < 0)
					{
						long seconds = (lastUse.getTimeInMillis() - now.getTimeInMillis()) / 1000;
						player.sendMessage (ChatColor.RED + "Please wait " + ChatColor.GOLD + seconds + ChatColor.RED + " seconds before using this command again!");
						return false;
					}
				}
				
				int radius = this.parseBetweenRange(args[0], 1, fireworkSpreadLimit, "Radius");
				int power = this.parseBetweenRange(args[1], 1, fireworkPowerLimit, "Power");
				int ammoCount = this.parseBetweenRange(args[2], minFireworkCount, fireworkLimit, "Ammo Count");
				
				if (n >= 4 && !args[3].isEmpty())
				{
					MusicType music = this.musicInterpreter.searchTrie(args[3]);
					if (music != null)
					{
						// Plays the custom firework music for all players within range of the player launching the
						// firework show. This should also prevent sound overlapping issues.
						List <Player> nearbyPlayers = ScanEntities.ScanNearbyPlayers(player, 48);
						if (!nearbyPlayers.contains(player))
						{
							nearbyPlayers.add(player);
						}
						
						for (Player nearbyPlayer : nearbyPlayers)
						{
							this.musicListener.soundPlayer.stopAllSoundsNearPlayer(nearbyPlayer);
							this.musicListener.soundPlayer.playCustomMusicNearPlayer(music, nearbyPlayer, SoundPlayerState.FIREWORK, this.musicListener.fireworkMusicVolume);
						}
					}
				}
				
				new SpawnFireworksOnLocationTask (player.getLocation(), radius, power, ammoCount).runTaskTimer(AGCraftPlugin.plugin, 30, 15);
				AGCraftPlugin.plugin.getServer().broadcastMessage(ChatColor.GOLD + sender.getName() + ChatColor.AQUA + " started a fireworks show!");
				Calendar cooldown = Calendar.getInstance();
				cooldown.add(CalendarField.MINUTES.getField(), cooldownTimer);
				new FireworkCommandMetadata (cooldown).addStringMetadata(player);
			}
			
			catch (ParseException e)
			{
				e.printStackTrace();
				player.sendMessage (ChatColor.RED + "Detected an invalid parameter.");
				return false;
			}
			
			catch (NumberFormatException ne)
			{
				player.sendMessage(ChatColor.RED + ne.getMessage());
				return false;
			}
			
			return true;
		}
		return false;
	}
	
	protected int parseBetweenRange (String param, int min, int max, String name) throws NumberFormatException
	{
		int result = Integer.parseInt(param);
		if (!(result >= min && result <= max))
		{
			throw new NumberFormatException ("The value for " + name + " must be between " + min + " and " + max + ".");
		}
		return result;
	}

	@Override
	public void loadConfigVariables(ServerConfigFile config) 
	{
		this.cooldownTimer = config.getValueAsInteger(COOLDOWN_TIMER_TAG);
		this.fireworkLimit = config.getValueAsInteger(FIREWORK_LIMIT);
		this.fireworkPowerLimit = config.getValueAsInteger(FIREWORK_POWER_LIMIT);
		this.fireworkSpreadLimit = config.getValueAsInteger(FIREWORK_SPREAD_LIMIT);
		this.minFireworkCount = config.getValueAsInteger(MIN_FIREWORK_COUNT);
	}

}
