package com.joojet.plugins.mobs.skills.runnable;

import java.util.List;

import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.entity.LivingEntity;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

import com.joojet.plugins.mobs.skills.attack.ThundagaSkill;

public class ThunderSkillRunnable extends BukkitRunnable {
	/** A reference to the thundaga skill instance using this runnable  */
	protected ThundagaSkill skill;
	/** The caster casting the skill */
	protected LivingEntity caster;
	/** List of nearby entities surrounding the caster at the time the skill is used */
	protected List <LivingEntity> allies;
	/** Target location where the thunder should strike at */
	protected Location targetLocation;
	/** Internal ticks timer for keeping track of thunder delay */
	private int ticks;
	
	/** Creates a new thunder strike cast, allowing a mob's thunder skill to strike
	 *  at a certain location in the world after a short delay.
	 *  @param skill A reference to the thundaga skill instance launching this runnable instance
	 *  @param targetLocation Location where the lightning should strike
	 *  @param caster The caster using this skill
	 *  @param allies A list of allies surrounding the caster by the time of its use */
	public ThunderSkillRunnable (ThundagaSkill skill, Location targetLocation, LivingEntity caster, List <LivingEntity> allies)
	{
		this.skill = skill;
		this.targetLocation = targetLocation.clone();
		this.caster = caster;
		this.allies = allies;
		this.ticks = skill.getDelayTicks();
	}
	@Override
	public void run() 
	{
		// Cancels the skill if the caster is dead
		if (this.caster.isDead())
		{
			this.cancel();
		}
		
		// Gives caster (and all surrounding allies) invincibility for 3 ticks so they can survive his own thunder 
		if (this.ticks == 1)
		{
			PotionEffect resistance = new PotionEffect (PotionEffectType.DAMAGE_RESISTANCE, 2, 5);
			this.caster.addPotionEffect(resistance);
			for (LivingEntity ally : this.allies)
			{
				if (!ally.isDead())
				{
					ally.addPotionEffect(resistance);
				}
			}
			
		}
		// Cast lightning and create explosion once delay is served
		else if (this.ticks <= 0)
		{
			this.targetLocation.getWorld().strikeLightning(this.targetLocation);
			this.targetLocation.getWorld().createExplosion(this.targetLocation.add(0.0, 1.0, 0.0), this.skill.getExplosionPower(), false, false, this.caster);
			this.cancel();
		}
		// Otherwise, play particle effects on the location based on a dice roll
		else if (this.ticks % 2 == 0 && this.skill.getRandomGenerator().nextDouble() >= 0.50)
		{
			this.targetLocation.getWorld().spawnParticle(Particle.SPELL_INSTANT, this.targetLocation, 6, 1.0, 1.0, 1.0);
			if (!this.caster.isDead())
			{
				this.caster.getWorld().spawnParticle(Particle.SPELL_INSTANT, this.caster.getLocation(), 4, 1.0, 1.0, 1.0);
				this.caster.getWorld().spawnParticle(Particle.VILLAGER_ANGRY, this.caster.getLocation(), 2, 1.0, 1.0, 1.0);
			}
		}
		
		--this.ticks;
	}

}
