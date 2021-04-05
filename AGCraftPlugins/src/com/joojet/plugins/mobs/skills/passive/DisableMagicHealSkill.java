package com.joojet.plugins.mobs.skills.passive;

import org.bukkit.entity.LivingEntity;
import org.bukkit.event.entity.EntityRegainHealthEvent.RegainReason;

import com.joojet.plugins.mobs.monsters.MobEquipment;
import com.joojet.plugins.mobs.skills.passive.interfaces.PassiveRegeneration;

/** Disables the ability from the skill-caster from being healed from magic based heals from
 *  health or harming potions. */
public class DisableMagicHealSkill extends AbstractPassiveSkill implements PassiveRegeneration
{
	/** Certain mobs possess potion effects that heals them to full health upon spawning due to their equipment
	 *  increasing their max health. Thus, a short delay is necessary in order for those effects to heal the monster
	 *  properly (in seconds). */
	private int delayBeforeActivation = 2;
	
	@Override
	public void update (LivingEntity caster)
	{
		super.update(caster);
		this.delayBeforeActivation = (this.delayBeforeActivation - 1 <= 0) ? this.delayBeforeActivation - 1 : 0;
	}
	
	@Override
	public double modifyRegenerationEvent(double health, RegainReason regainReason, LivingEntity target,
			MobEquipment targetEquipment) 
	{
		if (delayBeforeActivation <= 0 && regainReason == RegainReason.MAGIC)
		{
			return Double.MIN_VALUE;
		}
		
		return 0;
	}
	
}
