package com.joojet.plugins.mobs.skills.passive;

import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;

import com.joojet.plugins.agcraft.main.AGCraftPlugin;
import com.joojet.plugins.mobs.monsters.MobEquipment;
import com.joojet.plugins.mobs.skills.passive.interfaces.PassiveAttack;

/** Nerfs the damage output of the skill-weilder's attack by a certain percentage */
public class NerfDamageOutputSkill extends AbstractPassiveSkill implements PassiveAttack
{
	/** Damage reduction modifier for the skill-weilder's attacks */
	double damageReduction;
	
	/** Nerfs the damage output of the skill-weilder's attack by a certain percentage
	 *  based on the passed damageReduction modifier
	 *  @param damageReduction Percentage of damage taken away from the skill-weilder's attacks  */
	public NerfDamageOutputSkill (double damageReduction)
	{
		this.damageReduction = damageReduction;
	}

	
	/** Reduced outgoing damage based on the damage reduction modifier passed into this skill */
	@Override
	public double modifyOutgoingDamageEvent(double damage, Entity source, LivingEntity damager, LivingEntity target,
			MobEquipment damagerEquipment, MobEquipment targetEquipment) 
	{
		if (damageReduction > 1.0 || damageReduction < 0.0)
		{
			AGCraftPlugin.logger.warning("Damage reduction modifier set for " + damager.getCustomName() + " is not between 0.0-1.0!");
			return 0.0;
		}
		return -(damage * damageReduction);
	}

	@Override
	public double modifyIncomingDamageEvent(double damage, Entity source, LivingEntity damager, LivingEntity target,
			MobEquipment damagerEquipment, MobEquipment targetEquipment) 
	{
		return 0;
	}
	
	
	
}
