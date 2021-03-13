package com.joojet.plugins.mobs.skills.runnable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import org.bukkit.entity.LivingEntity;
import org.bukkit.scheduler.BukkitRunnable;

/** A runnable responsible for running all custom entity's mob skill tasks every second */
public class MobSkillRunner extends BukkitRunnable 
{
	/** Stores a registry of mob skill tasks for every custom monster (that hash skills)
	 *  in the server */
	protected HashMap <UUID, MobSkillTask> mobSkillRegistry;
	
	public MobSkillRunner ()
	{
		this.mobSkillRegistry = new HashMap <UUID, MobSkillTask> ();
	}
	
	@Override
	public void run() 
	{
		List <UUID> deadSkillTasks = new ArrayList <UUID> ();
		for (MobSkillTask task : mobSkillRegistry.values())
		{
			if (task.canRunTask())
			{
				task.run();
			}
			else
			{
				deadSkillTasks.add(task.getCasterUUID());
			}
		}
		
		// Removes dead skill tasks from the mob skill registry
		for (UUID deadTask : deadSkillTasks)
		{
			this.mobSkillRegistry.remove(deadTask);
		}
	}
	
	/** Retrieves the MobSkillTask instance attached to the entity with its assigned UUID
	 *  @param UUID of the living entity whose task we are retrieving */
	public MobSkillTask getSkillTask (UUID uuid)
	{
		return this.mobSkillRegistry.get(uuid);
	}
	
	public void attachSkillToEntity (LivingEntity entity, MobSkillTask task)
	{
		if (!this.containsSkill(entity))
		{
			this.mobSkillRegistry.put(entity.getUniqueId(), task);
		}
	}
	
	public boolean containsSkill (LivingEntity entity)
	{
		return this.mobSkillRegistry.containsKey(entity.getUniqueId());
	}
	
	public void removeSkillFromEntity (LivingEntity entity)
	{
		if (this.containsSkill(entity))
		{
			this.mobSkillRegistry.remove(entity.getUniqueId());
		}
	}

}
