package com.joojet.plugins.mobs.skills.passive.interfaces;

import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Projectile;

import com.joojet.plugins.mobs.monsters.MobEquipment;

/** A subset of passive skills that modifies the attributes
 *  of a launched projectile from a skill-caster using the modifyProjectile function. */
public interface PassiveProjectile 
{
	/** Applies custom attributes to a launched projectile
	 *  @param shooter - The LivingEntity who fired the projectile
	 *  @param projectile - The projectile entity that was fired by a living entity
	 *  @param shooterEquipment - The MobEquipment instance attached to the shooter */
	public void modifyProjectile (LivingEntity shooter, Projectile projectile, MobEquipment shooterEquipment);
}
