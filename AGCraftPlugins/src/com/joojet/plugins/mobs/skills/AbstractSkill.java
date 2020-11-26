package com.joojet.plugins.mobs.skills;

import java.util.ArrayList;

import org.bukkit.entity.LivingEntity;

import com.joojet.plugins.mobs.skills.enums.SkillPropetry;

public abstract class AbstractSkill 
{
	/** The skill's AOE radius */
	protected int range;
	/** The skill's cooldown */
	protected int cooldown;
	/** The skill's type */
	protected SkillPropetry type;
	/** Max amount of uses for this skill */
	protected int maxUses;
	/** Current cooldown tick */
	private int cooldownTick;
	/** Current usage of the skill */
	private int currentUsage;
	/** The weight of this skill. Higher weights means this skill is to be used more frequently. */
	private int weight;
	
	public AbstractSkill (SkillPropetry type, int range, int cooldown, int maxUses, int weight)
	{
		this.type = type;
		this.cooldown = cooldown;
		this.range = range;
		this.maxUses = maxUses;
		this.currentUsage = this.maxUses;
		this.cooldownTick = 0;
		this.weight = weight;
	}
	
	/** Allows the caster to use a skill once the internal cooldown tick reaches zero and its specified conditions are met. 
	 * 		@param caster - The LivingEntity using this skill
	 * 		@param allies - A list of allies this skill may positively affect
	 * 		@param enemies - A list of enemies this skill may negatively affect */
	public void useSkill (LivingEntity caster, ArrayList <LivingEntity> allies, ArrayList <LivingEntity> enemies)
	{
		if (cooldownTick <= 0 && this.checkConditons(caster, allies, enemies)
				&& this.currentUsage > 0)
		{
			this.handleSkill(caster, allies, enemies);
			this.cooldownTick = this.cooldown;
			--this.currentUsage;
		}
		else
		{
			this.cooldownTick = (this.cooldownTick - 1) >= 0 ? this.cooldownTick - 1 : 0;
		}
	}
	
	/** Allows the skillcaster to use a custom skill.
	 * 		@param caster - The LivingEntity using this skill
	 * 		@param allies - A list of allies this skill may positively affect
	 * 		@param enemies - A list of enemies this skill may negatively affect */
	protected abstract void handleSkill (LivingEntity caster, ArrayList <LivingEntity> allies, ArrayList <LivingEntity> enemies);
	
	/** Defines conditions that need to be met in order for this skill to be used.
	 * 		@return True if those conditions are met. False otherwise.
	 * 		@param caster - The LivingEntity using this skill
	 * 		@param allies - A list of allies this skill may positively affect
	 * 		@param enemies - A list of enemies this skill may negatively affect */
	protected abstract boolean checkConditons (LivingEntity caster, ArrayList <LivingEntity> allies, ArrayList <LivingEntity> enemies);
	
	/** Returns the AOE effect radius assigned to this skill */
	public int getRange ()
	{
		return this.range;
	}
	
	/** Returns the weight assigned to this skill. Higher weights means this skill will be selected
	 *  more frequently. */
	public int getWeight ()
	{
		return this.weight;
	}
}
