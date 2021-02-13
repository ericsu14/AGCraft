package com.joojet.plugins.mobs.skills.runnable;

import java.util.List;

import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.entity.LivingEntity;
import org.bukkit.scheduler.BukkitRunnable;

import com.joojet.plugins.mobs.skills.attack.ThundagaSkill;
import com.joojet.plugins.mobs.util.particle.ParticleUtil;

public class ThunderSkillRunnable extends BukkitRunnable 
{
	/** A reference to the thundaga skill instance using this runnable  */
	protected ThundagaSkill skill;
	/** The caster casting the skill */
	protected LivingEntity caster;
	/** List of nearby entities surrounding the caster at the time the skill is used */
	protected List <LivingEntity> allies;
	/** Target location where the thunder should strike at */
	protected List<Location> targetLocations;
	/** Internal ticks timer for keeping track of thunder delay */
	private int ticks;
	
	/** Creates a new thunder strike cast, allowing a mob's thunder skill to strike
	 *  at a certain location in the world after a short delay.
	 *  @param skill A reference to the thundaga skill instance launching this runnable instance
	 *  @param targetLocation Location where the lightning should strike
	 *  @param caster The caster using this skill
	 *  @param allies A list of allies surrounding the caster by the time of its use */
	public ThunderSkillRunnable (ThundagaSkill skill, List<Location> targetLocations, LivingEntity caster, List <LivingEntity> allies)
	{
		this.skill = skill;
		this.targetLocations = targetLocations;
		this.caster = caster;
		this.allies = allies;
		this.ticks = (int) Math.ceil(skill.getDelayTicks() / 2);
	}
	@Override
	public void run() 
	{
		// Cancels the skill if the caster is dead
		if (this.caster.isDead())
		{
			this.cancel();
		}
		
		for (Location targetLocation : this.targetLocations)
		{
			// Cast lightning and create explosion once delay is served
			if (this.ticks <= 0)
			{
				targetLocation.getWorld().spigot().strikeLightning(targetLocation, true);
				targetLocation.getWorld().createExplosion(targetLocation.add(0.0, 1.0, 0.0), this.skill.getExplosionPower(), false, false, this.caster);
				targetLocation.getWorld().playSound(targetLocation, Sound.ENTITY_LIGHTNING_BOLT_THUNDER, 0.8f, 1.0f);
			}
				
			else if (this.ticks % 4 == 0)
			{
				targetLocation.getWorld().spawnParticle(Particle.SPELL_INSTANT, targetLocation, 4, 1.0, 1.0, 1.0);
				targetLocation.getWorld().spawnParticle(Particle.VILLAGER_ANGRY, targetLocation, 2, 1.0, 1.0, 1.0);
				if (!this.caster.isDead())
				{
					skill.spawnColoredParticlesOnEntity(this.caster, 2, 0, 0, 0, Particle.VILLAGER_ANGRY);
					this.caster.getWorld().spawnParticle(Particle.SPELL_INSTANT, this.caster.getLocation(), 5, 1.0, 1.0, 1.0);
				}
			}
			else if (this.ticks % 10 == 0)
			{
				ParticleUtil.drawCircleOnXZPlane(targetLocation.getX(), targetLocation.getY() + 10.0, targetLocation.getZ(), 4, Particle.SMOKE_LARGE, targetLocation.getWorld());
			}
		}
		
		if (this.ticks <= 0)
		{
			this.cancel();
		}
		
		--this.ticks;
	}

}
