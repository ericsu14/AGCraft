package com.joojet.plugins.mobs.skills.runnable;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import org.bukkit.entity.LivingEntity;
import org.bukkit.scheduler.BukkitRunnable;

import com.joojet.plugins.mobs.enums.MonsterStat;
import com.joojet.plugins.mobs.enums.MonsterType;
import com.joojet.plugins.mobs.monsters.MobEquipment;

/** A runnable responsible for running all custom entity's mob skill tasks every second */
public class MobSkillRunner extends BukkitRunnable 
{
	/** Stores a registry of mob skill tasks for every custom monster (that hash skills)
	 *  in the server */
	protected HashMap <UUID, MobSkillTask> mobSkillRegistry;
	/** Keeps track of number of active custom monsters in the world for each
	 *  unique custom monster type */
	protected EnumMap <MonsterType, Integer> customMonsterFrequency;
	
	public MobSkillRunner ()
	{
		this.mobSkillRegistry = new HashMap <UUID, MobSkillTask> ();
		this.customMonsterFrequency = new EnumMap <MonsterType, Integer> (MonsterType.class);
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
			this.decrementMobFrequencyCount(mobSkillRegistry.get(deadTask).getMobEquipment().getMonsterType());
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
			// Updates custom monster count for that entity's custom type
			MonsterType type = task.getMobEquipment().getMonsterType();
			this.customMonsterFrequency.put(type, this.customMonsterFrequency.getOrDefault(type, 0) + 1);
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
			MobSkillTask task = this.mobSkillRegistry.remove(entity.getUniqueId());
			// Decrements custom monster count for that entity's custom type
			this.decrementMobFrequencyCount(task.getMobEquipment().getMonsterType());
		}
	}
	
	/** Decrements mob frequency count for the passed MonsterType */
	protected void decrementMobFrequencyCount (MonsterType type)
	{
		if (this.customMonsterFrequency.containsKey(type)
				&& this.customMonsterFrequency.get(type) > 0)
		{
			this.customMonsterFrequency.put(type, this.customMonsterFrequency.get(type) - 1);
		}
	}
	
	/** Returns the total number of active custom monsters associated with
	 *  the passed monster type in the server. */
	public int getActiveCustomMonsterCount (MonsterType type)
	{
		return this.customMonsterFrequency.getOrDefault(type, 0);
	}
	
	/** Returns true if the number of permitted active custom monsters of that type has been reached*
	 * @param equipment Custom monster equipment being checked */
	public boolean reachedSpawnLimit (MobEquipment equipment)
	{
		if (equipment.containsStat(MonsterStat.SPAWN_LIMIT))
		{
			return this.getActiveCustomMonsterCount(equipment.getMonsterType()) >= equipment.getStat(MonsterStat.SPAWN_LIMIT);
		}
		return false;
	}

}
