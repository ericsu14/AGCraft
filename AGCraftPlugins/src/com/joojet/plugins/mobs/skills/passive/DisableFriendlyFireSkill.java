package com.joojet.plugins.mobs.skills.passive;

import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;

import com.joojet.plugins.mobs.monsters.MobEquipment;
import com.joojet.plugins.mobs.skills.passive.interfaces.PassiveAttack;

public class DisableFriendlyFireSkill extends AbstractPassiveSkill implements PassiveAttack
{

	/**
	 * Disables friendly fire to allies when enabled on mobs who possesses this skill
	 */
	@Override
	public double modifyOutgoingDamageEvent(double damage, Entity source, LivingEntity damager, LivingEntity target,
			MobEquipment damagerEquipment, MobEquipment targetEquipment) 
	{
		return damagerEquipment.isAlliesOf(damager, target, targetEquipment) ? Double.MIN_VALUE : 0;
	}

	@Override
	public double modifyIncomingDamageEvent(double damage, Entity source, LivingEntity damager, LivingEntity target,
			MobEquipment damagerEquipment, MobEquipment targetEquipment) 
	{
		return 0;
	}

}
