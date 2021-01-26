package com.joojet.plugins.mobs.skills.visual;

import java.util.List;

import org.bukkit.entity.LivingEntity;

import com.joojet.plugins.mobs.DamageDisplayListener;
import com.joojet.plugins.mobs.bossbar.BossBarController;
import com.joojet.plugins.mobs.interpreter.MonsterTypeInterpreter;
import com.joojet.plugins.mobs.skills.AbstractSkill;
import com.joojet.plugins.mobs.skills.enums.SkillPropetry;

/** A set of skills that does not do anything game-changing, but allows
 *  the entity to emit a permanent particle effect around the entity until it dies.
 *  
 *  These skills will never be actively used and will never affect the skill selection calculations,
 *  but it will the skill's update function to display a visual effect every second.
 *  
 *  This can be used to create an "aura" around an entity for example. */
public abstract class AbstractVisualSkill extends AbstractSkill
{

	public AbstractVisualSkill() 
	{
		super(SkillPropetry.VISUAL, 0, Integer.MAX_VALUE, 0, 0);
	}
	
	/** Displays visual effects around the skill caster
	 *  @param caster Skill caster where visual effects are spawned on */
	protected abstract void displayVisualEffects (LivingEntity caster);

	@Override
	protected void handleSkill(LivingEntity caster, List<LivingEntity> allies, List<LivingEntity> enemies,
			DamageDisplayListener damageDisplayListener, MonsterTypeInterpreter monsterTypeInterpreter,
			BossBarController bossBarController) 
	{
		// No effect
	}
	
	/** Skill can never be used as it is just a passive visual effect skill. Therefore, this will always returns false */
	@Override
	protected boolean checkConditons(LivingEntity caster, List<LivingEntity> allies, List<LivingEntity> enemies) 
	{
		return false;
	}
	
	/** Skill can never be used as it is just a passive visual effect skill. Therefore, this will always returns false */
	@Override
	protected boolean checkConditions(LivingEntity caster) 
	{
		return false;
	}
	
	@Override
	public void update (LivingEntity caster)
	{
		super.update(caster);
		this.displayVisualEffects(caster);
	}
	
}
