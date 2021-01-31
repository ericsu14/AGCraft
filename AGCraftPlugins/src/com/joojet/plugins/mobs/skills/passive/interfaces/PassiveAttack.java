package com.joojet.plugins.mobs.skills.passive.interfaces;

import org.bukkit.entity.LivingEntity;

import com.joojet.plugins.mobs.monsters.MobEquipment;

/** A subset of passive skills that either modifies the damage dealt by
 *  living entities (with this passive skill) to other entities or
 *  applies a custom effect to the target when hit. */
public interface PassiveAttack 
{
	/** Allows custom behavior to be applied onto any damage dealt by the skill caster with this skill.
	 *  @param damage Original damage dealt to the target
	 *  @param damager The LivingEntity that caused the damage event
	 *  @param target The LivingEntity that is victim of the damage event
	 *  @param damagerEquipment The MobEquipment class belonging to the damager
	 *  @return Bonus damage dealt to the target (not incliding the original damage)*/
	public int modifyOutgoingDamageEvent (double damage, LivingEntity damager, LivingEntity target, MobEquipment damagerEquipment);
	
	/** Allows custom behavior to be applied onto any damage taken by the skill caster with this skill.
	 *  @param damage Original damage dealt to the target
	 *  @param damager The LivingEntity that caused the damage event
	 *  @param target The LivingEntity that is victim of the damage event
	 *  @param targetEquipment The MobEquipment class belonging to the target
	 *  @return Bonus damage taken by the target (not incliding the original damage)*/
	public int modifyIncomingDamageEvent (double damage, LivingEntity damager, LivingEntity target, MobEquipment targetEquipment);
}
