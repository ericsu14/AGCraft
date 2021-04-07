package com.joojet.plugins.mobs.util.stream;

import java.util.function.Predicate;

import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;

/** Filters out non-player entities from a collection of living entities */
public class FilterNonPlayerEntities implements Predicate <LivingEntity>
{
	@Override
	public boolean test(LivingEntity t) 
	{
		return t.getType() == EntityType.PLAYER;
	}
}
