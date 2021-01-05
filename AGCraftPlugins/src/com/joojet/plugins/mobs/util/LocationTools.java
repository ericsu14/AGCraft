package com.joojet.plugins.mobs.util;

import java.util.Random;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.entity.LivingEntity;
import org.bukkit.util.BoundingBox;

/** A helper class used to generate a random offset for locations */
public class LocationTools 
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
		
		Location transformed = location.clone();
		transformed.add(xOffset, yOffset, zOffset);
		return transformed;
	}
	
	/** Adds a random X and Z offset to a given location
	 *  @param location Location being transformed
	 *  @param offset Max. offset applied to the location. */
	public static Location addRandomXZOffsetOnLocation (Location location, double offset)
	{
		double xOffset = generateRandomOffset (offset);
		double zOffset = generateRandomOffset (offset);
		
		Location transformed = location.clone();
		transformed.add(xOffset, 0.0, zOffset);
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
	
	/** Uses the caster's bounding box to check if there is enough room (denoted by air blocks) around a location
	 *  for the caster to safely teleport to
	 *  @param caster Skillcaster using the skill
	 *  @param teleportLocation Location being checked */
	public static boolean checkSurroundingArea (LivingEntity caster, LivingEntity target)
	{
		World world = caster.getWorld();
		Block block = null;
		
		BoundingBox scanArea = BoundingBox.of(target.getLocation().add(0.0, caster.getHeight() / 2.0, 0.0), 
				caster.getWidth() / 2.0, caster.getHeight() / 2.0, caster.getWidth() / 2.0);
		
		for (int i = (int) scanArea.getMinX(); i <= (int) scanArea.getMaxX(); ++i)
		{
			for (int j = (int) scanArea.getMinY(); j <= (int) scanArea.getMaxY(); ++j)
			{
				for (int k = (int) scanArea.getMinZ(); k <= (int) scanArea.getMaxZ(); ++k)
				{
					block = world.getBlockAt(i, j, k);
					if (block == null || block.getType() != Material.AIR)
					{
						return false;
					}
				}
			}
		}
		return true;
	}
}
