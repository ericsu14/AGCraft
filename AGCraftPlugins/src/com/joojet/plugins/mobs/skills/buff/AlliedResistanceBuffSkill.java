package com.joojet.plugins.mobs.skills.buff;

import java.util.ArrayList;

import org.bukkit.entity.LivingEntity;

public class AlliedResistanceBuffSkill extends ResistanceBuffSkill 
{
	/** Creates an allied speed buff skill that can be used by allies without the high threat score requirements */
	public AlliedResistanceBuffSkill(int amplifier, int duration, int range, int cooldown, int weight) 
	{
		super(amplifier, duration, range, cooldown, weight);
	}
	
	/** Only use this skill if there is at least one player around the entity */
	@Override
	protected boolean checkConditons(LivingEntity caster, ArrayList<LivingEntity> allies,
			ArrayList<LivingEntity> enemies) 
	{
		return this.checkForSurroundingPlayers(allies);
	}

}
