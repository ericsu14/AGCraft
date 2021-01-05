package com.joojet.plugins.mobs.skills;

import java.util.List;
import java.util.Random;

import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.LivingEntity;

import com.joojet.plugins.mobs.DamageDisplayListener;
import com.joojet.plugins.mobs.bossbar.BossBarController;
import com.joojet.plugins.mobs.interpreter.MonsterTypeInterpreter;
import com.joojet.plugins.mobs.skills.enums.SkillPropetry;
import com.joojet.plugins.mobs.util.LocationTools;

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
	protected int cooldownTick;
	/** Current usage of the skill */
	protected int currentUsage;
	/** The weight of this skill. Higher weights means this skill is to be used more frequently. */
	protected int weight;
	/** Random number generator */
	protected Random random;
	
	public AbstractSkill (SkillPropetry type, int range, int cooldown, int maxUses, int weight)
	{
		this.type = type;
		this.cooldown = cooldown;
		this.range = range;
		this.maxUses = maxUses;
		this.currentUsage = this.maxUses;
		this.cooldownTick = 0;
		this.weight = weight;
		this.random = new Random ();
	}
	
	/** Allows the caster to use a skill once the internal cooldown tick reaches zero and its specified conditions are met. 
	 * 		@param caster - The LivingEntity using this skill
	 * 		@param allies - A list of allies this skill may positively affect
	 * 		@param enemies - A list of enemies this skill may negatively affect
	 * 		@param damageDisplayListener - A reference to the plugin's damage display listener, which is used to display floating name-tags */
	public void useSkill (LivingEntity caster, List <LivingEntity> allies, List <LivingEntity> enemies, DamageDisplayListener damageDisplayListener,
			MonsterTypeInterpreter monsterTypeInterpreter, BossBarController bossBarController)
	{
		if (this.canUseSkill(caster) && this.checkConditons(caster, allies, enemies))
		{
			this.handleSkill(caster, allies, enemies, damageDisplayListener, monsterTypeInterpreter, bossBarController);
			this.cooldownTick = this.cooldown;
			if (this.maxUses != Integer.MAX_VALUE)
			{
				--this.currentUsage;
			}
		}
	}
	
	/** Updates the skill's internal cooldown timer */
	public void update (LivingEntity caster)
	{
		this.cooldownTick = (this.cooldownTick - 1) >= 0 ? this.cooldownTick - 1 : 0;
	}
	
	/** Allows the skillcaster to use a custom skill.
	 * 		@param caster - The LivingEntity using this skill
	 * 		@param allies - A list of allies this skill may positively affect
	 * 		@param enemies - A list of enemies this skill may negatively affect */
	protected abstract void handleSkill (LivingEntity caster, List <LivingEntity> allies, List <LivingEntity> enemies, DamageDisplayListener damageDisplayListener,
			MonsterTypeInterpreter monsterTypeInterpreter, BossBarController bossBarController);
	
	/** Defines conditions that need to be met in order for this skill to be used.
	 * 		@return True if those conditions are met. False otherwise.
	 * 		@param caster - The LivingEntity using this skill
	 * 		@param allies - A list of allies this skill may positively affect
	 * 		@param enemies - A list of enemies this skill may negatively affect */
	protected abstract boolean checkConditons (LivingEntity caster, List <LivingEntity> allies, List <LivingEntity> enemies);
	
	/** Returns a boolean indicating w/e or not a skill can be used based on its current cooldown and usage */
	public boolean canUseSkill (LivingEntity caster)
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
	
	/** Returns the skill's internal random number generator instance */
	public Random getRandomGenerator ()
	{
		return this.random;
	}
	
	
	/** Returns true if the caster's health reaches below a certain threshold
	 *  @param caster The LivingEntity whose health is being checked
	 *  @param threshold The threshold needed to be reached */
	protected boolean checkHealthIsBelowThreshold (LivingEntity caster, double threshold)
	{
		return (caster.getHealth() / caster.getAttribute(Attribute.GENERIC_MAX_HEALTH).getValue()) <= threshold;
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
			entity.getWorld().spawnParticle(particle, LocationTools.addRandomOffsetOnLocation(entityLocation, 0.7),
					0, (red / 256D), (green / 256D), (blue / 256D), 1, null);
		}
	}
}
