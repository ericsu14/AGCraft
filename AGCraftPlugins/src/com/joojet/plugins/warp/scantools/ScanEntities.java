package com.joojet.plugins.warp.scantools;

import java.util.ArrayList;

import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Monster;
import org.bukkit.entity.Player;
import org.bukkit.entity.Sittable;
import org.bukkit.entity.Tameable;
import org.bukkit.entity.Vehicle;

public class ScanEntities 
{
	/** Returns true if there are enemies within an r-block radius of the current player */
	public static boolean ScanNearbyEnemies (Player p, int radius)
	{
		int halfRadius = (int) (radius / 2.0);
		ArrayList <Entity> entities = (ArrayList<Entity>) p.getNearbyEntities(radius, radius, halfRadius);
		
		for (Entity e : entities)
		{
			if (e instanceof Monster)
			{
				return true;
			}
		}
		return false;
	}
	
	/** Returns a list of players from an n-block radius around the entity */
	public static ArrayList <Player> ScanNearbyPlayers (LivingEntity e, int radius)
	{
		int halfRadius = (int) (radius / 2.0);
		ArrayList <Player> players = new ArrayList <Player> ();
		ArrayList <Entity> entities = (ArrayList<Entity>) e.getNearbyEntities(radius, radius, halfRadius);
		
		for (Entity ent : entities)
		{
			if (ent instanceof Player)
			{
				players.add((Player) ent);
			}
		}
		return players;
	}
	
	/** Returns a list of player-owned entities from an n-block radius around the entity */
	public static ArrayList <Entity> ScanNearbyPlayerOwnedEntities (Player p, int radius)
	{
		int halfRadius = (int) (radius / 2.0);
		ArrayList <Entity> entities = (ArrayList<Entity>) p.getNearbyEntities(radius, radius, halfRadius);
		ArrayList <Entity> ownedEntities = new ArrayList <Entity> ();
		
		for (Entity ent : entities)
		{
			// Searches for any nearby player-tamed wolves that are not sitting
			if (ent instanceof Sittable)
			{
				Sittable sitAnimal = (Sittable) ent;
				Tameable tameAnimal = (Tameable) ent;
				if (tameAnimal.isTamed() && !sitAnimal.isSitting() && tameAnimal.getOwner().equals(p))
				{
					ownedEntities.add(ent);
				}
			}
			
			// Searches for any living entity the player is currently riding in
			else if (ent instanceof Vehicle && ent instanceof LivingEntity)
			{
				for (Entity rider : ent.getPassengers())
				{
					if (rider.equals(p))
					{
						ownedEntities.add(ent);
					}
				}
			}
		}
		
		return ownedEntities;
	}
}
