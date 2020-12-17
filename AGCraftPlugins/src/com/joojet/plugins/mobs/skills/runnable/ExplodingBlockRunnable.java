package com.joojet.plugins.mobs.skills.runnable;

import org.bukkit.entity.FallingBlock;
import org.bukkit.scheduler.BukkitRunnable;

public class ExplodingBlockRunnable extends BukkitRunnable
{
	/** The entity this task is attached to */
	protected FallingBlock entity;
	
	/** Total amount of ticks this runnable can run before self-terminating */
	protected int ticks;
	
	/** Explosion power of final explosion once the projectile reaches the ground */
	protected float power;
	
	/** Creates a new instance of an exploding block runnable, where it
	 *  continuously checks if the falling block has reached the ground. 
	 *  
	 *  Once the projectile hits the ground, the server creates an explosion on that location
	 *  with a set power.
	 *  
	 *  @param entity - The falling block projectile this runnable is managing
	 *  @param ticks - The total amount of ticks this runnable can run before self-terminating
	 *  @param power - Power of final explosion once block hits the ground*/
	
	public ExplodingBlockRunnable (FallingBlock entity, int ticks, float power)
	{
		this.entity = entity;
		this.ticks = ticks;
		this.power = power;
	}
	
	@Override
	public void run() {
		if (ticks <= 0)
		{
			this.cancel();
		}
			
		if (entity != null && (entity.isOnGround() || entity.isDead()))
		{
			entity.getWorld().createExplosion(entity.getLocation(), power, false, false);
			this.cancel();
		}
		--ticks;
	}

}
