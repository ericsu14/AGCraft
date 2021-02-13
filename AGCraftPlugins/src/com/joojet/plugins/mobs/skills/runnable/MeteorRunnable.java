package com.joojet.plugins.mobs.skills.runnable;

import java.util.List;

import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.entity.LivingEntity;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

import com.joojet.plugins.mobs.skills.attack.MeteorSkill;
import com.joojet.plugins.mobs.util.particle.ParticleUtil;


public class MeteorRunnable extends BukkitRunnable
{
	/** Amount of time this skill will last (in ticks) */
	protected int duration;
	/** Explosion power of the shot fireballs */
	protected float power;
	/** The caster using this skill */
	protected LivingEntity caster;
	/** A list of targets to hit with this skill */
	protected List <LivingEntity> targets;
	/** A reference to the metor skill instance */
	protected MeteorSkill meteorSkill;
	/** Height of the meteor effect cloud */
	protected double effectCloudHeight = 10.0;
	
	public MeteorRunnable (int duration, LivingEntity caster, List <LivingEntity> targets, MeteorSkill meteorSkill)
	{
		this.duration = duration;
		this.power = meteorSkill.power;
		this.caster = caster;
		this.targets = targets;
		this.meteorSkill = meteorSkill;
		
		this.onInit();
	}
	
	/** Startup procedure for this skill */
	public void onInit ()
	{
		// Upon starting, the caster will be immobilized and will have the levitation effect while this skill is used.
		// A flag will be raised, making the caster invurnable to all attacks throughout the skill's duration
		this.caster.addPotionEffect(new PotionEffect (PotionEffectType.GLOWING, this.duration, 0));
		this.caster.addPotionEffect(new PotionEffect (PotionEffectType.SLOW, this.duration, 10));
		this.caster.addPotionEffect(new PotionEffect (PotionEffectType.LEVITATION, this.duration, 0));
		meteorSkill.setActive(true);
	}
	
	@Override
	public void run() 
	{
		// Terminates this skill if the duration reaches 0
		if (duration <= 0)
		{
			this.meteorSkill.setActive(false);
			this.cancel();
		}
		
		Location casterLocation = this.caster.getLocation();
		// Casts a large effect cloud every second
		if (duration % 20 == 0)
		{
			ParticleUtil.drawCircleOnXZPlane(casterLocation.getX(), (casterLocation.getY() + effectCloudHeight), casterLocation.getZ(), 
					this.meteorSkill.getRange(), Particle.SMOKE_LARGE, casterLocation.getWorld(), 0.5);
		}
		
		// Every half second, fire a meteor
		if (duration % 10 == 0)
		{
			// TODO
		}
		
		--duration;
	}
	
}
