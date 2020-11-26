package com.joojet.plugins.mobs.skills.buff;

import java.util.ArrayList;

import org.bukkit.entity.LivingEntity;

import com.joojet.plugins.mobs.enums.MonsterClassifier;
import com.joojet.plugins.mobs.skills.AbstractSkill;
import com.joojet.plugins.mobs.skills.enums.SkillPropetry;
import com.joojet.plugins.mobs.spawning.FairSpawnController;

public class AttackBuffSkill extends AbstractSkill 
{
	/** Used to calculate fair spawn weights */
	protected FairSpawnController spawnWeight;
	
	/** A monster skill that applies a strength I buff to the caster itself and its
	 *  surrounding allies. */
	public AttackBuffSkill ()
	{
		super (SkillPropetry.BUFF, 8, 30, 300, 10);
		this.spawnWeight = new FairSpawnController (this.getRange());
	}
	
	/** Gives the caster and any of its surrounding allies a Strength I buff. */
	@Override
	protected void handleSkill(LivingEntity caster, ArrayList<LivingEntity> allies, ArrayList<LivingEntity> enemies) 
	{
		
	}
	
	/** Only use this skill if any surrounding player's threat score exceeds mythic level */
	@Override
	protected boolean checkConditons(LivingEntity caster, ArrayList<LivingEntity> allies,
			ArrayList<LivingEntity> enemies) 
	{
		return this.spawnWeight.getAverageThreatScore(caster) >= MonsterClassifier.MYTHIC.getThreshold();
	}

}
