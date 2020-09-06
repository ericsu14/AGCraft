package com.joojet.plugins.mobs.fireworks.tasks;

import java.util.Random;

import org.bukkit.Location;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Firework;
import org.bukkit.inventory.meta.FireworkMeta;
import org.bukkit.scheduler.BukkitRunnable;

import com.joojet.plugins.mobs.fireworks.FireworkTypes;

public class SpawnFireworksOnLocationTask extends BukkitRunnable {
	/** The location in which the firework will spawn*/
	protected Location spawnLocation;
	/** The firework generator used to spawn in our custom fireworks */
	protected FireworkTypes fireworkGenerator;
	/** Used to generate a random location around the spawn location  */
	protected Random rand;
	/** Max radius in which the fireworks can spread out from the location*/
	protected int radius;
	/** Max amount of fireworks to be spawned */
	protected int fireworkCount;
	
	/** Constructs a new spawn fireworks task, which will continuously spawn random fireworks around
	 *  as long as this task is still running. */
	public SpawnFireworksOnLocationTask (Location spawnLocation, int radius)
	{
		this.spawnLocation = spawnLocation;
		this.fireworkGenerator = new FireworkTypes();
		this.radius = radius;
		this.rand = new Random();
		this.fireworkCount = 12;
	}
	
	@Override
	public void run() 
	{	
		if (this.fireworkCount > 0)
		{
			double xOffset = generateRandomOffset();
			double zOffset = generateRandomOffset();
			int yCoord = spawnLocation.getWorld().getHighestBlockYAt((int) (xOffset + spawnLocation.getX()), (int)(zOffset + spawnLocation.getBlockZ()));
			
			Location fireworkLocation = this.spawnLocation.add(xOffset, 0.0, zOffset);
			fireworkLocation.setY(yCoord + 2.0);
			Firework firework = (Firework) fireworkLocation.getWorld().spawnEntity(fireworkLocation, EntityType.FIREWORK);
			firework.setFireworkMeta((FireworkMeta)fireworkGenerator.getRandomFirework(1, 2).getItemMeta());
			--this.fireworkCount;
		}
		else
		{
			this.cancel();
		}
	}
	
	/** Invokes the RNG to generate a random double between 0 and the passed
	 *  radius */
	protected double generateRandomOffset ()
	{
		int min = -this.radius;
		int max = this.radius;
		return (double) this.rand.nextInt ((max - min) + 1) + min;
	}
	
}
