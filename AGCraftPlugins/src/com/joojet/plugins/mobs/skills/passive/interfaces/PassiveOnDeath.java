package com.joojet.plugins.mobs.skills.passive.interfaces;

import org.bukkit.entity.LivingEntity;

import com.joojet.plugins.mobs.monsters.MobEquipment;

public interface PassiveOnDeath 
{
	/**
	 * Activates a passive effect when an entity dies. The death event can be canceled
	 * if this function returns false.
	 * @param deadEntity Entity who is dying
	 * @param deadEntityEqipment MobEquipment instance of the entity who is dying
	 *  */
	public boolean handleDeathEvent (LivingEntity entity, MobEquipment deadEntityEquipment);
}
