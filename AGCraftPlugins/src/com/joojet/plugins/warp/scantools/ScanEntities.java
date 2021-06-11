package com.joojet.plugins.warp.scantools;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Monster;
import org.bukkit.entity.Player;
import org.bukkit.entity.Sittable;
import org.bukkit.entity.Tameable;
import org.bukkit.entity.Vehicle;
import org.bukkit.util.BoundingBox;

import com.joojet.plugins.mobs.interpreter.MonsterTypeInterpreter;
import com.joojet.plugins.mobs.monsters.MobEquipment;

public class ScanEntities 
{
	/** Returns true if there are enemies within an r-block radius of the current player */
	public static boolean ScanNearbyEnemies (Player p, int radius, MonsterTypeInterpreter monsterTypeInterpreter)
	{
		int halfRadius = (int) (radius / 2.0);
		Collection<Entity> entities = p.getWorld().getNearbyEntities(p.getLocation(), radius, halfRadius, radius);
		
		for (Entity e : entities)
		{
			if (e instanceof Monster)
			{
				// Check if the monster is a custom mob and not hostile
				// the player. If so, continue searching
				LivingEntity ent = (LivingEntity) e;
				MobEquipment entEquipment = monsterTypeInterpreter.getMobEquipmentFromEntity(ent);
				if (entEquipment != null 
						&& entEquipment.getIgnoreList().contains(p.getType()))
				{
					continue;
				}
				return true;
			}
		}
		return false;
	}
	
	/** Returns a list of players from an n-block radius around the entity */
	public static List <Player> ScanNearbyPlayers (LivingEntity e, int radius)
	{
		BoundingBox searchBox = e.getBoundingBox().clone();
		searchBox.expand(radius);
		
		List <Player> nearbyPlayers = new ArrayList <Player> ();
		
		for (Player p : Bukkit.getOnlinePlayers())
		{
			if (searchBox.contains(p.getBoundingBox()))
			{
				nearbyPlayers.add(p);
			}
		}
		return nearbyPlayers;
	}
	
	/** Returns a list of player-owned entities from an n-block radius around the entity */
	public static List <Entity> ScanNearbyPlayerOwnedEntities (Player p, int radius)
	{
		int halfRadius = (int) (radius / 2.0);
		Collection<Entity> entities = p.getWorld().getNearbyEntities(p.getLocation(), radius, halfRadius, radius);
		List <Entity> ownedEntities = new ArrayList <Entity> ();
		
		for (Entity ent : entities)
		{
			// Searches for any nearby player-tamed wolves that are not sitting
			if (ent instanceof Sittable && ent instanceof Tameable)
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
