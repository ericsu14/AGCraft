package com.joojet.plugins.mobs.pathfinding;

import org.bukkit.Location;
import org.bukkit.entity.LivingEntity;

import com.joojet.plugins.agcraft.main.AGCraftPlugin;
import com.joojet.plugins.mobs.pathfinding.util.LaserBeam;

import net.minecraft.server.v1_16_R2.DamageSource;
import net.minecraft.server.v1_16_R2.EntityInsentient;
import net.minecraft.server.v1_16_R2.EntityLiving;
import net.minecraft.server.v1_16_R2.PathfinderGoal;

public class PathfinderGoalLaserAttack extends PathfinderGoal
{
	/** The NMS entity attached to this pathfinding goal */
	protected EntityInsentient nmsEntity;
	/** The living entity attached to this pathfinding goal */
	protected LivingEntity entity;
	/** Tick cooldown timer for this attack */
	protected int tickCooldown;
	/** Tick needed to be reached before the entity can peform the laser attack */
	protected int attackTick;
	/** Laser beam used to follow the target */
	protected LaserBeam laser;
	
	public PathfinderGoalLaserAttack (EntityInsentient nmsEntity, LivingEntity entity)
	{
		this.nmsEntity = nmsEntity;
		this.entity = entity;
		this.attackTick = 80;
	}
	
	/** Returns true if there exists a living target for this mob */
	@Override
	public boolean a() 
	{
		EntityLiving target = this.nmsEntity.getGoalTarget();
		return (target != null && target.isAlive());
	}
	
	public boolean b()
	{
		// Entity must be alive
		// and target must be 9 blocks away from this entity?
		return (super.b() && this.nmsEntity.h(this.nmsEntity.getGoalTarget()) > 9.0D);
	}
	
	/** Resets tick cooldown and navigation and tells the mob to
	 *  look at the target. */
	public void c()
	{
		this.tickCooldown = -10;
	}
	
	/** Fires a lazer at the entity's current target */
	@Override
	public void e()
	{
		EntityLiving target = this.nmsEntity.getGoalTarget();
		this.nmsEntity.getNavigation().o();
		this.nmsEntity.getControllerLook().a (target, 90.0F, 90.0F);
		
		if (!this.nmsEntity.hasLineOfSight(target))
		{
			this.nmsEntity.setGoalTarget((EntityLiving) null);
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
			target.damageEntity(DamageSource.mobAttack(this.nmsEntity), damage);
			this.nmsEntity.setGoalTarget((EntityLiving)null);
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
		super.e();
	}
	
	private Location getTargetLocation (EntityLiving target)
	{
		return new Location (entity.getWorld(), target.locX(), target.locY(), target.locZ());
	}

}
