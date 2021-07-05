package com.joojet.plugins.mobs.skills.passive.interfaces;

import org.bukkit.entity.LivingEntity;

import com.joojet.plugins.mobs.monsters.MobEquipment;

public interface PassiveOnExplode 
{
	/**
	 * Runs a custom effect when an entity explodes. The explosion event can be canceled if the event returns false.
	 * @param entity Entity exploding
	 * @param entityEquipment MobEquipment instance of the entity exploding
	 */
	public boolean handleExplosionEvent (LivingEntity entity, MobEquipment entityEquipment);
}
