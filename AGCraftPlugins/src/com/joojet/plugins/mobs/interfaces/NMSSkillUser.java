package com.joojet.plugins.mobs.interfaces;

import org.bukkit.entity.LivingEntity;

import net.minecraft.world.entity.EntityInsentient;

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
	 *  nmsMob.bO.a (weight, goal); // Goal selector changed to bO in 1.17 versions
	 *  
	 *  nmsMob.bP.a (weight, goal); // In 1.17.1, Goal selector is now named bP.
	 *  
	 *  @param nmsMob CraftBukkit instance of the entity that is spawned
	 *  @param entity Bukkit instance of the entity that is spawned*/
	public void loadNMSSkills (EntityInsentient nmsMob, LivingEntity entity);
}
