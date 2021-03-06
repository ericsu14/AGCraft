package com.joojet.plugins.mobs.pathfinding;

import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LargeFireball;
import org.bukkit.entity.LivingEntity;
import org.bukkit.util.Vector;

import net.minecraft.world.entity.EntityLiving;
import net.minecraft.world.entity.ai.goal.PathfinderGoal;
import net.minecraft.world.entity.monster.EntityGiantZombie;
import net.minecraft.world.phys.Vec3D;


/** A copy and pasted implementation from minecraft source files
 *  of their own GhastFireball pathfinder goal modified to hopefully work
 *  for Giants. */
public class PathfinderGoalGiantFireball extends PathfinderGoal 
{
	private LivingEntity giantBukkit;
	private final EntityGiantZombie giant;
		    
	public int a;
		    
	public PathfinderGoalGiantFireball(EntityGiantZombie entitygiant, LivingEntity entity) 
	{
		this.giant = entitygiant;
		this.giantBukkit = entity;
	}
	
	public boolean a() 
	{
		return (this.giant.getGoalTarget() != null);
	}
		    
	public void c() 
	{
		this.a = 0;
	}
		    
	public void d() 
	{
	}
	
	@Override
	public void e() 
	{
		EntityLiving entityliving = this.giant.getGoalTarget();
		if (entityliving != null &&
				entityliving.f(this.giant) < 1524.0D &&
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
				Vec3D vec3d = this.giant.e(1.0F);
				
				Vector fireballLocation = new Vector (this.giant.locX() + vec3d.getX() * 4.0D, this.giant.e(0.5D) + 0.5D, this.giant.locZ() + vec3d.getZ() * 4.0D);
				Vector targetLocation = new Vector (entityliving.locX(), entityliving.locY(), entityliving.locZ());
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
