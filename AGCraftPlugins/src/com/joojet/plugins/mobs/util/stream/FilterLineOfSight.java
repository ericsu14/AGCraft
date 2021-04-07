package com.joojet.plugins.mobs.util.stream;

import java.util.function.Predicate;

import org.bukkit.entity.LivingEntity;

public class FilterLineOfSight implements Predicate <LivingEntity>
{
	protected LivingEntity caster;
	
	public FilterLineOfSight (LivingEntity caster)
	{
		this.caster = caster;
	}
	
	@Override
	public boolean test(LivingEntity t) 
	{
		return t.hasLineOfSight(this.caster);
	}
	 
}
