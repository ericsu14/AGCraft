package com.joojet.plugins.mobs.interfaces;

import org.bukkit.entity.LivingEntity;

import net.minecraft.server.v1_16_R3.EntityInsentient;

/** If the custom mob equipment instance is an NMS SKill User,
 *  override the loadNMSSkills function to load a custom set of
 *  PathfinderGoals into the entity upon spawning. */
public interface NMSSkillUser 
{
	/** Allows the custom monster to use a custom set of NMS Pathfinder Goal skills
	 *  upon spawning. Pathfinder goals can be loaded using the nmsMob's goalSelector
	 *  instance.
	 *  
	 *  Usage Example:
	 *  nmsMob.goalSelector.a (weight, goal);
	 *  
	 *  @param nmsMob CraftBukkit instance of the entity that is spawned
	 *  @param entity Bukkit instance of the entity that is spawned*/
	public void loadNMSSkills (EntityInsentient nmsMob, LivingEntity entity);
}
