package com.joojet.plugins.mobs.util.customtargets;

import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.entity.DragonFireball;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.util.Vector;

import net.minecraft.server.v1_16_R2.EntityGiantZombie;
import net.minecraft.server.v1_16_R2.EntityLiving;
import net.minecraft.server.v1_16_R2.PathfinderGoal;
import net.minecraft.server.v1_16_R2.Vec3D;

/** A copy and pasted implementation from minecraft source files
 *  of their own GhastFireball pathfinder goal modified to hopefully work
 *  for Giants. */
public class PathfinderGoalGiantFireball extends PathfinderGoal 
{
	private LivingEntity giantBukkit;
	private final EntityGiantZombie giant;
		    
	public int a;
		    
	public PathfinderGoalGiantFireball(EntityGiantZombie entitygiant, LivingEntity entity) {
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
	
	public void e() 
	{
		EntityLiving entityliving = this.giant.getGoalTarget();
		if (entityliving.h(this.giant) < 4096.0D && this.giant.hasLineOfSight(entityliving)) 
		{
			// World world = this.giant.world;
			this.a++;
			// Plays the ghast sound on the 10th tick
			if (this.a == 10 && !this.giant.isSilent())
			{
				// world.a((EntityHuman)null, 1015, this.giant.getChunkCoordinates(), 0);
				giantBukkit.getWorld().playSound(giantBukkit.getLocation(), Sound.ENTITY_DRAGON_FIREBALL_EXPLODE, 1.0f, 1.0f);
			}
			if (this.a == 20) 
			{
				Vec3D vec3d = this.giant.f(1.0F);
				
				Vector fireballLocation = new Vector (this.giant.locX() + vec3d.x * 4.0D, this.giant.e(0.5D) + 0.5D, this.giant.locZ() + vec3d.z * 4.0D);
				Vector targetLocation = new Vector (entityliving.locX(), entityliving.locY(), entityliving.locZ());
				Vector fireballDirection = targetLocation.subtract(fireballLocation).normalize();
				
				DragonFireball fireball = (DragonFireball) this.giantBukkit.getWorld().spawnEntity(new Location (this.giantBukkit.getWorld(), fireballLocation.getX(),
						fireballLocation.getY(), fireballLocation.getZ()), EntityType.DRAGON_FIREBALL);
				fireball.setDirection(fireballDirection);
				fireball.setYield(2.0f);
				this.a = -40;
			} 
		} 
		else if (this.a > 0) 
		{
			this.a--;
		} 
	}
}
