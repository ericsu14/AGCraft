package com.joojet.plugins.mobs.skills.runnable;

import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

import org.bukkit.entity.LivingEntity;
import org.bukkit.scheduler.BukkitRunnable;

/** A runnable responsible for running all custom entity's mob skill tasks every second */
public class MobSkillRunner extends BukkitRunnable 
{
	/** Stores a registry of mob skill tasks for every custom monster (that hash skills)
	 *  in the server */
	protected ConcurrentHashMap <UUID, MobSkillTask> mobSkillRegistry;
	
	public MobSkillRunner ()
	{
		this.mobSkillRegistry = new ConcurrentHashMap <UUID, MobSkillTask> ();
	}
	
	@Override
	public void run() 
	{
		for (MobSkillTask task : mobSkillRegistry.values())
		{
			task.run();
		}
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
