package com.joojet.plugins.mobs.skills.passive;

import org.bukkit.entity.AbstractArrow;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Projectile;

import com.joojet.plugins.mobs.enums.MonsterStat;
import com.joojet.plugins.mobs.monsters.MobEquipment;
import com.joojet.plugins.mobs.skills.passive.interfaces.PassiveProjectile;

public class ArrowDamageModifierSkill extends AbstractPassiveSkill implements PassiveProjectile 
{	
	/** If the entity has a base arrow damage modifier attribute, apply that base damage modifier to the shot
	 * projectile */
	@Override
	public void modifyProjectile(LivingEntity shooter, Projectile projectile, MobEquipment shooterEquipment) 
	{
		if (shooter == null || shooterEquipment == null || projectile == null || 
				!(projectile instanceof AbstractArrow))
		{
			return;
		}
		
		AbstractArrow arrow = (AbstractArrow) projectile;
		if (shooterEquipment.containsStat(MonsterStat.BASE_ARROW_DAMAGE))
		{
			arrow.setDamage(shooterEquipment.getStat(MonsterStat.BASE_ARROW_DAMAGE));
		}
	}

}
