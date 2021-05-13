package com.joojet.plugins.mobs.skills.passive;

import org.bukkit.entity.LivingEntity;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;

import com.joojet.plugins.mobs.monsters.MobEquipment;
import com.joojet.plugins.mobs.skills.passive.interfaces.PassiveEnvironmental;

/** Prevents the custom monster from melting to death */
public class DisableMeltingSkill extends AbstractPassiveSkill implements PassiveEnvironmental {

	@Override
	public double modifyIncomingEnvironmentalDamageEvent(double damage, DamageCause damageReason, LivingEntity target,
			MobEquipment targetEquipment) 
	{
		if (damageReason == DamageCause.MELTING)
		{
			return Double.MIN_VALUE;
		}
		return 0;
	}

}
