package com.joojet.plugins.mobs.bossbar;

import java.util.HashMap;
import java.util.Stack;
import java.util.UUID;

import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class BossBarTaskRunner extends BukkitRunnable
{
	/** Stores all dead BossBarTasks collected from every tick */
	protected Stack <UUID> deadTasks;
	
	/** A HashMap storing all UUID to BossBarTask mappings */
	protected HashMap <UUID, BossBarTask> activeBossBars;
	
	public BossBarTaskRunner ()
	{
		this.activeBossBars = new HashMap <UUID, BossBarTask> ();
		this.deadTasks = new Stack <UUID> ();
	}

	@Override
	public void run() 
	{
		for (BossBarTask task : this.activeBossBars.values())
		{
			task.update();
			
			if (!task.isActive())
			{
				this.deadTasks.push(task.getBossUUID());
			}
		}
		
		while (!deadTasks.isEmpty())
		{
			this.removeBossBarTask(deadTasks.pop());
		}
	}
	
	public void addBossBarTask (UUID uuid, BossBarTask task)
	{
		this.activeBossBars.put(uuid, task);
	}
	
	public void removeBossBarTask (UUID uuid)
	{
		BossBarTask removedTask = activeBossBars.remove(uuid);
		removedTask.cleanup();
	}
	
	/** Adds a new player to a BossBar instance, if one exists
	 *  @param uuid UUID of the boss entity holding the BossBarTask instance
	 *  @param player Player to be added to the boss entity's BossBar instance */
	public void addPlayerToBossBar (UUID uuid, Player player)
	{
		if (this.containsTask(uuid))
		{
			this.activeBossBars.get(uuid).addPlayerToBossBar(player);
		}
	}
	
	/** Removes a player from a boss entity's BossBar instance
	 *  @param uuid UUID of the boss entity holding the BossBarTask instance
	 *  @param player Player to be removed from the boss entity's BossBar instance */
	public void removePlayerFromBossBar (UUID uuid, Player player)
	{
		if (this.containsTask(uuid))
		{
			this.activeBossBars.get(uuid).removePlayerFromBossBar(player);
		}
	}
	
	public boolean containsTask (UUID uuid)
	{
		return this.activeBossBars.containsKey(uuid);
	}
	
}
