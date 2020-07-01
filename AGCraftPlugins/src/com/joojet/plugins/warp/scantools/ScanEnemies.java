package com.joojet.plugins.warp.scantools;

import java.util.ArrayList;

import org.bukkit.entity.Entity;
import org.bukkit.entity.Monster;
import org.bukkit.entity.Player;

public class ScanEnemies 
{
	/** Returns true if there are enemies within an r-block radius of the current player */
	public static boolean ScanNearbyEnemies (Player p, int radius)
	{
		int halfRadius = (int) (radius / 2.0);
		ArrayList <Entity> entites = (ArrayList<Entity>) p.getNearbyEntities(radius, radius, halfRadius);
		
		for (Entity e : entites)
		{
			if (e instanceof Monster)
			{
				return true;
			}
		}
		return false;
	}
}
