package com.joojet.plugins.mobs.skills;

import java.util.ArrayList;

import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.entity.LivingEntity;

import com.joojet.plugins.mobs.skills.enums.SkillPropetry;
import com.joojet.plugins.mobs.util.LocationOffset;

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
		if (this.canUseSkill() && this.checkConditons(caster, allies, enemies))
		{
			this.handleSkill(caster, allies, enemies);
			this.cooldownTick = this.cooldown;
			if (this.maxUses != Integer.MAX_VALUE)
			{
				--this.currentUsage;
			}
		}
	}
	
	/** Updates the skill's internal cooldown timer */
	public void update ()
	{
		this.cooldownTick = (this.cooldownTick - 1) >= 0 ? this.cooldownTick - 1 : 0;
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
	
	/** Returns a boolean indicating w/e or not a skill can be used based on its current cooldown and usage */
	public boolean canUseSkill ()
	{
		return (cooldownTick <= 0 && this.currentUsage > 0);
	}
	
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
	
	/** Spawns a random amount of colored particles (can only be REDSTONE, SPELL_MOB and SPELL_MOB_AMBIENT)
	 *  around an entity.
	 *  @param entity - LivingEntity in which particles are spawned at
	 *  @param count - Amount of particles to be spawned
	 *  @param red - Particle's RGB red value
	 *  @param green - Particle's RGB green value
	 *  @param blue - Particle's RGB blue value
	 *  @param particle - Type of particle to be spawned */
	public void spawnColoredParticlesOnEntity (LivingEntity entity, int count, int red, int green, int blue, Particle particle)
	{
		if (count <= 0)
		{
			count = 1;
		}
		
		Location entityLocation = entity.getEyeLocation();
		for (int i = 0; i < count; ++i)
		{
			entity.getWorld().spawnParticle(particle, LocationOffset.addRandomOffsetOnLocation(entityLocation, 1),
					0, (red / 256D), (green / 256D), (blue / 256D), 1, null);
		}
	}
}