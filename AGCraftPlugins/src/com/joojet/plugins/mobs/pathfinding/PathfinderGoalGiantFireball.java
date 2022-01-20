package com.joojet.plugins.mobs.pathfinding;

import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LargeFireball;
import org.bukkit.entity.LivingEntity;
import org.bukkit.util.Vector;

import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.monster.Giant;
import net.minecraft.world.phys.Vec3;


/** A copy and pasted implementation from minecraft source files
 *  of their own GhastFireball pathfinder goal modified to hopefully work
 *  for Giants. */
public class PathfinderGoalGiantFireball extends Goal 
{
	private LivingEntity giantBukkit;
	private final Giant giant;
		    
	public int a = 0;
		    
	public PathfinderGoalGiantFireball(Giant entitygiant, LivingEntity entity) 
	{
		this.giant = entitygiant;
		this.giantBukkit = entity;
	}
	
	@Override
	public boolean canUse() 
	{
		return (this.giant.getTarget() != null);
	}
		
	@Override
	public void start() 
	{
		this.a = 0;
	}
	
	@Override
	public void stop() 
	{
	}
	
	@Override
	public void tick() 
	{
		net.minecraft.world.entity.LivingEntity entityliving = this.giant.getTarget();
		if (entityliving != null &&
				entityliving.distanceToSqr(this.giant) < 1524.0D &&
				this.giant.hasLineOfSight(entityliving)) 
		{
			// World world = this.giant.world;
			this.a++;
			// Plays the ghast sound on the 10th tick
			if (this.a == 10 && !this.giant.isSilent())
			{
				giantBukkit.getWorld().playSound(giantBukkit.getLocation(), Sound.ENTITY_WITHER_SHOOT, 1.0f, 1.0f);
			}
			if (this.a == 20) 
			{
				Vec3 vec3d = this.giant.getViewVector(1.0F);
				
				Vector fireballLocation = new Vector (this.giant.getX() + vec3d.x() * 4.0D, this.giant.getX(0.5D) + 0.5D, this.giant.getZ() + vec3d.z() * 4.0D);
				Vector targetLocation = new Vector (entityliving.getX(), entityliving.getY(), entityliving.getZ());
				Vector fireballDirection = targetLocation.subtract(fireballLocation).normalize();
				
				LargeFireball fireball = (LargeFireball) this.giantBukkit.getWorld().spawnEntity(new Location (this.giantBukkit.getWorld(), fireballLocation.getX(),
						fireballLocation.getY(), fireballLocation.getZ()), EntityType.FIREBALL);
				fireball.setDirection(fireballDirection);
				fireball.setYield(3.0f);
				fireball.setIsIncendiary(false);
				fireball.setShooter(this.giantBukkit);
				this.a = -80;
			} 
		} 
		else if (this.a > 0) 
		{
			this.a--;
		}
	}
}
