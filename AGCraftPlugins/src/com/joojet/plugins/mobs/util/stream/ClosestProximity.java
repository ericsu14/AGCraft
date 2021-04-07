package com.joojet.plugins.mobs.util.stream;

import java.util.Comparator;

import org.bukkit.Location;
import org.bukkit.entity.LivingEntity;

public class ClosestProximity implements Comparator <LivingEntity>
{
	protected Location casterLocation;
	
	public ClosestProximity (Location casterLocation)
	{
		this.casterLocation = casterLocation;
	}
	
	@Override
	public int compare(LivingEntity a, LivingEntity b) 
	{
		return (int) (casterLocation.distanceSquared(a.getLocation()) - casterLocation.distanceSquared(b.getLocation()));
	}

}
