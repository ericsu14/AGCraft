package com.joojet.plugins.mobs.scrolls;

import org.bukkit.entity.EntityType;

import com.joojet.plugins.mobs.villager.wandering.JohnnyRusnak;

public class SummonJohnnyRusnak extends SummoningScroll 
{
	public SummonJohnnyRusnak ()
	{
		super (new JohnnyRusnak(), EntityType.WANDERING_TRADER);
	}
}
