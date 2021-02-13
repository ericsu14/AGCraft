package com.joojet.plugins.mobs.skills.runnable;

import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Monster;
import org.bukkit.scheduler.BukkitRunnable;

import com.joojet.plugins.agcraft.main.AGCraftPlugin;
import com.joojet.plugins.mobs.monsters.MobEquipment;
import com.joojet.plugins.mobs.pathfinding.PathfinderGoalCustomMeleeAttack;
import com.joojet.plugins.mobs.pathfinding.util.LaserBeam;
import com.joojet.plugins.mobs.skills.attack.LazerBeamAttack;

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
		this.chargeTime = laserBeamSkill.getDelayTicks();
		this.active = false;
	}
	
	/** Initializes the laser beam operation when this skill is enabled */
	public void onEnable ()
	{
		try
		{
			this.laser = new LaserBeam (this.caster.getEyeLocation().clone(), this.target.getLocation(),
					(this.chargeTime / 20), 256);
			this.laser.start(AGCraftPlugin.plugin);
			this.laser.callColorChange();
		}
		catch (ReflectiveOperationException roe)
		{
			this.cancel();
		}
		
		this.active = true;
	}
	
	@Override
	public void run() 
	{
		if (!this.active)
		{
			this.onEnable();
		}
		
		// Breaks from the skill once the target is out of the caster's line of sight
		if (this.caster.isDead() || this.target.isDead() || !caster.hasLineOfSight(this.target))
		{
			this.cancel();
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
			this.cancel();
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
				}
			}
			else
			{
				this.cancel();
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

}
