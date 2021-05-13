package com.joojet.plugins.mobs.skills.passive;

import org.bukkit.entity.LivingEntity;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;

import com.joojet.plugins.mobs.monsters.MobEquipment;
import com.joojet.plugins.mobs.skills.passive.interfaces.PassiveEnvironmental;

public class DisableSuffocationSkill extends AbstractPassiveSkill implements PassiveEnvironmental
{
	/** Disables suffocation damage if the skill-caster possesses this skill */
	@Override
	public double modifyIncomingEnvironmentalDamageEvent(double damage, DamageCause damageReason, LivingEntity target,
			MobEquipment targetEquipment) 
	{
		if (damageReason == DamageCause.SUFFOCATION)
		{
			return Double.MIN_VALUE;
		}
		return 0;
	}

}
