package com.joojet.plugins.mobs.util.customtargets;

import net.minecraft.server.v1_16_R2.EntityGiantZombie;
import net.minecraft.server.v1_16_R2.EntityHuman;
import net.minecraft.server.v1_16_R2.EntityLargeFireball;
import net.minecraft.server.v1_16_R2.EntityLiving;
import net.minecraft.server.v1_16_R2.PathfinderGoal;
import net.minecraft.server.v1_16_R2.Vec3D;
import net.minecraft.server.v1_16_R2.World;

/** A copy and pasted implementation from minecraft source files
 *  of their own GhastFireball pathfinder goal modified to hopefully work
 *  for Giants. */
public class PathfinderGoalGiantFireball extends PathfinderGoal 
{
	private final EntityGiantZombie giant;
		    
	public int a;
		    
	public PathfinderGoalGiantFireball(EntityGiantZombie entitygiant) {
		this.giant = entitygiant;
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
			World world = this.giant.world;
			this.a++;
			if (this.a == 10 && !this.giant.isSilent())
			{
				world.a((EntityHuman)null, 1015, this.giant.getChunkCoordinates(), 0);
			}
			if (this.a == 20) 
			{
				Vec3D vec3d = this.giant.f(1.0F);
				double d2 = entityliving.locX() - this.giant.locX() + vec3d.x * 4.0D;
				double d3 = entityliving.e(0.5D) - 0.5D + this.giant.e(0.5D);
				double d4 = entityliving.locZ() - this.giant.locZ() + vec3d.z * 4.0D;
				if (!this.giant.isSilent())
					world.a((EntityHuman)null, 1016, this.giant.getChunkCoordinates(), 0); 
				EntityLargeFireball entitylargefireball = new EntityLargeFireball(world, this.giant, d2, d3, d4);
				entitylargefireball.bukkitYield = (entitylargefireball.yield = 2);
				entitylargefireball.setPosition(this.giant.locX() + vec3d.x * 4.0D, this.giant.e(0.5D) + 0.5D, entitylargefireball.locZ() + vec3d.z * 4.0D);
				world.addEntity(entitylargefireball);
				this.a = -40;
			} 
		} 
		else if (this.a > 0) 
		{
			this.a--;
		} 
	}
}
