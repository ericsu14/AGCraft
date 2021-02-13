package com.joojet.plugins.mobs.util.particle;

import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.World;
import org.bukkit.entity.LivingEntity;

import com.joojet.plugins.mobs.util.LocationTools;

public class ParticleUtil 
{
	/** Uses the midpoint circle algorithm to draw a flat circle on the XZ plane in the world using the given particle
	 *  @param centerX Center x coordinate of the circle
	 *  @param centerY Center y coordinate of the circle
	 *  @param centerZ Center z coordinate of the circle
	 *  @param radius Radius of the circle
	 *  @param particle Particle used to render the circle
	 *  @param world World the circle is drawn to
	 *  @param stepSize Distance between adjacent particles in the circle */
	public static void drawCircleOnXZPlane (double centerX, double centerY, double centerZ, int radius, Particle particle, World world, double stepSize)
	{
		double d = (5 - radius * 4) / 4;
		double x = 0;
		double z = radius;
		
		// Uses the midpoint circle algorithm to render a circle on the XZ plane on a center coordinate
		do
		{
			drawLine (centerX - x, centerY, centerZ - z, centerX + x, centerY, centerZ + z, particle, world, stepSize);
			if (d < 0)
			{
				d += 2 * x + 1;
			}
			else
			{
				d += 2 * (x - z) + 1;
				z -= stepSize;
			}
			x += stepSize;
		} while (x < z);
	}
	
	/** Draws a line between the two corner coordinates with the given particle
	 *  @param fromX Starting x coord. of the line
	 *  @param fromY Starting y coord. of the line
	 *  @param fromZ Starting z coord. of the line
	 *  @param toX Ending x coord. of the line
	 *  @param toY Ending y coord. of the line
	 *  @param toZ Ending z coord. of the line
	 *  @param particle Particle used to draw the line
	 *  @param world World the line is drawn in
	 *  @param stepSize Distance between adjacent particles in the line */
	public static void drawLine (double fromX, double fromY, double fromZ, double toX, double toY, double toZ, Particle particle, World world, double stepSize)
	{
		for (double x = fromX; x <= toX; x += stepSize)
		{
			for (double y = fromY; y <= toY; y += stepSize)
			{
				for (double z = fromZ; z <= toZ; z += stepSize)
				{
					world.spawnParticle(particle, x, toY, z, 0, 0.0, 0.0, 0.0, 0.0);
				}
			}
		}
	}
	
	/** Spawns a random amount of colored particles (can only be REDSTONE, SPELL_MOB and SPELL_MOB_AMBIENT)
	 *  around an entity.
	 *  @param entity - LivingEntity in which particles are spawned at
	 *  @param count - Amount of particles to be spawned
	 *  @param red - Particle's RGB red value
	 *  @param green - Particle's RGB green value
	 *  @param blue - Particle's RGB blue value
	 *  @param particle - Type of particle to be spawned */
	public static void spawnColoredParticlesOnEntity (LivingEntity entity, int count, int red, int green, int blue, Particle particle)
	{
		if (count <= 0)
		{
			count = 1;
		}
		
		Location entityLocation = entity.getEyeLocation();
		
		Object data = null;
		if (particle == Particle.REDSTONE)
		{
			data = new Particle.DustOptions(Color.fromRGB(red, green, blue), 1.0f);
		}
		for (int i = 0; i < count; ++i)
		{
			entity.getWorld().spawnParticle(particle, LocationTools.addRandomOffsetOnLocation(entityLocation, 0.7),
					0, (red / 256D), (green / 256D), (blue / 256D), 1, data);
		}
	}
}
