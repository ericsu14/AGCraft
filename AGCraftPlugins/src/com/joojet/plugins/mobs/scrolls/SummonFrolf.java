package com.joojet.plugins.mobs.scrolls;

import org.bukkit.entity.EntityType;

import com.joojet.plugins.mobs.villager.wandering.Frolf;

public class SummonFrolf extends SummoningScroll
{
	public SummonFrolf() 
	{
		super(new Frolf(), EntityType.WANDERING_TRADER);
	}
}
