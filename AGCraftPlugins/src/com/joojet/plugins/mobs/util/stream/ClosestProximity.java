package com.joojet.plugins.mobs.util.stream;

import java.util.Comparator;

import org.bukkit.Location;
import org.bukkit.entity.LivingEntity;

/** Comparator used to sort a collection of entities by how close that entities is to the skill-caster's location */
public class ClosestProximity implements Comparator <LivingEntity>
{
	/** Stores the skill-caster's current location as a point of reference */
	protected Location casterLocation;
	
	public ClosestProximity (LivingEntity entity)
	{
		this.casterLocation = entity.getLocation().clone();
	}
	
	public ClosestProximity (Location casterLocation)
	{
		this.casterLocation = casterLocation;
	}
	
	/** Compares the squared distance between the entity and the skill-caster's location */
	@Override
	public int compare(LivingEntity a, LivingEntity b) 
	{
		double aDist = this.casterLocation.distanceSquared(a.getLocation().clone());
		double bDist = this.casterLocation.distanceSquared(b.getLocation().clone());
		return Double.compare(aDist, bDist);
	}

}
