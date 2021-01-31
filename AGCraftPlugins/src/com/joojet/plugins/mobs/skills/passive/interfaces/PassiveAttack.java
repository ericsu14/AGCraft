package com.joojet.plugins.mobs.skills.passive.interfaces;

import org.bukkit.entity.LivingEntity;

import com.joojet.plugins.mobs.interpreter.MonsterTypeInterpreter;

/** A subset of passive skills that either modifies the damage dealt by
 *  living entities (with this passive skill) to other entities or
 *  applies a custom effect to the target when hit. */
public interface PassiveAttack 
{
	/** Adds custom modifications to an EntityDamageEvent.
	 *  @param damager The LivingEntity that caused the damage event
	 *  @param target The LivingEntity that is victim of the damage event
	 *  @param damage Original damage dealt to the target
	 *  @param monsterInterpreter - A reference to the custom mob reference class
	 *  @return A newly modified damage output to the target*/
	public int modifyAttackEvent (LivingEntity damager, LivingEntity target, double damage, MonsterTypeInterpreter monsterInterpreter); 
}
