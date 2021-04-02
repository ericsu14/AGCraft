package com.joojet.plugins.mobs.skills.passive.interfaces;

import org.bukkit.entity.LivingEntity;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;

import com.joojet.plugins.mobs.monsters.MobEquipment;

public interface PassiveEnvironmental 
{
	/** Modifies the environmental-based attacks (such as cactus, fire, lightning, etc.)
	 *  by applying or subtracting an added bonus to the existing damage to be inflicted onto the entity
	 *  If Double.MIN_VALUE is returned, the damage event is cancelled.
	 *  
	 *  @param damage original damage dealt to the target
	 *  @param damageReason The damage-reason for the incoming damage event
	 *  @param target The entity damaged by the event
	 *  @param targetEquipment The target's MobEquipment instance
	 *  @return Bonus damage dealt to the target (not including the original damage). If Double.MIN_VALUE is returned, the damage event will be canceled. */
	public double modifyIncomingEnvironmentalDamageEvent (double damage, DamageCause damageReason, LivingEntity target, MobEquipment targetEquipment);
	
}
