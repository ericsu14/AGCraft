package com.joojet.plugins.mobs.skills.passive.interfaces;

import org.bukkit.entity.LivingEntity;
import org.bukkit.event.entity.EntityRegainHealthEvent.RegainReason;

import com.joojet.plugins.mobs.monsters.MobEquipment;

public interface PassiveRegeneration 
{
	/** Allows the amount of health restored by health regeneration events to be modified by returning a bonus
	 *  value that either adds on or subtracts from the original health to be restored.
	 *  If a value of Double.MIN_VALUE is returned, the event is canceled.
	 *  @param health Original amount of health restored
	 *  @param regainReason Identifies the cause of the regain event
	 *  @param target The entity being healed
	 *  @param targetEquipment The MobEquipment instance belonging to the target  */
	public double modifyRegenerationEvent (double health, RegainReason regainReason, LivingEntity target, MobEquipment targetEquipment);
}
