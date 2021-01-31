package com.joojet.plugins.mobs.skills.passive;

import org.bukkit.entity.AbstractArrow;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Projectile;

import com.joojet.plugins.mobs.enums.MonsterStat;
import com.joojet.plugins.mobs.monsters.MobEquipment;
import com.joojet.plugins.mobs.skills.passive.interfaces.PassiveProjectile;

/** Projectiles shot from custom mobs with a critical chance arrow modifier
 *  has a random chance of becoming a critical shot, allowing the arrow
 *  to deal more damage */
public class CriticalShotSkill extends AbstractPassiveSkill implements PassiveProjectile
{
	/** The projectile is made a critical hit depending on the critical hit chance
	 *  set on the projectile shooter's MobEquipment instance */
	@Override
	public void modifyProjectile(LivingEntity shooter, Projectile projectile, MobEquipment shooterEquipment) 
	{
		if (shooter == null || shooterEquipment == null || projectile == null || 
				!(projectile instanceof AbstractArrow))
		{
			return;
		}
		
		AbstractArrow arrow = (AbstractArrow) projectile;
		
		if (shooterEquipment != null &&
				shooterEquipment.containsStat(MonsterStat.ARROW_CRITICAL_CHANCE))
		{
			arrow.setCritical(this.getRandomGenerator().nextDouble() <= shooterEquipment.getStat(MonsterStat.ARROW_CRITICAL_CHANCE));
		}
			
	}

}
