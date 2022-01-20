package com.joojet.plugins.mobs.skills.passive;

import java.util.Arrays;

import org.bukkit.craftbukkit.v1_18_R1.entity.CraftMob;
import org.bukkit.entity.LivingEntity;

import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.goal.PanicGoal;
import net.minecraft.world.entity.ai.goal.WrappedGoal;


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
		Mob nmsMob = ((CraftMob) caster).getHandle();
		// Attempts to remove the PANIC pathfinder goal from the chicken
		Object[] rawGoals = nmsMob.goalSelector.getRunningGoals().toArray();
		WrappedGoal[] goals = Arrays.copyOf(rawGoals, rawGoals.length, WrappedGoal[].class);
		for (WrappedGoal goal : goals)
		{
			if (goal.getGoal() instanceof PanicGoal)
			{
				nmsMob.goalSelector.removeGoal(goal);
				removed = true;
			}
		}
	}
}
