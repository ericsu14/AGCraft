package com.joojet.plugins.mobs.skills.passive;

import java.util.List;

import org.bukkit.entity.LivingEntity;

import com.joojet.plugins.mobs.DamageDisplayListener;
import com.joojet.plugins.mobs.bossbar.BossBarController;
import com.joojet.plugins.mobs.interpreter.MonsterTypeInterpreter;
import com.joojet.plugins.mobs.skills.AbstractSkill;
import com.joojet.plugins.mobs.skills.enums.SkillPropetry;

/** A set of skills that cannot be actively used by the skill-caster,
 *  but relies on server event listeners to give passive effects
 *  to the skill caster. */
public abstract class AbstractPassiveSkill extends AbstractSkill
{
	public AbstractPassiveSkill() 
	{
		super(SkillPropetry.PASSIVE, 0, 0, 0, 0);
	}

	@Override
	protected void handleSkill(LivingEntity caster, List<LivingEntity> allies, List<LivingEntity> enemies,
			DamageDisplayListener damageDisplayListener, MonsterTypeInterpreter monsterTypeInterpreter,
			BossBarController bossBarController) 
	{
		// Do nothing
	}

	@Override
	protected boolean checkConditons(LivingEntity caster, List<LivingEntity> allies, List<LivingEntity> enemies)
	{
		return false;
	}

	@Override
	protected boolean checkConditions(LivingEntity caster) 
	{
		return false;
	}

}
