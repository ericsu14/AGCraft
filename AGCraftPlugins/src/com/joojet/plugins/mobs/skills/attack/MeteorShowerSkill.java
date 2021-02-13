package com.joojet.plugins.mobs.skills.attack;

import java.util.List;

import org.bukkit.entity.LivingEntity;

import com.joojet.plugins.mobs.DamageDisplayListener;
import com.joojet.plugins.mobs.bossbar.BossBarController;
import com.joojet.plugins.mobs.interpreter.MonsterTypeInterpreter;

public class MeteorShowerSkill extends AbstractAttackSkill
{

	public MeteorShowerSkill(int range, int cooldown, int maxUses, int weight) 
	{
		super(range, cooldown, maxUses, weight);
	}
	
	/** Summons a meteor shower, targeting random enemies around a surrounding area */
	@Override
	protected void handleSkill(LivingEntity caster, List<LivingEntity> allies, List<LivingEntity> enemies,
			DamageDisplayListener damageDisplayListener, MonsterTypeInterpreter monsterTypeInterpreter,
			BossBarController bossBarController) 
	{
		
	}

	@Override
	protected boolean checkConditons(LivingEntity caster, List<LivingEntity> allies, List<LivingEntity> enemies) 
	{
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	protected boolean checkConditions(LivingEntity caster) 
	{
		// TODO Auto-generated method stub
		return false;
	}

}
