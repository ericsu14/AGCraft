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
	/** The power put onto the launched fireworks */
	protected int power;
	/** Max amount of fireworks to be spawned */
	protected int fireworkCount;
	
	/** Constructs a new spawn fireworks task, which will continuously spawn random fireworks around
	 *  as long as this task is still running.
	 *  @param spawnLocation - The location in the world where the fireworks should spawn
	 *  @param radius - The max. radius in which the fireworks should spread out
	 *  @param power - The power applied onto the generated fireworks.
	 *  @param fireworkCount - The amount of fireworks that will be launched */
	public SpawnFireworksOnLocationTask (Location spawnLocation, int radius, int power, int fireworkCount)
	{
		this.spawnLocation = spawnLocation;
		this.fireworkGenerator = new FireworkTypes();
		this.radius = radius;
		this.power = power;
		this.rand = new Random();
		this.fireworkCount = fireworkCount;
	}
	
	/** Spawns a random firework around the spawn location point within a radius
	 *  until the firework count reaches 0. */
	@Override
	public void run() 
	{	
		if (this.fireworkCount > 0)
		{
			double xOffset = generateRandomOffset();
			double zOffset = generateRandomOffset();
			int yCoord = spawnLocation.getWorld().getHighestBlockYAt((int) (xOffset + spawnLocation.getX()), (int)(zOffset + spawnLocation.getBlockZ()));
			
			Location fireworkLocation = new Location (this.spawnLocation.getWorld(), this.spawnLocation.getX(), yCoord + 2.0, this.spawnLocation.getZ());
			fireworkLocation.add(xOffset, 0.0, zOffset);
			
			Firework firework = (Firework) fireworkLocation.getWorld().spawnEntity(fireworkLocation, EntityType.FIREWORK);
			firework.setFireworkMeta((FireworkMeta)fireworkGenerator.getRandomFirework(1, this.power).getItemMeta());
			--this.fireworkCount;
		}
		else
		{
			this.cancel();
		}
	}
	
	/** Invokes the RNG to generate a random double between the passed
	 *  radius */
	protected double generateRandomOffset ()
	{
		int min = -this.radius;
		int max = this.radius;
		return (double) this.rand.nextInt ((max - min) + 1) + min;
	}
	
}
