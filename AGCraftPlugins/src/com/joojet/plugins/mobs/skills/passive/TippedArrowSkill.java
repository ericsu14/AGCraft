package com.joojet.plugins.mobs.skills.passive;

import org.bukkit.entity.Arrow;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Projectile;

import com.joojet.plugins.mobs.monsters.MobEquipment;
import com.joojet.plugins.mobs.skills.passive.interfaces.PassiveProjectile;

public class TippedArrowSkill extends AbstractPassiveSkill implements PassiveProjectile 
{
	/** If the entity has a tipped arrow in its MobEquipment instance, transform the projectile into that
	 *  tipped arrow. */
	@Override
	public void modifyProjectile(LivingEntity shooter, Projectile projectile, MobEquipment shooterEquipment) 
	{
		if (shooter == null || shooterEquipment == null || projectile == null || 
				!(projectile instanceof Arrow))
		{
			return;
		}
		
		Arrow arrow = (Arrow) projectile;
		
		if (shooterEquipment.hasTippedArrow())
		{
			shooterEquipment.getTippedArrow().applyPotionDataToArrow(arrow);
		}
	}

}
