package com.joojet.plugins.mobs.pathfinding;

import org.bukkit.Location;
import org.bukkit.entity.LivingEntity;

import com.joojet.plugins.agcraft.main.AGCraftPlugin;
import com.joojet.plugins.mobs.pathfinding.util.LaserBeam;

import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.goal.Goal;

public class PathfinderGoalLaserAttack extends Goal
{
	/** The NMS entity attached to this pathfinding goal */
	protected Mob nmsEntity;
	/** The living entity attached to this pathfinding goal */
	protected LivingEntity entity;
	/** Tick cooldown timer for this attack */
	protected int tickCooldown;
	/** Tick needed to be reached before the entity can peform the laser attack */
	protected int attackTick;
	/** Laser beam used to follow the target */
	protected LaserBeam laser;
	
	public PathfinderGoalLaserAttack (Mob nmsEntity, LivingEntity entity)
	{
		this.nmsEntity = nmsEntity;
		this.entity = entity;
		this.attackTick = 80;
	}
	
	/** Returns true if there exists a living target for this mob */
	public boolean canUse() 
	{
		net.minecraft.world.entity.LivingEntity target = this.nmsEntity.getTarget();
		return (target != null && target.isAlive());
	}
	
	public boolean canContinueToUse()
	{
		// Entity must be alive
		// and target must be 9 blocks away from this entity?
		return (super.canContinueToUse() && this.nmsEntity.distanceToSqr(this.nmsEntity.getTarget()) > 9.0D);
	}
	
	/** Resets tick cooldown and navigation and tells the mob to
	 *  look at the target. */
	public void start()
	{
		this.tickCooldown = -10;
	}
	
	/** Fires a lazer at the entity's current target */
	@Override
	public void tick()
	{
		net.minecraft.world.entity.LivingEntity target = this.nmsEntity.getTarget();
		this.nmsEntity.getNavigation().stop();
		this.nmsEntity.getLookControl().setLookAt (target, 90.0F, 90.0F);
		
		if (!this.nmsEntity.hasLineOfSight(target))
		{
			this.nmsEntity.setTarget((net.minecraft.world.entity.LivingEntity) null);
			return;
		}
		
		this.tickCooldown++;
		
		if (this.tickCooldown == 0)
		{
			try {
				this.laser = new LaserBeam (this.entity.getEyeLocation().clone(), this.getTargetLocation(target),
						4, 256);
				this.laser.start(AGCraftPlugin.plugin);
				this.laser.callColorChange();
			} 
			catch (ReflectiveOperationException e1) 
			{
				e1.printStackTrace();
			}
		}
		else if (this.tickCooldown >= this.attackTick)
		{
			float damage = 5.0F;
			target.hurt(DamageSource.mobAttack(this.nmsEntity), damage);
			this.nmsEntity.setTarget((net.minecraft.world.entity.LivingEntity)null);
			this.tickCooldown = -80;
		}
		else if (this.laser != null)
		{
			try 
			{
				this.laser.moveStart(this.entity.getEyeLocation().clone());
				this.laser.moveEnd(this.getTargetLocation(target));
			} 
			catch (ReflectiveOperationException e1) 
			{
				e1.printStackTrace();
			}
		}
		super.tick();
	}
	
	private Location getTargetLocation (net.minecraft.world.entity.LivingEntity target)
	{
		return new Location (entity.getWorld(), target.getX(), target.getY(), target.getZ());
	}

}
