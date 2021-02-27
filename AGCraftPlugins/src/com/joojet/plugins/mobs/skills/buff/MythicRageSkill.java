package com.joojet.plugins.mobs.skills.buff;

import org.bukkit.entity.LivingEntity;

import com.joojet.plugins.mobs.enums.MonsterClassifier;

public class MythicRageSkill extends RageSkill
{
	
	/** A modified variant of the rage skill that can only be activated if the average threat scores for all surrouding players
	 *  exceed mythic.*/
	public MythicRageSkill(int amplifier, int duration, double threshold) 
	{
		super(amplifier, duration, threshold);
	}
	
	/** Overrides the self-check conditions check to account for the presence of mythic-level players
	 *  in order for this skill to be used */
	@Override
	protected boolean checkConditions (LivingEntity caster)
	{
		return super.checkConditions(caster) && 
				this.spawnWeight.getAverageThreatScore(caster) >= MonsterClassifier.MYTHIC.getThreshold();
	}
}
