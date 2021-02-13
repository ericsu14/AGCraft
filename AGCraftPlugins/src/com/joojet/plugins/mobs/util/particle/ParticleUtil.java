package com.joojet.plugins.mobs.util.particle;

import org.bukkit.Particle;
import org.bukkit.World;

public class ParticleUtil 
{
	public static void drawCircleOnXZPlane (double centerX, double centerY, double centerZ, int radius, Particle particle, World world)
	{
		double d = (5 - radius * 4) / 4;
		double x = 0;
		double z = radius;
		
		// Uses the midpoint circle algorithm to render a circle on the XZ plane on a center coordinate
		do
		{
			drawLine (centerX - x, centerY, centerZ - z, centerX + x, centerY, centerZ + z, particle, world);
			if (d < 0)
			{
				d += 2 * x + 1;
			}
			else
			{
				d += 2 * (x - z) + 1;
				z -= 0.5;
			}
			x += 0.5;
		} while (x < z);
	}
	
	public static void drawLine (double fromX, double fromY, double fromZ, double toX, double toY, double toZ, Particle particle, World world)
	{
		for (double x = fromX; x <= toX; x += 0.5)
		{
			for (double y = fromY; y <= toY; ++y)
			{
				for (double z = fromZ; z <= toZ; z += 0.5)
				{
					world.spawnParticle(particle, x, toY, z, 0, 0.0, 0.0, 0.0, 1.0);
				}
			}
		}
	}
}
