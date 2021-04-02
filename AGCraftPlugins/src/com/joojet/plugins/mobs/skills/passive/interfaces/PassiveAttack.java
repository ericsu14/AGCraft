package com.joojet.plugins.mobs.skills.passive.interfaces;

import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;

import com.joojet.plugins.mobs.monsters.MobEquipment;

/** A subset of passive skills that either modifies the damage dealt by
 *  living entities (with this passive skill) to other entities or
 *  applies a custom effect to the target when hit. */
public interface PassiveAttack 
{
	/** Allows custom behavior to be applied onto any damage dealt by the skill caster with this skill.
	 *  @param damage Original damage dealt to the target
	 *  @param source The original source of the entity attack, which can either be a projectile or the entity itself that
	 *                triggered this event.
	 *  @param damager The LivingEntity that caused the damage event
	 *  @param target The LivingEntity that is victim of the damage event
	 *  @param damagerEquipment The MobEquipment class belonging to the damager
	 *  @param targetEquipment The MobEquipment class belonging to the target
	 *  @return Bonus damage dealt to the target (not including the original damage). If Double.MIN_VALUE is returned, the damage event will be canceled. */
	public double modifyOutgoingDamageEvent (double damage, Entity source, LivingEntity damager, LivingEntity target, MobEquipment damagerEquipment,
			MobEquipment targetEquipment);
	
	/** Allows custom behavior to be applied onto any damage taken by the skill caster with this skill.
	 *  @param damage Original damage dealt to the target
	 * 	@param source The original source of the entity attack, which can either be a projectile or the entity itself that
	 *         triggered this event.
	 *  @param damager The LivingEntity that caused the damage event
	 *  @param target The LivingEntity that is victim of the damage event
	 *  @param damagerEquipment The MobEquipment class belonging to the damager
	 *  @param targetEquipment The MobEquipment class belonging to the target
	 *  @return Bonus damage taken by the target (not including the original damage)*/
	public double modifyIncomingDamageEvent (double damage, Entity source, LivingEntity damager, LivingEntity target, MobEquipment damagerEquipment, 
			MobEquipment targetEquipment);
}
