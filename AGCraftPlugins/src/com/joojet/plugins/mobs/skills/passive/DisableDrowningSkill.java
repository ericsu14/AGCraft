package com.joojet.plugins.mobs.skills.passive;

import org.bukkit.entity.LivingEntity;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;

import com.joojet.plugins.mobs.monsters.MobEquipment;
import com.joojet.plugins.mobs.skills.passive.interfaces.PassiveEnvironmental;

/** Allows the custom monster to no longer drown to death */
public class DisableDrowningSkill extends AbstractPassiveSkill implements PassiveEnvironmental 
{
	@Override
	public double modifyIncomingEnvironmentalDamageEvent(double damage, DamageCause damageReason, LivingEntity target,
			MobEquipment targetEquipment) 
	{
		if (damageReason == DamageCause.DROWNING || damageReason == DamageCause.DRYOUT)
		{
			return Double.MIN_VALUE;
		}
		return 0;
	}

}
