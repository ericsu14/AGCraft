package com.joojet.plugins.warp.commands.tasks;

import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;

import com.joojet.plugins.agcraft.asynctasks.AsyncDatabaseTask;
import com.joojet.plugins.agcraft.asynctasks.response.DatabaseResponse;
import com.joojet.plugins.coordinates.commands.GetCoordinates;
import com.joojet.plugins.warp.commands.Warp;
import com.joojet.plugins.warp.database.LocationDatabaseManager;
import com.joojet.plugins.warp.scantools.ScanEntities;

public class AsyncWarpPlayerTask extends AsyncDatabaseTask<DatabaseResponse <Location>>
{
	/** The player's bed spawn location */
	protected Location playerBedSpawnLocation;
	/** The player executing this command */
	protected Player player;
	/** The UUID of the player */
	protected UUID playerUUID;
	/** The name of the location the player is warping to */
	protected String locationName;
	
	public AsyncWarpPlayerTask (Player player, String locationName)
	{
		this.playerBedSpawnLocation = player.getBedSpawnLocation();
		this.player = player;
		this.playerUUID = player.getUniqueId();
		this.locationName = locationName;
	}
	
	@Override
	protected DatabaseResponse <Location> getDataFromDatabase() throws SQLException, RuntimeException
	{
		boolean status = true;
		Location result = null;
		StringBuilder message = new StringBuilder ();
		try
		{
			switch (this.locationName)
			{
				case Warp.home:
					result = this.playerBedSpawnLocation;
					break;
				default:
					result = LocationDatabaseManager.getlocation(player, locationName);
					break;
			}
		}
		catch (RuntimeException re)
		{
			message.append(re.getMessage());
			status = false;
		}
		
		return new DatabaseResponse <Location> (result, message.toString(), status);
	}

	@Override
	protected void handlePromise(DatabaseResponse <Location> response) 
	{
		// Null check for player
		if (this.player == null)
		{
			return;
		}
		
		// Send error message when something went wrong with location retrieval
		if (!response.getStatus())
		{
			this.player.sendMessage(ChatColor.RED + response.getMessage());
			return;
		}
		
		Location location = response.getData();
		
		if (location == null)
		{
			if (locationName.equals(Warp.home))
			{
				this.player.sendMessage(ChatColor.RED + "Your home bed is either missing or obstructed.");
			}
			else
			{
				this.player.sendMessage(ChatColor.GOLD + this.locationName + ChatColor.RED + " is not a valid stored location in our database.");
			}
			return;
		}
		
		List <Entity> ownedEntities = ScanEntities.ScanNearbyPlayerOwnedEntities(this.player, 40);
		
		// Teleports the player
		this.player.teleport(location);
		this.player.playSound(location, Sound.ENTITY_ENDERMAN_TELEPORT, 0.4f, 1f);
		
		// Teleports any player-owned entities to the player's current location as well
		StringBuilder teleportedEntities = new StringBuilder ();
		int index = 0;
		for (Entity entity : ownedEntities)
		{
			entity.teleport(this.player.getLocation());
			
			// Appends an "and" to the last element of the string if there is more than one owned entity to teleport
			if (index == ownedEntities.size() - 1 && ownedEntities.size() > 1)
			{
				teleportedEntities = new StringBuilder (teleportedEntities.substring(0, teleportedEntities.length() - 2));
				teleportedEntities.append(ChatColor.GOLD);
				teleportedEntities.append(" and ");
			}
			
			teleportedEntities.append(ChatColor.AQUA);
			teleportedEntities.append(entity.getName());
			
			// Appends a comma on the end of the string if there is more than one entity to teleport
			if (index < ownedEntities.size() - 1)
			{
				teleportedEntities.append(", ");
			}
			
			++index;
		}
		// Notifies the player that their owned entities are teleported with them.
		if (!ownedEntities.isEmpty())
		{
			this.player.sendMessage(ChatColor.GOLD + "Teleported " + teleportedEntities.toString() + ChatColor.GOLD + " to your location. Please rejoin the server if they are invisible.");
		}
		this.player.sendMessage(ChatColor.GOLD + "Teleported you to location " + ChatColor.AQUA + this.locationName + ChatColor.GOLD + " at " + GetCoordinates.getCoordinates(this.player));
	}

}
