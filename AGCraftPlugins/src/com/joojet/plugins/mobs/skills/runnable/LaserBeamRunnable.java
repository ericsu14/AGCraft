package com.joojet.plugins.mobs.skills.runnable;

import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Monster;
import org.bukkit.scheduler.BukkitRunnable;

import com.joojet.plugins.agcraft.main.AGCraftPlugin;
import com.joojet.plugins.mobs.monsters.MobEquipment;
import com.joojet.plugins.mobs.pathfinding.PathfinderGoalCustomMeleeAttack;
import com.joojet.plugins.mobs.pathfinding.util.LaserBeam;
import com.joojet.plugins.mobs.skills.attack.LazerBeamAttack;
import com.joojet.plugins.mobs.util.particle.ParticleUtil;

public class LaserBeamRunnable extends BukkitRunnable
{
	/** The living entity casting this skill */
	protected LivingEntity caster;
	/** MobEquipment instance tied to the caster's equipment */
	protected MobEquipment casterEquipment;
	/** The living entity being targeted by the caster */
	protected LivingEntity target;
	/** A reference to the lazer beam attack skill */
	protected LazerBeamAttack laserBeamSkill;
	/** Laser beam used to follow the target */
	protected LaserBeam laser;
	/** Ticks before laser fire */
	protected int chargeTime;
	/** Signals if this skill is active */
	protected boolean active;
	
	public LaserBeamRunnable (LivingEntity caster, MobEquipment casterEquipment, LivingEntity target, LazerBeamAttack laserBeamSkill)
	{
		this.caster = caster;
		this.casterEquipment = casterEquipment;
		this.target = target;
		this.laserBeamSkill = laserBeamSkill;
		this.chargeTime = laserBeamSkill.getDelayTicks() / 4;
		this.active = false;
	}
	
	/** Initializes the laser beam operation when this skill is enabled */
	public void onEnable ()
	{
		try
		{
			this.laser = new LaserBeam (this.caster.getEyeLocation().clone(), this.target.getLocation(),
					(this.laserBeamSkill.getDelayTicks() / 20), 256);
			this.laser.start(AGCraftPlugin.plugin);
			this.laser.callColorChange();
			this.active = true;
		}
		catch (ReflectiveOperationException roe)
		{
			// Do Nothing
		}
	}
	
	@Override
	public void run() 
	{
		if (!this.active)
		{
			this.onEnable();
		}
		
		// Breaks from the skill once the target is out of the caster's line of sight
		if (this.caster.isDead() || this.target.isDead() || this.outOfSight())
		{
			this.cancel();
			return;
		}
		
		// Attacks the entity once the charge time diminishes
		if (this.chargeTime <= 0)
		{
			// Uses our attackEntity function if the caster is an entity without a base attack stat
			if (this.caster instanceof Monster)
			{
				this.caster.attack(target);
			}
			else
			{
				PathfinderGoalCustomMeleeAttack.attackEntity (this.caster, this.casterEquipment, this.target);
			}
			this.playAttackAnimations();
			this.cancel();
			return;
		}
		// Otherwise, update the laser's starting and ending points based on their current eye locations
		else
		{
			if (this.laser != null)
			{
				try
				{
					this.laser.moveStart(this.caster.getEyeLocation().clone());
					this.laser.moveEnd(this.target.getLocation());
				}
				catch (ReflectiveOperationException roe)
				{
					this.cancel();
					return;
				}
			}
			else
			{
				this.cancel();
				return;
			}
		}
		--this.chargeTime;
	}
	
	/** Deactivates the laser beam once the skill is canceled */
	@Override
	public void cancel ()
	{
		super.cancel();
		
		if (this.laser != null && this.laser.isStarted())
		{
			this.laser.stop();
		}
	}
	
	/** Plays animations upon successful laser attacks */
	public void playAttackAnimations ()
	{
		ParticleUtil.spawnColoredParticlesOnEntity(this.target, 4, 0, 0, 0, Particle.EXPLOSION_NORMAL);
		ParticleUtil.spawnColoredParticlesOnEntity(this.target, 16, 0, 0, 0, Particle.CRIT);
		this.target.getWorld().playSound(this.target.getLocation(), Sound.ENTITY_PLAYER_ATTACK_CRIT, 1.0f, 1.0f);
		this.target.getWorld().playSound(this.target.getLocation(), Sound.ENTITY_GUARDIAN_ATTACK, 1.0f, 1.0f);
	}
	
	/** Returns true if the target is either out of the caster's line of sight
	 *  or out of range of the skillcaster's AOE radius */
	public boolean outOfSight ()
	{
		// Checks if the target is out of the caster's line of sight
		if (!this.caster.hasLineOfSight(this.target))
		{
			return true;
		}
		// Otherwise, check if the target is out of the caster's kill radius (which is the caster's AOE range increased by 20%
		int killRange = (int) Math.ceil((this.laserBeamSkill.getRange() / 2.0) * 1.2);
		return !this.caster.getBoundingBox().clone().expand(killRange)
				.contains(this.target.getBoundingBox());
	}

}
