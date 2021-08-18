package com.joojet.plugins.mobs.skills.passive;

import java.util.Arrays;
import org.bukkit.craftbukkit.v1_17_R1.entity.CraftMob;
import org.bukkit.entity.LivingEntity;

import net.minecraft.world.entity.EntityInsentient;
import net.minecraft.world.entity.ai.goal.PathfinderGoalPanic;
import net.minecraft.world.entity.ai.goal.PathfinderGoalWrapped;


/** Attempts to remove the panic pathfinder mob-goal from the mob wielding this skill,
 *  forcing it to be fearless when attacking mobs. */
public class FearlessSkill extends AbstractPassiveSkill 
{
	/** Keeps track if the panic pathfiner goal is removed or not. */
	private boolean removed = false;
	
	/** Attempt to remove the PANIC pathfinder goal from a mob every tick, forcing the
	 *  otherwise passive mob to be more coordinated in its attacks. */
	@Override
	public void update (LivingEntity caster)
	{
		super.update(caster);
		
		// Do not run if the PANIC pathfinder goal is already removed
		if (removed)
		{
			return;
		}
		
		// Cast this into a NMS entity monster
		EntityInsentient nmsMob = ((CraftMob) caster).getHandle();
		// Attempts to remove the PANIC pathfinder goal from the chicken
		Object[] rawGoals = nmsMob.bP.d().toArray();
		PathfinderGoalWrapped[] goals = Arrays.copyOf(rawGoals, rawGoals.length, PathfinderGoalWrapped[].class);
		for (PathfinderGoalWrapped goal : goals)
		{
			if (goal.j() instanceof PathfinderGoalPanic)
			{
				nmsMob.bP.a(goal.j());
				removed = true;
			}
		}
	}
}
