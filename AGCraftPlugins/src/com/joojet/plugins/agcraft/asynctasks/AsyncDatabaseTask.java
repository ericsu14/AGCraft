package com.joojet.plugins.agcraft.asynctasks;

import java.sql.SQLException;

import org.bukkit.scheduler.BukkitRunnable;

import com.joojet.plugins.agcraft.main.AGCraftPlugin;

public abstract class AsyncDatabaseTask <T>
{
	/** Schedules and retrieves data from an user-defined asynchronous database task, 
	 *  then synchronously schedules and launches a user-defined handler right after. */
	public void runDatabaseTask ()
	{
		new BukkitRunnable ()
		{
			@Override
			public void run ()
			{
				try 
				{
					// Gets requested data from the database
					T data = getDataFromDatabase ();
					
					// Once the data is retrieved, handle the event
					new BukkitRunnable () 
					{
						@Override
						public void run ()
						{
							handlePromise (data);
						}
					}.runTask(AGCraftPlugin.plugin);
				}
				catch (SQLException se)
				{
					se.printStackTrace();
				}
			}
		}.runTaskAsynchronously(AGCraftPlugin.plugin);
	}
	
	/** Asynchronous gets data from a database function */
	protected abstract T getDataFromDatabase () throws SQLException;
	
	/** Synchronously handles data retrieved from the asynchronous call */
	protected abstract void handlePromise (T data);
}
