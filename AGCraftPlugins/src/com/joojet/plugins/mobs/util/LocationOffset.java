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
	public static Location addRandomOffsetOnLocation (Location location, double offset)
	{
		double xOffset = generateRandomOffset (offset);
		double yOffset = generateRandomOffset (offset);
		double zOffset = generateRandomOffset (offset);
		
		Location transformed = new Location (location.getWorld(), location.getX(), location.getY(),
				location.getZ());
		transformed.add(xOffset, yOffset, zOffset);
		return transformed;
	}
	
	
	/** Generates a random number between -offset and +offset, which can be used
	 *  as a random offset for a coordinate
	 *  @param offset - The offset */
	public static double generateRandomOffset (double offset)
	{
		double min = -offset;
		double max = offset;
		return min + (max - min) * rand.nextDouble();
	}
}
