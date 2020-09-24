package com.joojet.plugins.mobs.util;

import java.util.Random;

import org.bukkit.Location;

/** A helper class used to generate a random offset for locations */
public class LocationOffset 
{
	/** Random class used to generate random numbers */
	protected static Random rand = new Random ();
	
	/** Transforms a location by adding a random offset to the x and z coordinates
	 *  to that location.
	 *  @param location - Location being transformed
	 *  @param offset - Random offset applied to location */
	public static Location addRandomOffsetOnLocation (Location location, int offset)
	{
		double xOffset = generateRandomOffset (offset);
		double zOffset = generateRandomOffset (offset);
		
		Location transformed = new Location (location.getWorld(), location.getX(), location.getY(),
				location.getZ());
		transformed.add(xOffset, 0, zOffset);
		return transformed;
	}
	
	
	/** Generates a random number between -offset and +offset, which can be used
	 *  as a random offset for a coordinate
	 *  @param offset - The offset */
	public static double generateRandomOffset (int offset)
	{
		int min = -offset;
		int max = offset;
		return (double) rand.nextInt ((max - min) + 1) + min;
	}
}
