package com.joojet.plugins.warp.commands.tasks;

import java.sql.SQLException;
import java.util.List;

import org.bukkit.entity.Player;

import com.joojet.plugins.agcraft.asynctasks.AsyncTask;
import com.joojet.plugins.agcraft.util.Pair;
import com.joojet.plugins.warp.constants.WarpAccessLevel;
import com.joojet.plugins.warp.database.LocationDatabaseManager;
import com.joojet.plugins.warp.database.LocationEntry;

public abstract class AsyncGetLocations extends AsyncTask<Pair <List <LocationEntry>, List <LocationEntry>>> 
{
	/** Number of arguments in the command */
	protected int argsLength;
	/** Access level the player is searching for */
	protected WarpAccessLevel accessLevel;
	/** The player executing this command */
	protected Player player;
	
	public AsyncGetLocations (int argsLength, WarpAccessLevel accessLevel, Player player)
	{
		this.argsLength = argsLength;
		this.accessLevel = accessLevel;
		this.player = player;
	}
	
	@Override
	protected Pair<List<LocationEntry>, List<LocationEntry>> getAsyncData()
			throws SQLException, RuntimeException 
	{
		Pair<List<LocationEntry>, List<LocationEntry>> result = new Pair <List <LocationEntry>, List <LocationEntry>> (null, null);
		// Prints all public locations
		if (this.argsLength == 0 || this.accessLevel == WarpAccessLevel.PUBLIC)
		{
			result.setKey(LocationDatabaseManager.getLocationsAsList(this.player, WarpAccessLevel.PUBLIC));
		}
		if (this.argsLength == 0 || this.accessLevel == WarpAccessLevel.PRIVATE)
		{
			result.setEntry(LocationDatabaseManager.getLocationsAsList(this.player, WarpAccessLevel.PRIVATE));
		}
		return result;
	}

}
